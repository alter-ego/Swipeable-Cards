package com.andtinder.demo;

import com.andtinder.model.BaseCardModel;

public class DifferentCard extends BaseCardModel {

    private String title;

    private String text;

    public DifferentCard(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
