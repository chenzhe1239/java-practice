package com.janosgyerik.kata.tripservice.trip;

import java.util.List;

import com.janosgyerik.kata.tripservice.exception.CollaboratorCallException;
import com.janosgyerik.kata.tripservice.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}
