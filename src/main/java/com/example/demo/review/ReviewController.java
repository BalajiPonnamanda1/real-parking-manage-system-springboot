package com.example.demo.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Booking.Booking;
import com.example.demo.Booking.BookingRepository;
import com.example.demo.CustomerData.Customer;
import com.example.demo.PartnerData.Partner;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	BookingRepository repo;
	
	@Autowired
	reviewService ser;

	
// default review page	
	@GetMapping("/")
	public String review()
	{
		return "Review/review";
	}
	
// to add review page in ui
	@GetMapping("/add")
	public String AddReview()
	{
		return "Review/AddReview";
	}
	
// to check the customer
	@GetMapping("/check")
	public String Added(HttpSession session, Model model)
	{
		Customer cust = (Customer) session.getAttribute("cust");
		List<Booking> cus= repo.findByCustomerAndOtpIsNull(cust);
		model.addAttribute("data", cus);
		return "Review/Land";
	}
	
// to add the review for respective user
	@GetMapping("/addReview")
	public String AddRev(@RequestParam("id") Integer bookingId, Model model)
	{
		model.addAttribute("data", bookingId);
		return "Review/feedBack";
	}
	
// to store in database
	@PostMapping("/added")
	public String saveReview(@RequestParam Integer bookingId,
	                         @RequestParam String review,
	                         @RequestParam Integer rating, Model model) {
		Booking bk = repo.getById(bookingId);
		Review r = new Review();
		r.setBooking(bk);
		r.setRating(rating);
		r.setReview(review);
		
		 r = ser.saveData(r);
		if(r == null)
			return "Review/feedBack";
		else
		{
			
			model.addAttribute("msg", "your review is added sucessfully");
			return "Review/msg";
		}

	}
	
// to display all reviews
	@GetMapping("/allReviews")
	public String getData(Model model, HttpSession session)
	{
		Customer cust = (Customer) session.getAttribute("cust");
		List<Review> al = ser.getAll(cust);
		model.addAttribute("data", al);
		return "Review/AllReview";
	}

// to delete the reviews
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer reviewId, Model model)
	{
		ser.delete(reviewId);
		model.addAttribute("msg", "your review is deleted sucessfully");
		return "Review/msg";
	}
	
	// Open update page
	@GetMapping("/update/{id}")
	public String updateReviewPage(@PathVariable("id") Integer reviewId,
	                               Model model) {

	    Review review = ser.getReview(reviewId);

	    model.addAttribute("data", review);

	    return "Review/UpdateReview";
	}


	// Update review in database
	@PostMapping("/updated")
	public String updateReview(
	        @RequestParam("reviewId") Integer reviewId,
	        @RequestParam("review") String reviewText,
	        @RequestParam("rating") Integer rating,
	        Model model) {

	    Review review = ser.getReview(reviewId);

	    review.setReview(reviewText);
	    review.setRating(rating);

	    ser.saveData(review);

	    model.addAttribute(
	            "msg",
	            "Your review is updated successfully"
	    );

	    return "Review/msg";
	}


}
