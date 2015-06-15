package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Karthik on 4/21/2015.
 */
public class Fragment_FridgeSearch extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private FetchRecipeList fetchRecipeList;
    private FetchRecipeWithIngredients fetchRecipeWithIngredients;

    public Fragment_FridgeSearch()
    {

    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        final Menu mMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        if(menu.findItem(R.id.search) == null)
            inflater.inflate(R.menu.menu_searchview,menu);
        final SearchView mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        if(mSearchView!=null){
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    fetchRecipeWithIngredients=new FetchRecipeWithIngredients(query,recyclerView,getActivity());
                    fetchRecipeWithIngredients.execute();

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_recipelist, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Enter Title/Ingredients");
        recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        //mLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        fetchRecipeList=new FetchRecipeList(recyclerView,getActivity(),"0");
        fetchRecipeList.execute();


        return rootView;
    }

}
