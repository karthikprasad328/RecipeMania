<<<<<<< HEAD
package app.com.example.karthik.recipemania;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/*
 * Created by Karthik on 4/19/2015.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.RecipeViewHolder> {
   // private final Context mcontext;

    OnItemClickListener mItemClickListener;

    private final RecipeList recipeList;

    MyRecycleViewAdapter(RecipeList myDataSet) {
        recipeList = myDataSet;
    }

    //create a viewholder
    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private TextView vTitle;

        private ImageView vImage;

        private ImageView vMenu;

        public RecipeViewHolder(final View itemView){
            super(itemView);

            vImage=(ImageView) itemView.findViewById(R.id.recipelistimage);

            vTitle=(TextView)itemView.findViewById(R.id.title);

            vMenu=(ImageView) itemView.findViewById(R.id.menu_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                      //  mItemClickListener.onItemLongClick(v,getPosition());
                    }

                }
            });

//

        }

        public void bindRecipeData(RecipeListItem recipeListitem)
        {
            vTitle.setText(recipeListitem.getTitle());

            Picasso.with(this.vImage.getContext())
                    .load(recipeListitem.getImage_url())
                    .into(vImage);
        }



    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recipelist, viewGroup, false);
        RecipeViewHolder recipeViewHolder=new RecipeViewHolder(v);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder viewHolder, int i) {
        RecipeListItem recipeListItem=recipeList.getRecipes().get(i);
        viewHolder.bindRecipeData(recipeListItem);

    }



    @Override
    public int getItemCount() {
        return Integer.parseInt(recipeList.getCount());
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
       // public void onItemLongClick(View view, int position);
//       public void onOverFlowMenuClick(View view, final int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}
=======
package app.com.example.karthik.recipemania;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/*
 * Created by Karthik on 4/19/2015.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.RecipeViewHolder> {
   // private final Context mcontext;

    OnItemClickListener mItemClickListener;

    private final RecipeList recipeList;

    MyRecycleViewAdapter(RecipeList myDataSet) {
        recipeList = myDataSet;
    }

    //create a viewholder
    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private TextView vTitle;

        private ImageView vImage;

        private ImageView vMenu;

        public RecipeViewHolder(final View itemView){
            super(itemView);

            vImage=(ImageView) itemView.findViewById(R.id.recipelistimage);

            vTitle=(TextView)itemView.findViewById(R.id.title);

            vMenu=(ImageView) itemView.findViewById(R.id.menu_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                      //  mItemClickListener.onItemLongClick(v,getPosition());
                    }

                }
            });

            vMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener!=null)
                        mItemClickListener.onOverFlowMenuClick(v,getPosition());
                }
            });


        }

        public void bindRecipeData(RecipeListItem recipeListitem)
        {
            vTitle.setText(recipeListitem.getTitle());

            Picasso.with(this.vImage.getContext())
                    .load(recipeListitem.getImage_url())
                    .into(vImage);
        }



    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recipelist, viewGroup, false);
        RecipeViewHolder recipeViewHolder=new RecipeViewHolder(v);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder viewHolder, int i) {
        RecipeListItem recipeListItem=recipeList.getRecipes().get(i);
        viewHolder.bindRecipeData(recipeListItem);

    }



    @Override
    public int getItemCount() {
        return Integer.parseInt(recipeList.getCount());
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
       // public void onItemLongClick(View view, int position);
       public void onOverFlowMenuClick(View view, final int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}
>>>>>>> origin/master
