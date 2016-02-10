package com.janosgyerik.kata.tripservice.trip;

import com.janosgyerik.kata.tripservice.exception.UserNotLoggedInException;
import com.janosgyerik.kata.tripservice.user.User;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TripServiceTest {

    private TripService createTripService(User user) {
        return createTripService(user, null);
    }

    private TripService createTripService(User user, List<Trip> trips) {
        return new TripService() {
            @Override
            protected User getLoggedUser() {
                return user;
            }

            @Override
            protected List<Trip> findTripsByUser(User user) {
                return trips;
            }
        };
    }

    @Test(expected = UserNotLoggedInException.class)
    public void test_getTripsByUser_should_throw_if_user_not_logged() {
        TripService tripService = createTripService(null);
        tripService.getTripsByUser(null);
    }

    @Test
    public void test_getTripsByUser_should_find_nothing_if_user_has_no_friends() {
        TripService tripService = createTripService(new User());
        assertEquals(Collections.emptyList(), tripService.getTripsByUser(new User()));
    }

    @Test
    public void test_getTripsByUser_should_find_nothing_if_loggedUser_is_not_a_friend() {
        TripService tripService = createTripService(new User());

        User user = new User();
        user.addFriend(new User());
        assertEquals(Collections.emptyList(), tripService.getTripsByUser(user));
    }

    @Test
    public void test_getTripsByUser_should_find_trips_if_loggedUser_is_a_friend() {
        User loggedUser = new User();
        TripService tripService = createTripService(loggedUser, Collections.emptyList());

        User user = new User();
        user.addFriend(loggedUser);
        assertEquals(user.trips(), tripService.getTripsByUser(user));
    }
}
