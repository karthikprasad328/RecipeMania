package app.com.example.karthik.recipemania;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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


    public HomeFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.homefragment, container, false);

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("RecipeMania");

        return rootView;
    }
}
