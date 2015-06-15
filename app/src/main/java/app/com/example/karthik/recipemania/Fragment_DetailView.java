<<<<<<< HEAD
package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dd.CircularProgressButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.RestAdapter;

/**
 * Created by Karthik on 4/19/2015.
 */
public class Fragment_DetailView extends Fragment {
    View v;
    public static String RECIPE_ID="";
    public ImageView image;
    public TextView title;
    public TextView source_url;
    public TextView ingredients;
    public CircularProgressButton sourceUrlButton;
    public CircularProgressButton favouritesButton;
    public CircularProgressButton groceriesButton;
    public ProgressBar progressBar;
    public CircularProgressButton plannerButton;
    public TextView textSource;
    public TextView textIngredients;
    public ImageView background;
    public LinearLayout foregroundLayout;

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

        v = inflater.inflate(R.layout.detailview_recipe, container, false);
        image = (ImageView) v.findViewById(R.id.recipeimage);
        title = (TextView) v.findViewById(R.id.recipetitle);
        source_url=(TextView) v.findViewById(R.id.recipesourceurl);
        ingredients=(TextView) v.findViewById(R.id.ingredients);
        sourceUrlButton=(CircularProgressButton)v.findViewById(R.id.sourceUrlButton);
        //favouritesButton=(Button)v.findViewById(R.id.favouritesButton);
        favouritesButton=(CircularProgressButton)v.findViewById(R.id.favouritesButton);
        groceriesButton=(CircularProgressButton) v.findViewById(R.id.groceriesButton);
        progressBar=(ProgressBar)v.findViewById(R.id.progressBar);
        plannerButton=(CircularProgressButton) v.findViewById(R.id.plannerButton);
        textIngredients=(TextView)v.findViewById(R.id.textIngredients);
        textSource=(TextView)v.findViewById(R.id.textSource);
        background=(ImageView)v.findViewById(R.id.background);
        foregroundLayout=(LinearLayout)v.findViewById(R.id.foregroundLayout);

        Bundle bundle = this.getArguments();
        String user = bundle.getString(RECIPE_ID);

        System.out.println(user);

