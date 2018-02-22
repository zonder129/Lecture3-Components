package ru.mail.park.lecture3;

import android.app.Application;
import android.util.Log;

/**
 * This class is just an example of Application component and actually does nothing.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("App", "onCreate");
    }
}
