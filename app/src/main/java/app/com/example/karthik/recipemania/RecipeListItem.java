package app.com.example.karthik.recipemania;

/**
 * Created by Karthik on 4/19/2015.
 */
public class RecipeListItem {
    private String publisher;
    private String f2f_url;
    private String title;
    private String source_url;
    private String recipe_id;
    private String image_url;
    private String social_rank;
    private String publisher_url;

    public RecipeListItem(RecipeElement recipeElement) {
        RecipeItem recipeItem = recipeElement.getRecipe();
        publisher = recipeItem.getPublisher();
        f2f_url = recipeItem.getF2f_url();
        title = recipeItem.getTitle();
        source_url = recipeItem.getSource_url();
        recipe_id = recipeItem.getRecipe_id();
        image_url = recipeItem.getImage_url();
        social_rank = recipeItem.getSocial_rank();
        publisher_url = recipeItem.getPublisher_url();
    }

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


}
