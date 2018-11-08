package com.smedic.project.dagger;

import com.smedic.project.dagger.modules.AppModule;
import com.smedic.project.dagger.modules.NetworkApiModule;
import com.smedic.project.dagger.modules.NetworkModule;
import com.smedic.project.dagger.modules.RepositoriesModule;
import com.smedic.project.viewmodel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, NetworkApiModule.class, RepositoriesModule.class})
public interface AppComponent {

	void inject(MainActivityViewModel activityViewModel);
}