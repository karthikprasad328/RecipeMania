package app.com.example.karthik.recipemania;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import retrofit.RestAdapter;

/**
 * Created by Karthik on 4/19/2015.
 * fetches recipes using ingredients as the parameter
 */
public class FetchRecipeWithIngredients extends AsyncTask<Void,Void,Boolean> {
    String query;
    RecipeList recipeList;
    RecyclerView recyclerView;
    Activity activity;
    MyRecycleViewAdapter myRecycleViewAdapter;

    FetchRecipeWithIngredients(String query,RecyclerView mRecyclerView,Activity mActivity)
    {
        this.query=query;
        recyclerView=mRecyclerView;
        activity=mActivity;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try{
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://www.food2fork.com")
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();


            WebService service = restAdapter.create(WebService.class);
            recipeList= service.getRecipeQueryList(query);
            if(recipeList.getRecipes().isEmpty()){
                throw new InterruptedException() ;
            }
            //printing the recipes
//           for (int i=0; i<recipeList.getRecipes().size();i++)
//           {
//               RecipeListItem recipeListItem=recipeList.getRecipes().get(i);
//               System.out.println("\n Title: "+recipeListItem.getTitle()+"\n");
//           }
        }
        catch (Exception e) {

            System.out.println("\nException thrown: "+e);
            return false;
        }

        return true;
    }
    //onPostExecute sets the adapter and populates the view
    @Override
    protected void onPostExecute(Boolean success){
        if(success)
        {
            myRecycleViewAdapter=new MyRecycleViewAdapter(recipeList);
            recyclerView.setAdapter(myRecycleViewAdapter);

            myRecycleViewAdapter.SetOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(View view, int position) {
                    String id=recipeList.getRecipes().get(position).getRecipe_id();
                    activity.getFragmentManager().beginTransaction()
                            .replace(R.id.container,Fragment_DetailView.newInstance(id))
                            .addToBackStack(null)
                            .commit();
                }

                @Override
                public void onOverFlowMenuClick(View view, int position) {
                    final int pos = position;
                    final PopupMenu popup = new PopupMenu(activity, view);
                    final MenuInflater inflater = popup.getMenuInflater();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_favourite:
                                    System.out.println("\nPopupmenu item- " + popup.getMenu().getItem(0).getTitle());
                                    RecipeDatabase rdb = RecipeDatabase.getInstance(activity.getApplicationContext());
                                    RecipeDbItem1 recipeDbItem1 = new RecipeDbItem1();
                                    recipeDbItem1 = rdb.getFavourite(recipeList.getRecipes().get(pos).getRecipe_id());
                                    if (recipeDbItem1.getID().compareTo("") != 0) {
                                        Toast.makeText(activity.getApplicationContext(), "Already present in Favourites", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        recipeDbItem1.setID(recipeList.getRecipes().get(pos).getRecipe_id());
                                        recipeDbItem1.setTitle(recipeList.getRecipes().get(pos).getTitle());
                                        rdb.addFavourite(recipeDbItem1);
                                        Toast.makeText(activity.getApplicationContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
                                    }
                                    return true;
                                default:
                                    return false;

                            }
                        }
                    });
                    inflater.inflate(R.menu.contextual_menu, popup.getMenu());
                    popup.show();
                }
            });
        }
    }
}
