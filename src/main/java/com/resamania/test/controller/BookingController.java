package com.resamania.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resamania.test.business.BookingFormBean;
import com.resamania.test.business.BookingResultBean;
import com.resamania.test.rest.Activity;
import com.resamania.test.rest.ActivityWrapper;
import com.resamania.test.rest.Booking;
import com.resamania.test.rest.RestTemplateHelper;
import com.resamania.test.rest.Room;
import com.resamania.test.rest.RoomWrapper;
import com.resamania.test.rest.Supplier;
import com.resamania.test.rest.SupplierWrapper;

/**
 * COntroller for booking form
 * 
 * @author Mouhamed.tall<br>
 *         TODO : move business operations to business module
 */
@Controller
public class BookingController {

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	/**
	 * First call index of the page Loads form
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		SupplierWrapper retrieveSuppliers = RestTemplateHelper.retrieveSuppliers();
		model.addAttribute("suppliersList", retrieveSuppliers.getResultList());

		// TODO : replace by js call to get only suppliers' activities
		ActivityWrapper retrieveActivities = RestTemplateHelper.retrieveActivities("23");
		model.addAttribute("activitiesList", retrieveActivities.getResultList());

		// TODO : replace by js call to get only suppliers' rooms
		RoomWrapper retrieveRooms = RestTemplateHelper.retrieveRooms("23");
		model.addAttribute("roomsList", retrieveRooms.getResultList());

		model.addAttribute("book", new BookingFormBean());

		return "booking-form";
	}

	/**
	 * can be used to get suppliers' activity using id by js call
	 * 
	 * @param supplierId
	 * @return list of suppliers' activities
	 */
	@RequestMapping(value = "/activities/{supplierId}", method = RequestMethod.GET)
	public @ResponseBody List<Activity> getSupplierActivities(
			@RequestParam(value = "supplierId", defaultValue = "23") String supplierId) {
		ActivityWrapper retrieveSuppliers = RestTemplateHelper.retrieveActivities(supplierId);
		return retrieveSuppliers.getResultList();
	}

	/**
	 * can be used to get suppliers' Room using id by js call
	 * 
	 * @param supplierId
	 * @return list of suppliers' Room
	 */
	@RequestMapping(value = "/rooms/{supplierId}", method = RequestMethod.GET)
	public @ResponseBody List<Room> getSupplierRooms(
			@RequestParam(value = "supplierId", defaultValue = "23") String supplierId) {
		RoomWrapper retrieveSuppliers = RestTemplateHelper.retrieveRooms(supplierId);
		return retrieveSuppliers.getResultList();
	}

	/**
	 * Validation of form to search a booking<br>
	 * Calls resamania api to look for bookings that match with information
	 * given in form and displays result page
	 * 
	 * @param bookingRequest
	 * @param model
	 * @return booking-re
	 */
	@RequestMapping(value = "/searchMatchingBook", method = RequestMethod.POST)
	public String submitForm(@Valid BookingFormBean bookingRequest, Model model) {

		Supplier supplier = RestTemplateHelper.getSupplier(bookingRequest.getSupplier());
		Room room = RestTemplateHelper.getRoomByName(supplier.getId(), bookingRequest.getRoom());
		Activity activity = RestTemplateHelper.getActivityByName(supplier.getId(), bookingRequest.getActivity());

		Booking[] retrieveBookings = RestTemplateHelper.retrieveBookings(supplier, bookingRequest.getBeginDate(), room,
				activity);
		List<BookingResultBean> bookings = new ArrayList<>();
		// TODO: filter list if same room and activity
		if (retrieveBookings != null && retrieveBookings.length > 0) {
			// adding information to the list so that jsp will be able to read
			// it
			Arrays.stream(retrieveBookings)
					.forEach(result -> bookings.add(new BookingResultBean(result, supplier.getId())));
		}

		model.addAttribute("bookingsList", bookings);
		model.addAttribute("bookingResult", new BookingResultBean());

		return "booking-result";
	}

}
