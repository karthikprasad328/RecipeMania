package app.com.example.karthik.recipemania;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Karthik on 4/20/2015.
 */
public class HomeFragment extends android.app.Fragment {


    public HomeFragment(){}


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.homefragment, container, false);

//            recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);
//            mLayoutManager = new LinearLayoutManager(getActivity());
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(mLayoutManager);
//            fetchRecipeList=new FetchRecipeList(recyclerView,getActivity());
//            fetchRecipeList.execute();


        return rootView;
    }
}
