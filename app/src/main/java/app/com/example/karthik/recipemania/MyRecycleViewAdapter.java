package app.com.example.karthik.recipemania;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
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
        private TextView vId;
        private ImageView vImage;

        public RecipeViewHolder(final View itemView){
            super(itemView);

            vImage=(ImageView) itemView.findViewById(R.id.recipelistimage);
            vId=(TextView)itemView.findViewById(R.id.recipeid);
            vTitle=(TextView)itemView.findViewById(R.id.title);
        }

        public void bindRecipeData(RecipeListItem recipeListitem)
        {
            vTitle.setText((String)recipeListitem.getTitle());
            vId.setText((String)recipeListitem.getRecipe_id());
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
        //public void onItemLongClick(View view, int position);
       // public void onOverFlowMenuClick(View view, final int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}
