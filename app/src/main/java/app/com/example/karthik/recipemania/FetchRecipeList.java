package app.com.example.karthik.recipemania;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    }
}
