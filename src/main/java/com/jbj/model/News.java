package com.jbj.model;

import java.util.Date;

public class News {
    private int id;
    private String title;
    private String link;
    private String image;
    private int like_count;
    private Date created_date;
    private int user_id;
    private int comment_count;

    public News() {
    }

    public News(int id, String title, String link, String image, int like_count, Date created_date, int user_id, int comment_count) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.image = image;
        this.like_count = like_count;
        this.created_date = created_date;
        this.user_id = user_id;
        this.comment_count = comment_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }
}
