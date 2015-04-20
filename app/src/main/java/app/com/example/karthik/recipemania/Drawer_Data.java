package app.com.example.karthik.recipemania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by Karthik on 4/19/2015.
 */
public class Drawer_Data {
    List<Map<String,?>> drawerList;

    public List<Map<String, ?>> getDrawerList() {
        return drawerList;
    }

    public int getSize(){
        return drawerList.size();
    }

    public HashMap getItem(int i){
        return (HashMap) drawerList.get(i);
    }

    public Drawer_Data() {
        HashMap item;
        drawerList = new ArrayList<Map<String, ?>>();
        item = new HashMap();
        item.put("icon", R.drawable.icon1); item.put("title", "Home");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.icon2); item.put("title", "Popular Recipes");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.icon3); item.put("title", "My Fridge");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.icon4); item.put("title", "My Favourites");
        drawerList.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.icon4); item.put("title", "Planner");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.icon4); item.put("title", "Grocery List");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.icon4); item.put("title", "Help");
        drawerList.add(item);








    }


}
