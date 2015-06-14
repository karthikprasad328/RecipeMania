package app.com.example.karthik.recipemania;

import java.util.List;

/**
 * Created by Karthik on 4/19/2015.
 */
public class RecipeList {

    private String count;
    private List<RecipeListItem> recipes;

    public void setCount(String count){this.count=count;}

    public void setRecipes(List<RecipeListItem> recipes){this.recipes=recipes;}

    public String getCount(){return count;}

    public void clear(){recipes.clear(); count="0";}

    public List<RecipeListItem> getRecipes(){return  recipes;}
}
