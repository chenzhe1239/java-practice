package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private final UserSession userSession;
	private final TripDAO tripDAO;

	static class TripDAOWrapper extends TripDAO {
		@Override
		public List<Trip> findTripsByUser(User user) {
			return TripDAO.findTripsByUser_deprecated(user);
		}
	}

	public TripService() {
		this(UserSession.getInstance());
	}

	public TripService(UserSession userSession) {
		this(userSession, new TripDAOWrapper());
	}

	public TripService(UserSession userSession, TripDAO tripDAO) {
		this.userSession = userSession;
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = userSession.getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = tripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
}
