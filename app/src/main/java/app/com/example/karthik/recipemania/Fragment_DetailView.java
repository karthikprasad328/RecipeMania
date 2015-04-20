package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit.RestAdapter;

/**
 * Created by Karthik on 4/19/2015.
 */
public class Fragment_DetailView extends Fragment {

    public static String RECIPE_ID="";
    public ImageView image;
    public TextView title;
    public TextView source_url;
    public TextView ingredients;
    public TextView publisher;

    public Fragment_DetailView(){

    }

    public static Fragment_DetailView newInstance(String id){
        Fragment_DetailView fragment = new Fragment_DetailView();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.detailview_recipe, container, false);
        image = (ImageView) v.findViewById(R.id.recipeimage);
        title = (TextView) v.findViewById(R.id.recipetitle);
        source_url=(TextView) v.findViewById(R.id.recipesourceurl);
        publisher=(TextView) v.findViewById(R.id.recipepublisher);
        ingredients=(TextView) v.findViewById(R.id.ingredients);

        Bundle bundle = this.getArguments();
        String user = bundle.getString(RECIPE_ID);

        System.out.println(user);

        FetchDetailRecipe fetchDetailRecipe=new FetchDetailRecipe(user);
        fetchDetailRecipe.execute();
        return v;
    }

    public class FetchDetailRecipe extends AsyncTask<Void,Void,Boolean> {
        String id;
        RecipeElement recipeElement;

        FetchDetailRecipe(String id)
        {
            this.id=id;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            try{
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://www.food2fork.com")
                        .setLogLevel(RestAdapter.LogLevel.BASIC)
                        .build();


                WebService service = restAdapter.create(WebService.class);
                recipeElement= service.getFullRecipe(id);
                if(recipeElement.getRecipe().getIngredients().isEmpty()){
                    throw new InterruptedException() ;
                }
                //printing the recipe ingredients
//            System.out.println("Title: "+recipeElement.getRecipe().getTitle()+"\nIngredients: ");
//            for (int i=0; i<recipeElement.getRecipe().getIngredients().size();i++)
//            {
//               System.out.println(recipeElement.getRecipe().getIngredients().get(i));
//            }
            }
            catch (Exception e) {

                System.out.println("\nException thrown: "+e);
                return false;
            }


            return true;
        }
        protected void onPostExecute(Boolean success){
            if(success)
            {
                Picasso.with(image.getContext())
                        .load(recipeElement.getRecipe().getImage_url())
                        .into(image);

                title.setText("TITLE: "+(String)recipeElement.getRecipe().getTitle());
                publisher.setText("Publisher: " +(String)recipeElement.getRecipe().getPublisher());
                source_url.setText("Source URL: "+(String)recipeElement.getRecipe().getSource_url());
                ingredients.setText("Ingredients:\n"+(String)recipeElement.getRecipe().getIngredients().get(0));
                for(int i=1;i<recipeElement.getRecipe().getIngredients().size();i++)
                {
                    ingredients.append("\n"+recipeElement.getRecipe().getIngredients().get(i));
                }





            }
        }
    }

}