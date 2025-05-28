package com.example.tp6;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntityResponse {
    private List<Entity> results;

    public List<Entity> getData() {
        return results;
    }

    public void setData(List<Entity> data) {
        this.results = data;
    }
}
