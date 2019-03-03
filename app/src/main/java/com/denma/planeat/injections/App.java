package com.denma.planeat.injections;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate(){
        super.onCreate();
        LeakCanary.install(this);
        this.intDagger();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector(){
        return dispatchingAndroidInjector;
    }

    // Binding our application instance to our Dagger graph.
    private void intDagger(){
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }
}