        //query database to change button text
        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
        RecipeDbItem1 recipeDbItem1=rdb.getFavourite(user);
        if(recipeDbItem1.getID() != "")
            favouritesButton.setProgress(100);
            //favouritesButton.setText("REMOVE FROM FAVOURITES");
        //querying web api
        FetchDetailRecipe fetchDetailRecipe=new FetchDetailRecipe(user);
        fetchDetailRecipe.execute();
        return v;
    }

    public class FetchDetailRecipe extends AsyncTask<Void,Void,Boolean> {
        String id;
        RecipeElement recipeElement;
        Boolean isConnected;
        FetchDetailRecipe(String id)
        {
            this.id=id;
        }


        @Override
        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);

        }


        @Override
        protected Boolean doInBackground(Void... params) {
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

                    recipeElement = service.getFullRecipe(id);
                    if (recipeElement.getRecipe().getIngredients().isEmpty()) {
                        throw new InterruptedException();
                    }
                    //printing the recipe ingredients
//            System.out.println("Title: "+recipeElement.getRecipe().getTitle()+"\nIngredients: ");
//            for (int i=0; i<recipeElement.getRecipe().getIngredients().size();i++)
//            {
//               System.out.println(recipeElement.getRecipe().getIngredients().get(i));
//            }
                }
            }
            catch (Exception e) {

                System.out.println("\nException thrown: "+e);
                return false;
            }


            return isConnected;
        }
        protected void onPostExecute(Boolean success){
            if(success)
            {
                background.setVisibility(View.VISIBLE);
                foregroundLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                source_url.setVisibility(View.VISIBLE);
                ingredients.setVisibility(View.VISIBLE);
                favouritesButton.setVisibility(View.VISIBLE);
                groceriesButton.setVisibility(View.VISIBLE);
                plannerButton.setVisibility(View.VISIBLE);
                textSource.setVisibility(View.VISIBLE);
                textIngredients.setVisibility(View.VISIBLE);
                sourceUrlButton.setVisibility(View.VISIBLE);

                Picasso.with(image.getContext())
                        .load(recipeElement.getRecipe().getImage_url())
                        .into(image);

                title.setText((String) recipeElement.getRecipe().getTitle());


                source_url.setText((String) recipeElement.getRecipe().getSource_url());


                ingredients.setText("-" + (String) recipeElement.getRecipe().getIngredients().get(0));
                for(int i=1;i<recipeElement.getRecipe().getIngredients().size();i++)
                {
                    ingredients.append("\n-"+recipeElement.getRecipe().getIngredients().get(i));
                }

                favouritesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
                        //Button b = (Button)v;

//                        RotateAnimation rotate = new RotateAnimation(0, 360,
//                                0, 0, Animation.RELATIVE_TO_SELF,
//                                0.5f);
//
//                        rotate.setDuration(1000);
//                        //rotate.setRepeatCount(1);
//                        favouritesButton.setAnimation(rotate);

                        YoYo.with(Techniques.RotateInDownRight)
                                .duration(700)
                                .playOn(image);
                        //String buttonText = b.getText().toString();
                        RecipeDbItem1 recipeDbItem1 = new RecipeDbItem1();
                        recipeDbItem1.setID(recipeElement.getRecipe().getRecipe_id());
                        recipeDbItem1.setTitle(recipeElement.getRecipe().getTitle());
                        //if(buttonText.compareTo("ADD TO FAVOURITES")==0) {
                          if(favouritesButton.getProgress()==0){
                            rdb.addFavourite(recipeDbItem1);
                            //favouritesButton.setText("REMOVE FROM FAVOURITES");
                            //b.setChecked(true);
                              favouritesButton.setProgress(100);
                            Toast.makeText(getActivity().getApplicationContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
                        }
                       // else if(buttonText.compareTo("REMOVE FROM FAVOURITES")==0){
                        else{
                            rdb.deleteFavourite(recipeDbItem1);
                              favouritesButton.setProgress(0);
                            //System.out.println("\n Button on click . deleting from database");
                            //favouritesButton.setText("ADD TO FAVOURITES");
                            Toast.makeText(getActivity().getApplicationContext(), "Removed from Favourites", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                groceriesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
                        List<String> groceriesList=new ArrayList<String>();
                        groceriesList=recipeElement.getRecipe().getIngredients();
                        //Toast.makeText(getActivity().getApplicationContext(), "Added to Grocery List"+groceriesList.get(0), Toast.LENGTH_SHORT).show();
                        rdb.addGroceries(groceriesList);
                        groceriesButton.setProgress(100);
                        Toast.makeText(getActivity().getApplicationContext(), "Added to Grocery List", Toast.LENGTH_SHORT).show();
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .playOn(image);
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .playOn(ingredients);
                    }
                });

                plannerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent calIntent = new Intent(Intent.ACTION_INSERT);
                        calIntent.setType("vnd.android.cursor.item/event");
                        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "RECIPEMANIA");
                        calIntent.putExtra(CalendarContract.Events.TITLE, recipeElement.getRecipe().getTitle());
                        startActivity(calIntent);
                    }
                });

                sourceUrlButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse((String) recipeElement.getRecipe().getSource_url()));
                        startActivity(intent);
                    }
                });

            }
            else {
                Crouton.makeText(getActivity(), "NO NETWORK CONNECTION, TRY AGAIN LATER", Style.ALERT).show();
            }
        }
    }

}
=======
package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Karthik on 4/19/2015.
 */
public class Fragment_DetailView extends Fragment {
    View v;
    public static String RECIPE_ID="";
    public ImageView image;
    public TextView title;
    public TextView source_url;
    public TextView ingredients;
    public TextView publisher;
    //public Button favouritesButton;
    public ToggleButton favouritesButton;
    public Button groceriesButton;
    public ProgressBar progressBar;
    public Button plannerButton;
    public TextView textPublisher;
    public TextView textSource;
    public TextView textIngredients;

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

        v = inflater.inflate(R.layout.detailview_recipe, container, false);
        image = (ImageView) v.findViewById(R.id.recipeimage);
        title = (TextView) v.findViewById(R.id.recipetitle);
        source_url=(TextView) v.findViewById(R.id.recipesourceurl);
        publisher=(TextView) v.findViewById(R.id.recipepublisher);
        ingredients=(TextView) v.findViewById(R.id.ingredients);
        //favouritesButton=(Button)v.findViewById(R.id.favouritesButton);
        favouritesButton=(ToggleButton)v.findViewById(R.id.favouritesButton);
        groceriesButton=(Button) v.findViewById(R.id.groceriesButton);
        progressBar=(ProgressBar)v.findViewById(R.id.progressBar);
        plannerButton=(Button) v.findViewById(R.id.plannerButton);
        textPublisher=(TextView)v.findViewById(R.id.textPublisher);
        textIngredients=(TextView)v.findViewById(R.id.textIngredients);
        textSource=(TextView)v.findViewById(R.id.textSource);
        Bundle bundle = this.getArguments();
        String user = bundle.getString(RECIPE_ID);

