package com.janosgyerik.kata.tripservice.trip;

import com.janosgyerik.kata.tripservice.exception.UserNotLoggedInException;
import com.janosgyerik.kata.tripservice.user.User;
import com.janosgyerik.kata.tripservice.user.UserSession;

import java.util.Collections;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return findTripsByUser(user);
            }
        }
        return Collections.emptyList();
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
