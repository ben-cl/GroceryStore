package com.example.grocerystore.model;

import java.io.Serializable;

public class Store implements Serializable {

    private int id;
    private String name;

    public Store(){

    }

    public Store(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Store(String name){
        this.name = name;
    }


    public int getId(){ return id;}

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
