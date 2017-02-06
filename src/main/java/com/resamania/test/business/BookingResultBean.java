package com.resamania.test.business;

import com.resamania.test.rest.Activity;
import com.resamania.test.rest.Booking;
import com.resamania.test.rest.RestTemplateHelper;
import com.resamania.test.rest.Room;

public class BookingResultBean {

	private int id;
	private String beginDate;
	private String endDate;
	private int nbPlace;
	private int nbPlaceInUsed;
	private Room room;
	private Activity activity;
	private int coach;

	public BookingResultBean() {
	}

	/**
	 * Creates bean for jsp with booking information
	 * 
	 * @param booking
	 *            information from resamania service
	 * @param supplierId
	 *            supplier id
	 */
	public BookingResultBean(Booking booking, int supplierId) {
		this.id = booking.getId();
		this.beginDate = booking.getBeginDate();
		this.endDate = booking.getEndDate();
		this.nbPlace = booking.getNbPlace();
		this.nbPlaceInUsed = booking.getNbPlaceInUsed();

		this.room = (RestTemplateHelper.getRoomById(supplierId, booking.getRoomIdList().get(0)));

		this.activity = (RestTemplateHelper.getActivityById(supplierId, booking.getActivityIdList().get(0)));

		this.setCoach(booking.getCoachIdList().get(0));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public int getNbPlaceInUsed() {
		return nbPlaceInUsed;
	}

	public void setNbPlaceInUsed(int nbPlaceInUsed) {
		this.nbPlaceInUsed = nbPlaceInUsed;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getCoach() {
		return coach;
	}

	public void setCoach(int coach) {
		this.coach = coach;
	}

}