        System.out.println(user);

        //query database to change button text
        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
        RecipeDbItem1 recipeDbItem1=rdb.getFavourite(user);
        if(recipeDbItem1.getID()!="")
            favouritesButton.setChecked(true);
            //favouritesButton.setText("REMOVE FROM FAVOURITES");
        //querying web api
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
        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);

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
                progressBar.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                publisher.setVisibility(View.VISIBLE);
                source_url.setVisibility(View.VISIBLE);
                ingredients.setVisibility(View.VISIBLE);
                favouritesButton.setVisibility(View.VISIBLE);
                groceriesButton.setVisibility(View.VISIBLE);
                plannerButton.setVisibility(View.VISIBLE);
                textPublisher.setVisibility(View.VISIBLE);
                textSource.setVisibility(View.VISIBLE);
                textIngredients.setVisibility(View.VISIBLE);

                Picasso.with(image.getContext())
                        .load(recipeElement.getRecipe().getImage_url())
                        .into(image);

                title.setText((String)recipeElement.getRecipe().getTitle());
                publisher.setText((String)recipeElement.getRecipe().getPublisher());

                source_url.setText((String)recipeElement.getRecipe().getSource_url());
                source_url.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse((String)recipeElement.getRecipe().getSource_url()));
                        startActivity(intent);
                    }
                });

                ingredients.setText("-"+(String)recipeElement.getRecipe().getIngredients().get(0));
                for(int i=1;i<recipeElement.getRecipe().getIngredients().size();i++)
                {
                    ingredients.append("\n-"+recipeElement.getRecipe().getIngredients().get(i));
                }

                favouritesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
                        //Button b = (Button)v;
                        ToggleButton b=(ToggleButton)v;
                        RotateAnimation rotate = new RotateAnimation(0, 360,
                                0, 0, Animation.RELATIVE_TO_SELF,
                                0.5f);

                        rotate.setDuration(1000);
                        //rotate.setRepeatCount(1);
                        favouritesButton.setAnimation(rotate);

                        YoYo.with(Techniques.RotateInDownRight)
                                .duration(700)
                                .playOn(image);
                        //String buttonText = b.getText().toString();
                        RecipeDbItem1 recipeDbItem1 = new RecipeDbItem1();
                        recipeDbItem1.setID(recipeElement.getRecipe().getRecipe_id());
                        recipeDbItem1.setTitle(recipeElement.getRecipe().getTitle());
                        //if(buttonText.compareTo("ADD TO FAVOURITES")==0) {
                          if(favouritesButton.isChecked()){
                            rdb.addFavourite(recipeDbItem1);
                            //favouritesButton.setText("REMOVE FROM FAVOURITES");
                            //b.setChecked(true);
                            Toast.makeText(getActivity().getApplicationContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
                        }
                       // else if(buttonText.compareTo("REMOVE FROM FAVOURITES")==0){
                        else{
                            rdb.deleteFavourite(recipeDbItem1);
                            //System.out.println("\n Button on click . deleting from database");
                            //favouritesButton.setText("ADD TO FAVOURITES");
                            Toast.makeText(getActivity().getApplicationContext(), "Removed from Favourites", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                groceriesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
                        List<String> groceriesList=new ArrayList<String>();
                        groceriesList=recipeElement.getRecipe().getIngredients();
                        //Toast.makeText(getActivity().getApplicationContext(), "Added to Grocery List"+groceriesList.get(0), Toast.LENGTH_SHORT).show();
                        rdb.addGroceries(groceriesList);
                        Toast.makeText(getActivity().getApplicationContext(), "Added to Grocery List", Toast.LENGTH_SHORT).show();
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .playOn(image);
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .playOn(ingredients);
                    }
                });

                plannerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent calIntent = new Intent(Intent.ACTION_INSERT);
                        calIntent.setType("vnd.android.cursor.item/event");
                        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "RECIPEMANIA");
                        calIntent.putExtra(CalendarContract.Events.TITLE,recipeElement.getRecipe().getTitle());
                        startActivity(calIntent);
                    }
                });

            }
        }
    }

}
>>>>>>> origin/master
