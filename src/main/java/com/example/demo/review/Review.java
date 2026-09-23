package com.example.demo.review;

import com.example.demo.Booking.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Review {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer reviewId;

	    private String review;

	    private Integer rating;

	    @OneToOne
	    @JoinColumn(name = "booking_id")
	    private Booking booking;

		public Review() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Review(Integer reviewId, String review, Integer rating, Booking booking) {
			super();
			this.reviewId = reviewId;
			this.review = review;
			this.rating = rating;
			this.booking = booking;
		}

		public Integer getReviewId() {
			return reviewId;
		}

		public void setReviewId(Integer reviewId) {
			this.reviewId = reviewId;
		}

		public String getReview() {
			return review;
		}

		public void setReview(String review) {
			this.review = review;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public Booking getBooking() {
			return booking;
		}

		public void setBooking(Booking booking) {
			this.booking = booking;
		}
	    
}
