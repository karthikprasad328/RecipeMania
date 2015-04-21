package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Karthik on 4/20/2015.
 */


public class Fragment_Favourites extends Fragment {
    public static String RECIPE_ID="";

    public Fragment_Favourites() {
    }

    public static Fragment_Favourites newInstance(String id){
        Fragment_Favourites fragment = new Fragment_Favourites();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.detailview_recipe, container, false);

        RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());



        return rootView;
    }
}
