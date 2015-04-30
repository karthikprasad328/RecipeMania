package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Karthik on 4/21/2015.
 */
public class Fragment_Groceries extends Fragment{
    RecyclerView recyclerView;

    public Fragment_Groceries()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_grocerylist, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("My Grocery List");

        recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        final RecipeDatabase rdb=RecipeDatabase.getInstance(getActivity().getApplicationContext());
        List<String> groceries=rdb.getGroceries();
        final GroceryItemsList groceryItemsList=new GroceryItemsList(groceries);
        final MyGroceryListAdapter myGroceryListAdapter=new MyGroceryListAdapter(groceryItemsList.getGroceryList());
        recyclerView.setAdapter(myGroceryListAdapter);

        //Button removeButton=(Button) rootView.findViewById(R.id.removeButton);
        final ImageButton removeButton=(ImageButton)rootView.findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=myGroceryListAdapter.getItemCount()-1;i>=0;--i)
                {
                   // HashMap<String,Boolean> entry=(HashMap<String,Boolean>)groceryItemsList.getItem(i);
                    HashMap entry=(HashMap)groceryItemsList.getItem(i);
                    Boolean b=(Boolean)entry.get("selection");
                    YoYo.with(Techniques.Wobble)
                            .duration(700)
                            .playOn(removeButton);
                    if(b==true){
                        rdb.deleteGrocery((String)groceryItemsList.getItem(i).get("title"));
                        groceryItemsList.groceryList.remove(i);
                        myGroceryListAdapter.notifyItemRemoved(i);


                    }
                }
            }
        });


        return rootView;
    }
}
