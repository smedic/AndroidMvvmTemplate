package com.smedic.project;

import android.app.Application;
import android.content.Context;

import com.smedic.project.dagger.AppComponent;
import com.smedic.project.dagger.DaggerAppComponent;
import com.smedic.project.dagger.modules.AppModule;
import com.smedic.project.dagger.modules.NetworkApiModule;
import com.smedic.project.dagger.modules.NetworkModule;
import com.smedic.project.dagger.modules.RepositoriesModule;

import timber.log.Timber;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
public class MyApplication extends Application {

	private static AppComponent component;
	private static Context context;
	private static MyApplication instance = null;

	@Override
	public void onCreate() {
		super.onCreate();

		Timber.plant(new Timber.DebugTree());

		component = DaggerAppComponent.builder()
			.appModule(new AppModule(this))
			.networkModule(new NetworkModule())
			.networkApiModule(new NetworkApiModule())
			.repositoriesModule(new RepositoriesModule())
			//add more modules
			.build();

		context = getApplicationContext();

		instance = this;
	}

	public static AppComponent getComponent() {
		return component;
	}

	public static Context getAppContext() {
		return context;
	}

	public static MyApplication getInstance() {
		return instance;
	}
}