package com.smedic.project.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
@Module
public class AppModule {

	private Application mApplication;

	public AppModule(Application application) {
		mApplication = application;
	}

	@Provides
	@Singleton
	Application providesApplication() {
		return mApplication;
	}

	// Dagger will only look for methods annotated with @Provides
	@Provides
	@Singleton
	// Application reference must come from AppModule.class
	SharedPreferences providesSharedPreferences(Application application) {
		return PreferenceManager.getDefaultSharedPreferences(application);
	}
}