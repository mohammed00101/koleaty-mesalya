package mno.mohamed_youssef.myfaculty.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohamed Yossif on 28/09/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "images";

    // Table Names
    private String DB_TABLE = "table_image";

    // column names
    private static final String KEY_NAME = "image_name";
    private static final String KEY_IMAGE = "image_data";

    // Table create statement
    private String CREATE_TABLE_IMAGE ;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CREATE_TABLE_IMAGE="CREATE TABLE " + DB_TABLE + "("+
                KEY_NAME + " TEXT primary key," +
                KEY_IMAGE + " BLOB);";


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        // create new table
        onCreate(db);
    }



    public void addEntry( String name, byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(KEY_NAME,    name);
        cv.put(KEY_IMAGE,   image);
        database.insert( DB_TABLE, null, cv );
    }


    public boolean  isExists(String imageName){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor mCursor = database.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE"+KEY_NAME+"=?", new String[]{imageName});

        if (mCursor != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void delete(String imageName){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DB_TABLE, KEY_NAME + "=" +imageName, null);

    }
    public void setDB_TABLE(String DB_TABLE) {
        this.DB_TABLE = DB_TABLE;
    }
}