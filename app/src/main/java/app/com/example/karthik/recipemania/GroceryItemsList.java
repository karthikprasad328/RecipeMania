package app.com.example.karthik.recipemania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 4/21/2015.
 */
public class GroceryItemsList {

    List<Map<String,?>> groceryList=new ArrayList<Map<String,?>>();

    public List<Map<String,?>> getGroceryList(){return groceryList;}

    public int getSize(){return groceryList.size();}

    public GroceryItemsList(List<String> list1)
    {
        for(int i=0;i<list1.size();i++)
        {
            HashMap item=new HashMap();
            item.put("title",list1.get(i));
            item.put("selection",false);
            groceryList.add(item);
        }
    }

    public HashMap getItem(int i){
        if (i >=0 && i < groceryList.size()){
            return (HashMap) groceryList.get(i);
        } else return null;
    }
//
//    public void deleteGroceries()
//    {
//        for(int i=groceryList.size()-1;i>=0;i++)
//        {
//            HashMap entry=(HashMap)groceryList.get(i);
//            if (entry.get("selection")==true) {
//                groceryList.remove(entry);
//                deleteGroceries();
//            }
//        }
//    }
}
