package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripService createTripService(UserSession userSession) {
        return new TripService(userSession);
    }

    private TripService createTripService(UserSession userSession, TripDAO tripDAO) {
        return new TripService(userSession, tripDAO);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void test_getTripsByUser_should_throw_if_user_not_logged() {
        TripService tripService = createTripService(mock(UserSession.class));
        tripService.getTripsByUser(null);
    }

    @Test
    public void test_getTripsByUser_should_find_nothing_if_user_has_no_friends() {
        User loggedUser = new User();

        UserSession mockUserSession = mock(UserSession.class);
        when(mockUserSession.getLoggedUser()).thenReturn(loggedUser);

        TripService tripService = createTripService(mockUserSession);
        assertEquals(Collections.emptyList(), tripService.getTripsByUser(new User()));
    }

    @Test
    public void test_getTripsByUser_should_find_nothing_if_loggedUser_not_a_friend() {
        User loggedUser = new User();

        UserSession mockUserSession = mock(UserSession.class);
        when(mockUserSession.getLoggedUser()).thenReturn(loggedUser);

        TripService tripService = createTripService(mockUserSession);
        User user = new User();
        user.addFriend(new User());
        assertEquals(Collections.emptyList(), tripService.getTripsByUser(user));
    }

    @Test
    public void test_getTripsByUser_should_find_trips_if_loggedUser_is_a_friend() {
        User loggedUser = new User();

        UserSession mockUserSession = mock(UserSession.class);
        when(mockUserSession.getLoggedUser()).thenReturn(loggedUser);

        TripDAO mockTripDAO = mock(TripDAO.class);
        TripService tripService = createTripService(mockUserSession, mockTripDAO);
        User user = new User();
        user.addFriend(loggedUser);
        assertEquals(user.trips(), tripService.getTripsByUser(user));
    }
}
