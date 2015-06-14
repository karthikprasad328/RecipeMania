package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 4/22/2015.
 */
public class Fragment_Planner extends Fragment {

    private Cursor mCursor = null;
    private static final String[] COLS = new String[]
            { CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART,CalendarContract.Events.DESCRIPTION};

   public  Fragment_Planner()
    {
        mCursor=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.recyclerview_planner,container,false);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.cardList);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("My Planned Events");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        List<Map<String,?>> plannerList=new ArrayList<Map<String,?>>();


        mCursor=null;
        mCursor = getActivity().getContentResolver().query(
                CalendarContract.Events.CONTENT_URI, COLS, null, null, null);

        String title;
        Long start=0L;
        String description;
        Format df = DateFormat.getDateFormat(getActivity());
        Format tf = DateFormat.getTimeFormat(getActivity());
        System.out.println("\n in Planner");
        if(mCursor.moveToFirst()&&mCursor!=null)
        {
            do {
                title = mCursor.getString(0);
                start = mCursor.getLong(1);
                description=mCursor.getString(2);
                if(description.compareTo("RECIPEMANIA")==0) {
                    System.out.println("\nEvent: " + title + "on" + df.format(start));
                    HashMap entry=new HashMap();
                    entry.put("title",title);
                    entry.put("date",df.format(start));
                    plannerList.add(entry);
                }
            }while(mCursor.moveToNext());
        }
        if(plannerList.size()>0)
        {
            MyPlannerAdapter myPlannerAdapter=new MyPlannerAdapter(plannerList);
            recyclerView.setAdapter(myPlannerAdapter);
        }
        return rootView;
    }

}
