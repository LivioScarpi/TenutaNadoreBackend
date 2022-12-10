package com.technicalsand.googledrive.crud.model;

public class ReviewUI {

    private String username;
    private String review;
    private String images;
    
    protected ReviewUI() {
    }

    public ReviewUI(String username, String review) {
		this.username = username;
		this.review = review;
	}

    public ReviewUI(String username, String review, String images) {
		this.username = username;
		this.review = review;
		this.images = images;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String toString() {
        return String.format("ReviewUI[images='%s', review='%s', images='%s']", username, review, images);
    }
}