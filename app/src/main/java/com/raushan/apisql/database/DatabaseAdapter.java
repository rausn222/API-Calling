package com.raushan.apisql.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.raushan.apisql.Constants;
import com.raushan.apisql.diallist;
import com.raushan.apisql.diallist1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajit Kumar Baral on 9/1/2015.
 */
public class DatabaseAdapter {

    private DatabaseHelper databaseHelper;
    private Context context;
    List<diallist> movieList;

    public DatabaseAdapter(Context context,List<diallist> movieList){
        databaseHelper = new DatabaseHelper(context);
        this.movieList = movieList;
    }


    public void insert(List<diallist> greeting) {
        for (int i=0;i<greeting.size();i++)
        {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_ID, greeting.get(i).getPicture().getLarge());
            contentValues.put(Constants.COLUMN_CONTENT, greeting.get(i).getName().getTitle() +" "+greeting.get(i).getName().getFirst()+" "+greeting.get(i).getName().getLast());
            contentValues.put(Constants.COLUMN_CONTENT1, "not selected");
            db.insert(Constants.DATABASE_TABLE_NAME, null, contentValues);
        }
    }

    public int updateacc(diallist1 greeting) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_ID, greeting.getImage());
        contentValues.put(Constants.COLUMN_CONTENT, greeting.getName());
        contentValues.put(Constants.COLUMN_CONTENT1, "selected");
        return db.update(Constants.DATABASE_TABLE_NAME, contentValues, Constants.COLUMN_KEY_ID+" = "+greeting.getId(), null);
    }

    public int updaterej(diallist1 greeting) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_ID, greeting.getImage());
        contentValues.put(Constants.COLUMN_CONTENT, greeting.getName());
        contentValues.put(Constants.COLUMN_CONTENT1, "rejected");
        return db.update(Constants.DATABASE_TABLE_NAME, contentValues, Constants.COLUMN_KEY_ID+" = "+greeting.getId(), null);
    }

    public List<diallist1> getAll() {
        List<diallist1> greetingList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] columns = {Constants.COLUMN_KEY_ID, Constants.COLUMN_ID, Constants.COLUMN_CONTENT, Constants.COLUMN_CONTENT1};

        Cursor cursors = db.query(Constants.DATABASE_TABLE_NAME, columns, null, null, null, null, null);
        while(cursors.moveToNext()){
            int keyId = cursors.getInt(cursors.getColumnIndex(Constants.COLUMN_KEY_ID));
            String  greetingId = cursors.getString(cursors.getColumnIndex(Constants.COLUMN_ID));
            String content = cursors.getString(cursors.getColumnIndex(Constants.COLUMN_CONTENT));
            String content1 = cursors.getString(cursors.getColumnIndex(Constants.COLUMN_CONTENT1));
            diallist1 greeting = new diallist1( greetingId, content, keyId, content1);
            greetingList.add(greeting);
        }
        return greetingList;
    }
}
