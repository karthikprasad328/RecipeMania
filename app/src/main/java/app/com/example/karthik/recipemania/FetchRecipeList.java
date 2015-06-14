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
