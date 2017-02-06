package com.resamania.test.business;

import java.util.Date;

/**
 * Form bean . Contains all information needed for form
 * 
 * @author Mouhamed.tall
 *
 */
public class BookingFormBean {

	private Date beginDate;
	private String supplier;
	private String room;
	private String activity;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}
