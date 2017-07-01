package app.com.example.karthik.recipemania;

import java.util.List;

/**
 * Created by Karthik on 4/19/2015.
 */
class RecipeItem {
    private String publisher;
    private String f2f_url;
    private String title;
    private String source_url;
    private String recipe_id;
    private String image_url;
    private String social_rank;
    private String publisher_url;
    private List<String> ingredients;

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setF2f_url(String f2f_url) {
        this.f2f_url = f2f_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSocial_rank(String social_rank) {
        this.social_rank = social_rank;
    }

    public void setPublisher_url(String publisher_url) {
        this.publisher_url = publisher_url;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getF2f_url() {
        return f2f_url;
    }

    public String getTitle() {
        return title;
    }

    public String getSource_url() {
        return source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getSocial_rank() {
        return social_rank;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
