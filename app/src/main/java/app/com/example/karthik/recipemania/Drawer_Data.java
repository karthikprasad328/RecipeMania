package app.com.example.karthik.recipemania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by Karthik on 4/19/2015.
 */
class Drawer_Data {
    private List<Map<String, ?>> drawerList;

    List<Map<String, ?>> getDrawerList() {
        return drawerList;
    }

    public int getSize() {
        return drawerList.size();
    }

    public HashMap getItem(int i) {
        return (HashMap) drawerList.get(i);
    }

    Drawer_Data() {
        HashMap item;
        drawerList = new ArrayList<>();
        item = new HashMap();
        item.put("icon", R.drawable.popularicon);
        item.put("title", "Popular Recipes");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.fridgesearch);
        item.put("title", "Search By Ingredients");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.favouritesicon);
        item.put("title", "My Favourites");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.groceryicon);
        item.put("title", "Grocery List");
        drawerList.add(item);
        item = new HashMap();
        item.put("icon", R.drawable.help);
        item.put("title", "About");
        drawerList.add(item);
    }
}
