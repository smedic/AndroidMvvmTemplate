package com.smedic.project.dagger.modules;

import android.support.annotation.NonNull;

import com.smedic.project.repository.NetworkApi;
import com.smedic.project.repository.NetworkApiRepository;
import com.smedic.project.repository.NetworkApiRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

	@Provides
	@Singleton
	NetworkApiRepository provideNetworkApiModule(@NonNull final NetworkApi networkApi) {
		return new NetworkApiRepositoryImpl(networkApi);
	}
}
