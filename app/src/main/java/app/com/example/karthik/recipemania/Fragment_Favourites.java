package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.RestAdapter;

/**
 * Created by Karthik on 4/20/2015.
 */


public class Fragment_Favourites extends Fragment {
    public static String RECIPE_ID="";
    static RecipeList recipeList=new RecipeList();
    RecyclerView recyclerView;
    Boolean isConnected;

    public Fragment_Favourites() {
    }

    public static Fragment_Favourites newInstance(String id){
        Fragment_Favourites fragment = new Fragment_Favourites();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.recyclerview_recipelist, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);


        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("My Favourite Recipes");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
        List<RecipeDbItem1> recipeFavourites=rdb.getAllFavourites();
        try {
            FetchFavourites fetchFavourites = new FetchFavourites(recipeFavourites,recyclerView);
            fetchFavourites.execute();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return rootView;
    }

    public class FetchFavourites extends AsyncTask<Void,Void,List<RecipeElement>>
    {
        private List<RecipeDbItem1> recipeDbItem1;
        private List<RecipeElement> recipeElemList=new ArrayList<RecipeElement>();
        RecyclerView recyclerView;

        FetchFavourites(List<RecipeDbItem1> recipeDbItem1, RecyclerView recyclerView1)
        {
            this.recipeDbItem1=recipeDbItem1;
            recyclerView=recyclerView1;
        }

        @Override
        protected List<RecipeElement> doInBackground(Void... params) {
            try {
                ConnectivityManager cm =
                        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint("http://www.food2fork.com")
                            .setLogLevel(RestAdapter.LogLevel.BASIC)
                            .build();


                    WebService service = restAdapter.create(WebService.class);
                    for (int i = 0; i < recipeDbItem1.size(); i++) {
                        RecipeElement recipeElement = new RecipeElement();
                        recipeElement = service.getFullRecipe(recipeDbItem1.get(i).getID());
                        System.out.println("\n\n inside fetchFavs Id is: " + recipeDbItem1.get(i).getID());
                        recipeElemList.add(recipeElement);
                        if (recipeElement.getRecipe().getIngredients() == null) {
                            System.out.println("\n\n inside fetchFavs exception, ID is: " + recipeElement.getRecipe().getRecipe_id());
                            throw new InterruptedException();
                        }
                    }
                }
                else
                {
                    recipeElemList=null;
                }
            }
            catch (Exception e) {
                System.out.println("\nException thrown Karthik: "+e);
            }

            return recipeElemList;
        }
        protected void onPostExecute(List<RecipeElement> recipeElemList)
        {
            List<RecipeListItem> recipeListItems=new ArrayList<RecipeListItem>();
            if(recipeElemList!=null) {
                if (!recipeElemList.isEmpty()) {
                    System.out.println("\n\nFetchFavourites AsyncTask fetched");
                    for (int i = 0; i < recipeElemList.size(); i++) {
                        RecipeListItem recipeListItem = new RecipeListItem(recipeElemList.get(i));
                        recipeListItems.add(recipeListItem);
                    }
                    //RecipeList recipeList=new RecipeList();
                    recipeList.setCount(Integer.toString(recipeListItems.size()));
                    recipeList.setRecipes(recipeListItems);

                    MyRecycleViewAdapter myRecycleViewAdapter = new MyRecycleViewAdapter(recipeList);
                    recyclerView.setAdapter(myRecycleViewAdapter);
                    //System.out.println("\n printing in postExecute");

                    myRecycleViewAdapter.SetOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            System.out.println("\n\nItem CLicked\n");
                            String id = Fragment_Favourites.recipeList.getRecipes().get(position).getRecipe_id();

                            getActivity().getFragmentManager().beginTransaction()
                                    .replace(R.id.container, Fragment_DetailView.newInstance(id))
                                    .addToBackStack(null)
                                    .commit();
                        }
                    });
                }
                else {
                    Crouton.makeText(getActivity(), "NO FAVOURITES YET. BROWSE THROUGH OUR POPULAR RECIPES TO ADD SOME.", Style.INFO).show();
                }
            }
            else {
                Crouton.makeText(getActivity(), "NO NETWORK CONNECTION, TRY AGAIN LATER", Style.ALERT).show();
            }
        }
    }
}

