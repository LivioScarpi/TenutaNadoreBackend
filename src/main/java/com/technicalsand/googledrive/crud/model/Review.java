package com.technicalsand.googledrive.crud.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
 
	private static final long serialVersionUID = -2343243243242432341L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO, generator = "exception_seq_generator")
    // @SequenceGenerator(name = "exception_seq_generator", sequenceName = "exception_seq", allocationSize=1)
    // @Column(name="exception_id")
	// private long id;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private long id;

	@Column(name = "username")
	private String username;
 
	@Column(name = "review")
	private String review;

    @Column(name = "rating")
	private Integer rating;

    @Column(name = "date")
	private Date date;
 
    @Column(name = "images")
	private String images;

	public Review() {
	}
 
	public Review(String username, String review) {
		this.username = username;
		this.review = review;
        this.date = new Date();
        this.rating = 5;
	}

    public Review(String username, String review, String images) {
		this.username = username;
		this.review = review;
        this.date = new Date();
        this.rating = 5;
		this.images = images;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String toString() {
        return String.format("Review[images='%s', review='%s', images='%s']", username, review, images);
    }
}