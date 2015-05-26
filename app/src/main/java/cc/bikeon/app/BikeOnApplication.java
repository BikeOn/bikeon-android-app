package cc.bikeon.app;

import android.app.Application;

import com.facebook.Session;

import cc.bikeon.app.services.local.location.LocationTracker;


/**
 * Created by cristian.rosa on 1/19/2015.
 */
public class BikeOnApplication extends Application {

    private final String TAG = "BikeOnApplication";

    private LocationTracker locationTracker;
    private static Session facebookSession;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LocationTracker getLocationTracker() {
        return locationTracker;
    }

    public void setLocationTracker(LocationTracker locationTracker) {
        this.locationTracker = locationTracker;
    }

    public static void setFacebookSession(Session session)
    {
        facebookSession = session;
    }

    public static Session getFacebookSession()
    {
        return facebookSession;
    }

    public static boolean hasSessionOpen()
    {
        return facebookSession!=null? facebookSession.isOpened() : false;
    }

    public static boolean isSessionClosed()
    {
        return facebookSession!=null? facebookSession.isClosed() : true;
    }

}
