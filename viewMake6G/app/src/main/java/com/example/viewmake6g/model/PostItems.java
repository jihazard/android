package com.example.viewmake6g.model;

import java.util.List;

public class PostItems {

    private List<PostItem> items ;

    public PostItems(List<PostItem> items) {
        this.items = items;
    }

    public List<PostItem> getItems() {
        return items;
    }

    public void setItems(List<PostItem> items) {
        this.items = items;
    }
}
