package app.com.example.karthik.recipemania;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 4/20/2015.
 */
public class RecipeDatabase extends SQLiteOpenHelper {

    public static RecipeDatabase instance;
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RecipeManiaDatabase";

    // Table Names
    private static final String TABLE_PLANNER = "planner";
    private static final String TABLE_FAVOURITES = "favourites";
    private static final String TABLE_GROCERIES = "groceries";

    //planner column names
    private static final String KEY_ID="id";
    private static final String KEY_TITLE="title";

    //creating table favourites
    String CREATE_FAVOURITES_TABLE = "CREATE TABLE " + TABLE_FAVOURITES + "("
            + KEY_ID + " TEXT PRIMARY KEY," + KEY_TITLE + " TEXT"+ ")";


    //getInstance creates a singleton class
    public static synchronized RecipeDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new RecipeDatabase(context.getApplicationContext());
        }
        return instance;

    }


    public RecipeDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVOURITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITES);

        // Create tables again
        onCreate(db);
    }

    //Add a favourites item
    public void addFavourite(RecipeDbItem1 recipeDbItem1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,recipeDbItem1.getID());
        values.put(KEY_TITLE,recipeDbItem1.getTitle());
        //insert a row
        db.insert(TABLE_FAVOURITES,null,values);
        db.close();
        System.out.println("\n Inserted into Favourites Table: "+recipeDbItem1.getID()+" "+recipeDbItem1.getTitle());
    }

    //get a favourite item
    public RecipeDbItem1 getFavourite(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVOURITES, new String[] { KEY_ID,
                        KEY_TITLE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        RecipeDbItem1 recipeDbItem1=new RecipeDbItem1(cursor.getString(0),
                cursor.getString(1));
        return recipeDbItem1;
    }

    public List<RecipeDbItem1> getAllFavourites()
    {
        List<RecipeDbItem1> recipeDbList=new ArrayList<RecipeDbItem1>();
        String selectQuery = "SELECT  * FROM " + TABLE_FAVOURITES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeDbItem1 recipeDbItem1=new RecipeDbItem1();
                recipeDbItem1.setID(cursor.getString(0));
                recipeDbItem1.setTitle(cursor.getString(1));

                // Adding contact to list
                recipeDbList.add(recipeDbItem1);
            } while (cursor.moveToNext());
        }
        // return contact list
        return recipeDbList;
    }

    // Deleting single favourite
    public void deleteFavourite(RecipeDbItem1 recipeDbItem1) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVOURITES, KEY_ID + " = ?",
                new String[] { String.valueOf(recipeDbItem1.getID()) });
        db.close();
    }


}
