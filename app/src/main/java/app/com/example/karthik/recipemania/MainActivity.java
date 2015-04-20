package app.com.example.karthik.recipemania;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private FetchRecipeWithIngredients fetchRecipeWithIngredientsList;
        private FetchRecipeList fetchRecipeList;
        //private FetchDetailRecipe fetchDetailRecipe;
        private RecyclerView recyclerView;
        private LinearLayoutManager mLayoutManager;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
           // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            View rootView = inflater.inflate(R.layout.recyclerview_recipelist, container, false);

            recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);
            mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(mLayoutManager);
            fetchRecipeList=new FetchRecipeList(recyclerView,getActivity());
            fetchRecipeList.execute();


            return rootView;
        }
    }
}
