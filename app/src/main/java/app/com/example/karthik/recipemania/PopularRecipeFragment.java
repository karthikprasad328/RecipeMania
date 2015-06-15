package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dd.CircularProgressButton;

/**
 * Created by Karthik on 4/20/2015.
 */
public class PopularRecipeFragment extends Fragment {
    private FetchRecipeWithIngredients fetchRecipeWithIngredientsList;
    private FetchRecipeList fetchRecipeList;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private CircularProgressButton nextButton;
    private CircularProgressButton prevButton;
    private int count=1;

    public PopularRecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.recyclerview_popular, container, false);


        recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);


         mLayoutManager=new LinearLayoutManager(getActivity());
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Popular Recipes");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        fetchRecipeList=new FetchRecipeList(recyclerView,getActivity(),"0");
        fetchRecipeList.execute();

        prevButton=(CircularProgressButton)rootView.findViewById(R.id.prevButton);
        nextButton=(CircularProgressButton)rootView.findViewById(R.id.nextButton);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1) {
                    count--;
                    fetchRecipeList = new FetchRecipeList(recyclerView, getActivity(), Integer.toString(count));
                    fetchRecipeList.execute();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count >= 1 && count <= 3) {
                    count++;
                    fetchRecipeList = new FetchRecipeList(recyclerView, getActivity(), Integer.toString(count));
                    fetchRecipeList.execute();
                }

            }
        });

        return rootView;
    }






}
