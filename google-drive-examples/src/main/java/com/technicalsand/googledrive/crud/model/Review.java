package com.technicalsand.googledrive.crud.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
 
	private static final long serialVersionUID = -2343243243242432341L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
	@Column(name = "username")
	private String username;
 
	@Column(name = "review")
	private String review;
 
    @Column(name = "images")
	private String images;

	protected Review() {
	}
 
	public Review(String username, String review) {
		this.username = username;
		this.review = review;
	}

    public Review(String username, String review, String images) {
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
        return String.format("Review[images='%s', review='%s', images='%s']", username, review, images);
    }
}