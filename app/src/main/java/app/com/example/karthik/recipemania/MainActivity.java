package app.com.example.karthik.recipemania;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout mDrawer;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private  MyDrawerRecyclerViewAdapter mDrawerRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // mToolbar.setTitleTextColor(Integer.parseInt("#ffffff"));
        setSupportActionBar(mToolbar);

        RecipeDatabase rdb=new RecipeDatabase(getApplicationContext());

        mDrawer = (RelativeLayout) findViewById(R.id.drawer);
        mDrawerList = (RecyclerView) findViewById(R.id.drawer_list);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerRecyclerViewAdapter = new MyDrawerRecyclerViewAdapter(this,  (new Drawer_Data()).getDrawerList());
        mDrawerRecyclerViewAdapter.SetOnItemClickListener(new MyDrawerRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                selectItem(position);
            }

        });
        mDrawerList.setAdapter(mDrawerRecyclerViewAdapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mToolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        switch (position) {
            case 0: clearBackStack();
                getFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
                break;

            case 1: getFragmentManager().beginTransaction().replace(R.id.container, new PopularRecipeFragment()).addToBackStack(null).commit();
                break;

            case 2: getFragmentManager().beginTransaction().replace(R.id.container,new Fragment_FridgeSearch()).addToBackStack(null).commit();
                break;

            case 3: getFragmentManager().beginTransaction().replace(R.id.container,new Fragment_Favourites()).addToBackStack(null).commit();
                break;

            case 4: getFragmentManager().beginTransaction().replace(R.id.container,new Fragment_Groceries()).addToBackStack(null).commit();
                break;

            case 5: getFragmentManager().beginTransaction().replace(R.id.container,new Fragment_About()).addToBackStack(null).commit();
                break;

            default:getFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
                break;
        }
        mDrawerLayout.closeDrawer(mDrawer);
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
            getFragmentManager().beginTransaction().replace(R.id.container,new Fragment_About()).addToBackStack(null).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearBackStack()
    {
        while(getFragmentManager().getBackStackEntryCount()!=0)
            getFragmentManager().popBackStackImmediate();
    }


    @Override
    public void onBackPressed()
    {
        // Catch back action and pops from backstack
        // (if you called previously to addToBackStack() in your transaction)
        int stackCount=getFragmentManager().getBackStackEntryCount();
        if (stackCount > 0 &&(getFragmentManager().getBackStackEntryAt(0) != getFragmentManager().getBackStackEntryAt(stackCount-1))) {
            getFragmentManager().popBackStack();
        }
        // Default action on back pressed
        else super.onBackPressed();
    }
}
