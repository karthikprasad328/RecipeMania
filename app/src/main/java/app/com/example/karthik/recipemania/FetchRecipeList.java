<<<<<<< HEAD
package app.com.example.karthik.recipemania;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.RestAdapter;

/**
 * Created by Karthik on 4/19/2015.
 * used to find popular recipes
 */

public class FetchRecipeList extends AsyncTask<Void,Void,Boolean> {

    String query;
    RecipeList recipeList;
    RecyclerView recyclerView;
    Activity activity;
    MyRecycleViewAdapter myRecycleViewAdapter;
    String count;
    Boolean isConnected;

    FetchRecipeList(RecyclerView mRecyclerView,Activity mActivity, String count)
    {
        recyclerView=mRecyclerView;
        activity=mActivity;
        this.count=count;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
       try {
           ConnectivityManager cm =
                   (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

           NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
           isConnected = activeNetwork != null &&
                   activeNetwork.isConnectedOrConnecting();
           if (isConnected) {
               RestAdapter restAdapter = new RestAdapter.Builder()
                       .setEndpoint("http://www.food2fork.com")
                       .setLogLevel(RestAdapter.LogLevel.BASIC)
                       .build();


               WebService service = restAdapter.create(WebService.class);

               recipeList = service.getRecipeList(count);
               if (recipeList.getRecipes().isEmpty()) {
                   throw new InterruptedException();
               }
           }
       }
       catch (Exception e) {
                System.out.println("\nException thrown: "+e);
                return false;
       }
       return isConnected;
    }

    //onPostExecute sets the adapter and populates the view
    @Override
    protected void onPostExecute(Boolean success){
        if(success)
        {
            myRecycleViewAdapter=new MyRecycleViewAdapter(recipeList);
            recyclerView.setAdapter(myRecycleViewAdapter);
            myRecycleViewAdapter.notifyDataSetChanged();
            //System.out.println("\n printing in postExecute");

            myRecycleViewAdapter.SetOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    System.out.println("\n\nItem CLicked\n");
                    String id=recipeList.getRecipes().get(position).getRecipe_id();

                    activity.getFragmentManager().beginTransaction()
                            .replace(R.id.container,Fragment_DetailView.newInstance(id))
                            .addToBackStack(null)
                            .commit();

                }
            });
        }
        else {
            Crouton.makeText(activity, "NO NETWORK CONNECTION, TRY AGAIN LATER", Style.ALERT).show();
        }
    }
}
=======
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
 * used to find popular recipes
 */

public class FetchRecipeList extends AsyncTask<Void,Void,Boolean> {

    String query;
    RecipeList recipeList;
    RecyclerView recyclerView;
    Activity activity;
    MyRecycleViewAdapter myRecycleViewAdapter;

    FetchRecipeList(RecyclerView mRecyclerView,Activity mActivity)
    {
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
           recipeList= service.getRecipeList();
           if(recipeList.getRecipes().isEmpty()){
               throw new InterruptedException() ;
           }
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
            //System.out.println("\n printing in postExecute");

            myRecycleViewAdapter.SetOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    System.out.println("\n\nItem CLicked\n");
                    String id=recipeList.getRecipes().get(position).getRecipe_id();

                    activity.getFragmentManager().beginTransaction()
                            .replace(R.id.container,Fragment_DetailView.newInstance(id))
                            .addToBackStack(null)
                            .commit();

                }

                @Override
                public void onOverFlowMenuClick(View view, int position) {
                    final int pos=position;
                    final PopupMenu popup=new PopupMenu(activity,view);
                    final MenuInflater inflater = popup.getMenuInflater();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            switch(item.getItemId()){
                                case R.id.item_favourite:
                                   System.out.println("\nPopupmenu item- " + popup.getMenu().getItem(0).getTitle());
                                   RecipeDatabase rdb=RecipeDatabase.getInstance(activity.getApplicationContext());
                                    RecipeDbItem1 recipeDbItem1=new RecipeDbItem1();
                                    recipeDbItem1=rdb.getFavourite(recipeList.getRecipes().get(pos).getRecipe_id());
                                    if(recipeDbItem1.getID().compareTo("")!=0)
                                    {
                                        Toast.makeText(activity.getApplicationContext(), "Already present in Favourites", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
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
>>>>>>> origin/master
