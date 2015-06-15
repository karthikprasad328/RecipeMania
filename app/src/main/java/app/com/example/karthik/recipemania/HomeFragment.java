<<<<<<< HEAD
package app.com.example.karthik.recipemania;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;

/**
 * Created by Karthik on 4/20/2015.
 */
public class HomeFragment extends android.app.Fragment {

    ImageButton imageButton;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private FetchRecipeList fetchRecipeList;
    private ParallaxScrollView parallaxScrollView;


    public HomeFragment(){}


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.homefragment, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("RecipeMania");

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest.Builder adRequest=new AdRequest.Builder().addTestDevice("motorola-xt1032-TA9300HB5L");
        AdRequest adRequest2 = adRequest.build();

        mAdView.loadAd(adRequest2);



        return rootView;
    }
}
=======
package app.com.example.karthik.recipemania;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Karthik on 4/20/2015.
 */
public class HomeFragment extends android.app.Fragment {

    ImageButton imageButton;
    public HomeFragment(){}


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.homefragment, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("RecipeMania");

        imageButton=(ImageButton)rootView.findViewById(R.id.homeimage);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    YoYo.with(Techniques.StandUp)
                            .duration(700)
                            .playOn(imageButton);
            }
        });


        return rootView;
    }
}
>>>>>>> origin/master
