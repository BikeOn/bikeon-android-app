package cc.bikeon.app;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.facebook.Session;

import cc.bikeon.app.services.local.location.LocationTracker;


/**
 * Created by cristian.rosa on 1/19/2015.
 */
public class BikeOnApplication extends Application {

    private final String TAG = "BikeOnApplication";

    private LocationTracker locationTracker;
    private static Session facebookSession;

    private static BikeOnApplication instance;

    public static BikeOnApplication getInstance()
    {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public LocationTracker getLocationTracker() {

        if (locationTracker == null)
        {
            LocationManager locationManager = (LocationManager)
                    this.getSystemService(Context.LOCATION_SERVICE);

            locationTracker = new LocationTracker(locationManager);
        }

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
