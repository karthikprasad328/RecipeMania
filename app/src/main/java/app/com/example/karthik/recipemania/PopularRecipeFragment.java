package app.com.example.karthik.recipemania;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by Karthik on 4/20/2015.
 */
public class PopularRecipeFragment extends Fragment {
    private FetchRecipeWithIngredients fetchRecipeWithIngredientsList;
    private FetchRecipeList fetchRecipeList;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private OnRecipeItemSelectedListener mListener;

    public PopularRecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.recyclerview_recipelist, container, false);

            recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);
            mLayoutManager = new LinearLayoutManager(getActivity());
            //mLayoutManager=new GridLayoutManager(getActivity(),2);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(mLayoutManager);
            fetchRecipeList=new FetchRecipeList(recyclerView,getActivity());
            fetchRecipeList.execute();


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRecipeItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRecipeItemSelectedListener {
        // TODO: Update argument type and name
        public void onItemSelected(int position, HashMap<String, ?> movie);

    }




}
