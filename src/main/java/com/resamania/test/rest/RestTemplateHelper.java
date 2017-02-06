package com.resamania.test.rest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestTemplateHelper {

	// TODO extract to properties
	private static final String MAIL = "test-api@intprod01.resamania.fr";
	private static final String PASSWORD = "anthony";

	/**
	 * Sets up header mandatory information: mail and password TODO: get infos
	 * from properties
	 * 
	 * @return
	 */
	private static HttpEntity<String> setUpHeader() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("mail", MAIL);
		headers.set("password", PASSWORD);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

	/**
	 * Calls resamania supplier service to retrieve suppliers according
	 * 
	 * @return
	 */
	public static SupplierWrapper retrieveSuppliers() {
		HttpEntity<String> setUpHeader = setUpHeader();
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		ResponseEntity<SupplierWrapper> exchange = restTemplate.exchange(ResamaniaServicesLinks.SUPPLIER.link,
				HttpMethod.GET, setUpHeader, SupplierWrapper.class, "10", "0");

		return exchange.getBody();
	}

	/**
	 * Calls resamania Activity Services to retrieve Activities of given
	 * supplier
	 * 
	 * @param supplierId
	 * @return
	 */
	public static ActivityWrapper retrieveActivities(String supplierId) {
		HttpEntity<String> setUpHeader = setUpHeader();
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		ResponseEntity<ActivityWrapper> exchange = restTemplate.exchange(ResamaniaServicesLinks.ACTIVITY.link,
				HttpMethod.GET, setUpHeader, ActivityWrapper.class, "10", "0", supplierId);

		return exchange.getBody();
	}

	/**
	 * Calls resamania room service to retrieve rooms of given supplier
	 * 
	 * @param supplierId
	 * @return
	 */
	public static RoomWrapper retrieveRooms(String supplierId) {
		HttpEntity<String> setUpHeader = setUpHeader();
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		ResponseEntity<RoomWrapper> exchange = restTemplate.exchange(ResamaniaServicesLinks.ROOM.link, HttpMethod.GET,
				setUpHeader, RoomWrapper.class, "10", "0", supplierId);

		return exchange.getBody();
	}

	/**
	 * Calls resamania booking service to retrieve bookings according to params
	 * 
	 * @param supplier
	 * @param beginDate
	 * @param room
	 * @param activity
	 * @return
	 */
	public static Booking[] retrieveBookings(Supplier supplier, Date beginDate, Room room, Activity activity) {
		HttpEntity<String> setUpHeader = setUpHeader();
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		// formatting dates before calling service
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String beginDateParam = dateFormatter.format(beginDate);

		LocalDateTime dateTime = LocalDateTime.ofInstant(beginDate.toInstant(), ZoneId.systemDefault()).plusDays(1);

		String endDateParam = dateFormatter.format(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));

		ResponseEntity<Booking[]> exchange = restTemplate.exchange(ResamaniaServicesLinks.BOOKING.link, HttpMethod.GET,
				setUpHeader, Booking[].class, supplier.getId(), beginDateParam, endDateParam, room.getId(),
				activity.getId());

		return exchange.getBody();
	}

	/**
	 * TODO: do it right with spring or call dedicated service
	 * 
	 * @param supplier
	 * @return
	 */
	public static Supplier getSupplier(String supplierName) {
		SupplierWrapper retrieveSuppliers = retrieveSuppliers();
		Stream<Supplier> filter = retrieveSuppliers.getResultList().stream()
				.filter(s -> s.getName().equals(supplierName));
		return filter.findFirst().get();

	}

	/**
	 * TODO: do it right with spring or call dedicated service
	 * 
	 * @param supplier
	 * @return
	 */
	public static Room getRoomByName(int supplierId, String roomName) {
		RoomWrapper retrieveSuppliers = retrieveRooms(Integer.toString(supplierId));
		return retrieveSuppliers.getResultList().stream().filter(s -> s.getName().equals(roomName)).findFirst().get();
	}

	/**
	 * TODO: do it right with spring or call dedicated service
	 * 
	 * @param supplierId
	 * @param roomId
	 * @return
	 */
	public static Room getRoomById(int supplierId, int roomId) {
		RoomWrapper retrieveSuppliers = retrieveRooms(Integer.toString(supplierId));
		return retrieveSuppliers.getResultList().stream().filter(s -> s.getId() == roomId).findFirst().get();
	}

	/**
	 * TODO: do it right with spring or call dedicated service
	 * 
	 * @param supplier
	 * @return
	 */
	public static Activity getActivityByName(int supplierId, String activityName) {
		ActivityWrapper retrieveSuppliers = retrieveActivities(Integer.toString(supplierId));
		return retrieveSuppliers.getResultList().stream().filter(s -> s.getName().equals(activityName)).findFirst()
				.get();
	}

	/**
	 * TODO: do it right with spring or call dedicated service
	 * 
	 * @param supplierId
	 * @param activityId
	 * @return
	 */
	public static Activity getActivityById(int supplierId, int activityId) {
		ActivityWrapper retrieveSuppliers = retrieveActivities(Integer.toString(supplierId));
		return retrieveSuppliers.getResultList().stream().filter(s -> s.getId() == activityId).findFirst().get();
	}

}
