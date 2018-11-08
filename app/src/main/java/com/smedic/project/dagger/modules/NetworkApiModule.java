package com.smedic.project.dagger.modules;

import com.smedic.project.repository.NetworkApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
@Module
public class NetworkApiModule {

	@Provides
	NetworkApi provideNetworkApi(Retrofit retrofit) {
		return retrofit.create(NetworkApi.class);
	}
}
