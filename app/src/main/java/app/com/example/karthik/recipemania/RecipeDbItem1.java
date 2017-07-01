package app.com.example.karthik.recipemania;

/**
 * Created by Karthik on 4/20/2015.
 */
class RecipeDbItem1 {
    String id;
    String title;

    //Empty constructor
    RecipeDbItem1() {

    }

    //constructor
    RecipeDbItem1(String id, String title) {
        this.id = id;
        this.title = title;
    }

    // getting ID
    String getID() {
        return this.id;
    }

    // setting id
    void setID(String id) {
        this.id = id;
    }

    // getting title
    public String getTitle() {
        return this.title;
    }

    // setting title
    public void setTitle(String title) {
        this.title = title;
    }
}
