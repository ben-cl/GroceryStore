package com.example.grocerystore.model;

import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private int storeId;
    private String name;

    public Item(){

    }

    public Item(int storeId, String name){
        this.storeId = storeId;
        this.name = name;
    }

    public Item(int id, int storeId, String name) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
    }

    public Item(String name){
        this.name = name;
    }




    public int getId(){ return id;}

    public int getStoreId() { return storeId;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
