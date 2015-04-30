package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Karthik on 4/20/2015.
 */
public class PopularRecipeFragment extends Fragment {
    private FetchRecipeWithIngredients fetchRecipeWithIngredientsList;
    private FetchRecipeList fetchRecipeList;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    public PopularRecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.recyclerview_recipelist, container, false);

            recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);
            mLayoutManager=new LinearLayoutManager(getActivity());
            TextView heading =(TextView)rootView.findViewById(R.id.popularText);
            heading.setText("");
            ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Popular Recipes");
            //mLayoutManager=new GridLayoutManager(getActivity(),2);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(mLayoutManager);
            fetchRecipeList=new FetchRecipeList(recyclerView,getActivity());
            fetchRecipeList.execute();


        return rootView;
    }






}
