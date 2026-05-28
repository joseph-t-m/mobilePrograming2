package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mycourses";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String DURATION_COL = "duration";
    private static final String DESCRIPTION_COL = "description";
    private static final String TRACKS_COL = "tracks";

    // Constructor for the Database Handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }//DBHandler

    // ................................Creating a database................................
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "
                + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + DURATION_COL + " TEXT, "
                + DESCRIPTION_COL + " TEXT, "
                + TRACKS_COL + " TEXT)";

        db.execSQL(query);
    }//onCreate

    // ................................Adding a new course................................
    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks) {

        SQLiteDatabase db = this.getWritableDatabase();

        // creating a variable for content values.
        ContentValues values = new ContentValues();

        // passing all values along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        // passing content values to the table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our database after adding database.
        db.close();
    }//addNewCourse

    // ................................Reading all the courses................................
    public ArrayList<CourseModal> readCourses() {
        // creating a database for reading the database.
        SQLiteDatabase db = this.getReadableDatabase();

        // creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        // moving the cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // adding the data from cursor to the array list.
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }//if
        // at last closing our cursor and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }//ArrayList

    // ................................Updating the course................................
    public void updateCourse(String originalCourseName, String courseName, String courseDescription,
                             String courseTracks, String courseDuration) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // passing all values along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        // calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    // ................................Deleting the course................................
    public void deleteCourse(String courseName) {

        // creating a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // calling a method to delete our course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }//onUpgrade

}//DBHandler