package com.intech.labs.intechmusic.app;

import android.app.Application;

import com.intech.labs.intechmusic.di.component.AppComponent;
import com.intech.labs.intechmusic.di.component.DaggerAppComponent;
import com.intech.labs.intechmusic.di.module.AppModule;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */
public class App extends Application {

    private static AppComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(){
        return mApplicationComponent;
    }

}
