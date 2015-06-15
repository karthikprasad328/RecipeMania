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
        item.put("icon", R.drawable.homeicon); item.put("title", "Home");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.popularicon); item.put("title", "Popular Recipes");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.fridgesearch); item.put("title", "Search By Ingredients");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.favouritesicon); item.put("title", "My Favourites");
        drawerList.add(item);
//        item = new HashMap();
//        item.put("icon", R.drawable.calendar); item.put("title", "Planner");
//        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.groceryicon); item.put("title", "Grocery List");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.help); item.put("title", "About");
        drawerList.add(item);
    }
}
