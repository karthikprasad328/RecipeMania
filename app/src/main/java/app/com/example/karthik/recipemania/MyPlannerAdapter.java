package app.com.example.karthik.recipemania;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 4/22/2015.
 */
public class MyPlannerAdapter extends RecyclerView.Adapter<MyPlannerAdapter.ViewHolder> {

    private List<Map<String,?>> plannerList=new ArrayList<Map<String,?>>();

    public MyPlannerAdapter(List<Map<String,?>> plannerList){this.plannerList=plannerList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_planner, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String,?> entry=plannerList.get(position);
        holder.bindPlannerData(entry);
    }

    @Override
    public int getItemCount() {
        return plannerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titleText;
        private TextView dateText;

        public ViewHolder(final View itemView)
        {
         super(itemView);
            titleText=(TextView)itemView.findViewById(R.id.titleText);
            dateText=(TextView) itemView.findViewById(R.id.dateText);

        }

        public void bindPlannerData(Map<String,?> item)
        {
            titleText.setText((String)item.get("title"));
            dateText.setText((String)item.get("date"));
        }
    }
}
