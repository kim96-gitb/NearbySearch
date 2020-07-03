package com.test.nearbysearch.model;

public class Store {
    private  String name;
    private  String vicinity;

    public Store() {
    }

    public Store(String name, String vicinity) {
        this.name = name;
        this.vicinity = vicinity;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
