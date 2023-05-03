package com.team2.gogame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "goDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_GAME = "games";
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String MOVES = "moves";
    private static final String BOARD = "board";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_GAME + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + DATE;
        sqlCreate += " text, " + MOVES;
        sqlCreate += " text, " + BOARD + " text )";
        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_GAME);
        // Re-create tables
        onCreate(db);
    }

    // Insert Game into database
    public void insert(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_GAME;
        sqlInsert += " values( null, '" + game.getDate();
        sqlInsert += "', '" + game.getMoves() ;
        sqlInsert += "', '" + game.getBoard() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }

    // Delete Game from database
    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GAME + " WHERE " + ID + " = " + id);
        db.close();
    }

    // Update Game record
    public void updateById(int id, String date, String moves, String board) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlString = "UPDATE " + TABLE_GAME + " SET " + DATE + " = " + "\"" + date + "\"" + " WHERE " + ID + " = " + id;
        db.execSQL(sqlString);
        sqlString = "UPDATE " + TABLE_GAME + " SET " + MOVES + " = " + "\"" + moves + "\"" + " WHERE " + ID + " = " + id;
        db.execSQL(sqlString);
        sqlString = "UPDATE " + TABLE_GAME + " SET " + BOARD + " = " + "\"" + board + "\"" + " WHERE " + ID + " = " + id;
        db.execSQL(sqlString);
        db.close();
    }

    // Return list of all candies
    public ArrayList<Game> selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TABLE_GAME;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<Game> games = new ArrayList<Game>();
        while (cursor.moveToNext()) {
            Game currentGame = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            games.add(currentGame);
            Log.w("dbm", currentGame.getId() + "");
        }
        db.close();
        return games;
    }

    // Select Game by id
    public Game selectById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TABLE_GAME;
        sqlQuery += " where " + ID + " = " + id;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Game game = null;
        if (cursor.moveToFirst())
            game = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return game;
    }

}



