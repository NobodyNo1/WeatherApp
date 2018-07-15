package com.example.weatherapp.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weatherapp.presenter.mainPage.model.CityWeather;

import java.util.ArrayList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wheather_db";
    private static final String TABLE_NAME = "city_wheather";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "city_name";
    private static final String COLUME_CITY_ID = "city_id";
    private static final String COLUME_TEMPETATURE = "temperature";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String CREATE_TABLE =
            "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUME_CITY_ID + " INTEGER,"
                    + COLUME_TEMPETATURE + " REAL,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void onUpgrade() {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,DATABASE_VERSION,DATABASE_VERSION);
    }

    public long insertNote(CityWeather data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUME_CITY_ID, data.getCityId());
        values.put(COLUMN_NAME, data.getCityName());
        values.put(COLUME_TEMPETATURE, data.getTemperature());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();

        return id;
    }



    public List<CityWeather> getAllCityWheather() {
        List<CityWeather> dataList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CityWeather cityWeather = new CityWeather();
                cityWeather.setCityId(cursor.getInt(cursor.getColumnIndex(COLUME_CITY_ID)));
                cityWeather.setCityName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                cityWeather.setTemperature(cursor.getDouble(cursor.getColumnIndex(COLUME_TEMPETATURE)));
                cityWeather.setTimestamp(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP)));

                dataList.add(cityWeather);
            } while (cursor.moveToNext());
        }
        db.close();

        return dataList;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        return count;
    }


}
