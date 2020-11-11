package com.ghiaconi.task_manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String slug;
    private String title;

    public Task(String slug, String title) {
        this.slug = slug;
        this.title = title;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
