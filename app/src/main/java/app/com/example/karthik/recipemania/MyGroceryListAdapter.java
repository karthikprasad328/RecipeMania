package app.com.example.karthik.recipemania;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 4/21/2015.
 */
public class MyGroceryListAdapter extends RecyclerView.Adapter<MyGroceryListAdapter.ViewHolder> {

    private List<Map<String,?>> groceryList;

    OnItemClickListener mItemClickListener;

    public MyGroceryListAdapter(List<Map<String,?>> mDataset){groceryList=mDataset;}


    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView vTitle;
        public CheckBox vCheckBox;

        public ViewHolder(final View itemView)
        {
            super(itemView);
            vTitle = (TextView) itemView.findViewById(R.id.groceryText);
            vCheckBox=(CheckBox)itemView.findViewById(R.id.checkbox);

            vCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap movie = (HashMap) groceryList.get(getPosition());
                    movie.put("selection", vCheckBox.isChecked());
                }
            });
        }

        public void bindGroceryData(Map<String,?> item)
        {
            vTitle.setText((String)item.get("title"));
            if(item.get("selection")!=null)
                vCheckBox.setChecked((Boolean)item.get("selection"));
        }

    }


    @Override
    public MyGroceryListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_grocerylist, viewGroup, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyGroceryListAdapter.ViewHolder viewHolder, int i) {
        Map<String,?> entry=groceryList.get(i);
        viewHolder.bindGroceryData(entry);
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    public interface OnItemClickListener{

    }

    public void setOnClickListener(final OnItemClickListener mItemClickListener)
    {
        this.mItemClickListener=mItemClickListener;
    }
}
