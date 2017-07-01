package app.com.example.karthik.recipemania;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 4/20/2015.
 */
class MyDrawerRecyclerViewAdapter extends RecyclerView.Adapter<MyDrawerRecyclerViewAdapter.ViewHolder> {
    private final Context mcontext;
    private final List<Map<String, ?>> mDataSet;
    private OnItemClickListener mItemClickListener;
    private int mCurrentPosition;

    MyDrawerRecyclerViewAdapter(Context mycontext, List<Map<String, ?>> myDataSet) {
        mcontext = mycontext;
        mDataSet = myDataSet;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vIcon;
        TextView vTitle;
        View vView;

        ViewHolder(View v) {
            super(v);
            vView = v;
            vIcon = (ImageView) v.findViewById(R.id.icon);
            vTitle = (TextView) v.findViewById(R.id.title);
            Typeface typeFace = Typeface.createFromAsset(mcontext.getAssets(), "fonts/Roboto-Regular.ttf");
            vTitle.setTypeface(typeFace);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                    mCurrentPosition = getPosition();
                    notifyDataSetChanged();
                }
            });

        }

        void bindDrawerData(Map<String, ?> item, int position) {
            if (position == mCurrentPosition)
                vView.setBackgroundColor(Color.LTGRAY);
            else
                vView.setBackgroundColor(0x00000000);
            if (vIcon != null)
                vIcon.setImageResource(((Integer) item.get("icon")).intValue());
            if (vTitle != null)
                vTitle.setText((String) item.get("title"));
        }

    }

    interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public MyDrawerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_list_item, parent, false);
        MyDrawerRecyclerViewAdapter.ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyDrawerRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Map<String, ?> item = mDataSet.get(position);
        viewHolder.bindDrawerData(item, position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
