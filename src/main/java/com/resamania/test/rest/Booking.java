package com.resamania.test.rest;

import java.util.List;

public class Booking {
	private int id;
	private String beginDate;
	private String endDate;
	private int nbPlace;
	private int nbPlaceInUsed;
	private List<Integer> roomIdList;
	private List<Integer> activityIdList;
	private List<Integer> coachIdList;

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

	public List<Integer> getRoomIdList() {
		return roomIdList;
	}

	public void setRoomIdList(List<Integer> roomIdList) {
		this.roomIdList = roomIdList;
	}

	public List<Integer> getActivityIdList() {
		return activityIdList;
	}

	public void setActivityIdList(List<Integer> activityIdList) {
		this.activityIdList = activityIdList;
	}

	public void setCoachIdList(List<Integer> coachIdList) {
		this.coachIdList = coachIdList;
	}

	public List<Integer> getCoachIdList() {
		return coachIdList;
	}
}
