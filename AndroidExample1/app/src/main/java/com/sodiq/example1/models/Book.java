package com.sodiq.example1.models;

import com.orm.SugarRecord;

/**
 * Created by mohamadsodiq on 10/27/15.
 */
public class Book extends SugarRecord{
   private String title;
   private String edition;

    public Book() {
    }

    public Book(String title, String edition) {
        this.title = title;
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return title + "id: " + id;

    }

}
