package com.example.grocerystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.grocerystore.R;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Store;
import com.example.grocerystore.util.Util;

import java.util.ArrayList;
import java.util.List;

import static com.example.grocerystore.util.Util.KEY_ID;
import static com.example.grocerystore.util.Util.TABLE_NAME_ITEM;
import static com.example.grocerystore.util.Util.TABLE_NAME_STORE;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

//    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create First Table Store
        String CREATE_STORE_TABLE = "CREATE TABLE "+ TABLE_NAME_STORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT)";

        db.execSQL(CREATE_STORE_TABLE);

        // Create Second Table Item
        //* add foreign key constraint and field to store
        String CREATE_ITEM_TABLE = "CREATE TABLE "+Util.TABLE_NAME_ITEM + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.FOREIGN_KEY_ID + " INTEGER, "
                + Util.KEY_NAME + " TEXT, "+
                "FOREIGN KEY("+Util.FOREIGN_KEY_ID+") REFERENCES "+ TABLE_NAME_STORE+ "( "+KEY_ID +" ))";

        // add constraint

        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);
    }


    // todo HANDLE STORE

    public void addStore(Store store){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Util.KEY_ID, contact.getId());
        values.put(Util.KEY_NAME, store.getName());
        //values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber(), values);
        //values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(TABLE_NAME_STORE, null, values);
        Log.d("test", "addContact: "+"item added");
        db.close();

    }

    // not tested
    public boolean editStore(Store store){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, store.getName());

        db.update(TABLE_NAME_STORE, values, KEY_ID + " = ?", new String[]{String.valueOf(store.getId())});

        return true;
    }

    public List<Store> getAllStores(){
        List<Store> storeList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM "+ TABLE_NAME_STORE;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {

            do{
                Store store = new Store();

                store.setId(Integer.parseInt(cursor.getString(0)));
                store.setName(cursor.getString(1));
                //store.setPhoneNumber(cursor.getString(2));

                storeList.add(store);
            }while(cursor.moveToNext());
        }
        db.close();
        return storeList;
    }

    //Delete Method
    public void deleteContact(Store store)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME_STORE, KEY_ID + "=?", new String[]{String.valueOf(store.getId())});
        //        db.delete(TABLE_NAME, KEY_ID + "=" + new String[]{String.valueOf(contact.getId())}, null);
        db.close();
    }




    //TODO HANDLE ITEMS

    public List<Item> getAllItems(){
        List<Item> itemList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM "+ TABLE_NAME_ITEM;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {

            do{
                Item item = new Item();

                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setStoreId(Integer.parseInt(cursor.getString(1)));
                item.setName(cursor.getString(2));
                //foreign id

                //store.setPhoneNumber(cursor.getString(2));

                itemList.add(item);
            }while(cursor.moveToNext());
        }
        db.close();
        return itemList;
    }

    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();


        //Look need to get id position from store.

        ContentValues values = new ContentValues();
        values.put(Util.FOREIGN_KEY_ID, item.getStoreId());
        values.put(Util.KEY_NAME, item.getName());
        //values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber(), values);
        //values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(TABLE_NAME_ITEM, null, values);
        Log.d("test", "addContact: "+"item added");
        db.close();

    }

    // edit
    public boolean editItem(Item item){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, item.getName());

        db.update(TABLE_NAME_ITEM, values, KEY_ID + " = ?", new String[]{String.valueOf(item.getId())});

        return true;
    }

    //Delete Method
    public void deleteItem(Item item)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME_ITEM, KEY_ID + "=?", new String[]{String.valueOf(item.getId())});
        //        db.delete(TABLE_NAME, KEY_ID + "=" + new String[]{String.valueOf(contact.getId())}, null);
        db.close();
    }



}
