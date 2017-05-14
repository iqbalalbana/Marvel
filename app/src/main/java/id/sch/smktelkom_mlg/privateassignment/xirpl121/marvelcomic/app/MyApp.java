package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.app;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Iqbal Albana on 14/05/2017.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
