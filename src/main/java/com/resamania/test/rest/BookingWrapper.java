package com.resamania.test.rest;

import java.util.List;

/**
 * created to match resamania service response
 * 
 * @author Mouhamed.tall
 *
 */
public class BookingWrapper {

	private List<Booking> resultList;

	public List<Booking> getResultList() {
		return resultList;
	}

	public void setResultList(List<Booking> resultList) {
		this.resultList = resultList;
	}

}
