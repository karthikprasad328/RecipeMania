package app.com.example.karthik.recipemania;

/**
 * Created by Karthik on 4/19/2015.
 */

import retrofit.http.GET;
import retrofit.http.Query;

public interface WebService {

    @GET("/api/search?key=94bb6246be7efb1ed390e94c71e2d389")
    RecipeList getRecipeList(@Query("page") String pageno);

    @GET("/api/search?key=94bb6246be7efb1ed390e94c71e2d389")
    RecipeList getRecipeQueryList(@Query("q") String query, @Query("page") String pageno);


    @GET("/api/get?key=94bb6246be7efb1ed390e94c71e2d389")
    RecipeElement getFullRecipe(@Query("rId") String id);


}