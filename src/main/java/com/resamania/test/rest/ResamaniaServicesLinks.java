package com.resamania.test.rest;

public enum ResamaniaServicesLinks {

	REST_API_HOME("http://api.intprod01.resamania.fr/rest/resamania/v1/"), 
	SUPPLIER(REST_API_HOME.link + "supplier?size={size}&offset={offset}"), 
	ACTIVITY(REST_API_HOME.link + "activity?size={size}&offset={offset}&supplierId={supplierId}"),
	ROOM(REST_API_HOME.link + "room?size={size}&offset={offset}&supplierId={supplierId}"),
	BOOKING(REST_API_HOME.link + "booking?supplierId={supplierId}&begin={begin}&end={end}&roomId={roomId}&activityId={activityId}");

	String link;

	private ResamaniaServicesLinks(String link) {
		this.link = link;
	}
}
