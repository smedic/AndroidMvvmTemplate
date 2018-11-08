package com.smedic.project.dagger.modules;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smedic.project.utils.Utils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.smedic.project.utils.Constants.BASE_URL;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
@Module
public class NetworkModule {

	private static final String TAG = "SMEDIC";

	@Provides
	@Singleton
	Gson provideGson() {
		return new GsonBuilder().serializeNulls()
			//.registerTypeAdapterFactory(AutoValueGsonFactory.create())
			.create();
	}

	@Provides
	@Singleton
	HttpLoggingInterceptor provideHttpLoggingInterceptor() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d(TAG, "log: " + message));
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		return logging;
	}

	@Provides
	@Singleton
	OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
		return new OkHttpClient.Builder()
			.writeTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.addInterceptor(loggingInterceptor)
			.addInterceptor(chain -> {
				final Request original = chain.request();
				final Request request;
				if (Utils.getHeader() != null && !Utils.getHeader().isEmpty()) {
					request = original.newBuilder()
						.header("Authorization", Utils.getHeader())
						.method(original.method(), original.body())
						.build();
				} else {
					request = original.newBuilder()
						.method(original.method(), original.body())
						.build();
				}
				return chain.proceed(request);
			})
			.hostnameVerifier((s, sslSession) -> true)
			.build();
	}

	@Provides
	@Singleton
	RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
		return RxJava2CallAdapterFactory.create();
	}

	@Provides
	@Singleton
	GsonConverterFactory provideGsonConverterFactory(Gson gson) {
		return GsonConverterFactory.create(gson);
	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory, GsonConverterFactory factory,
							 OkHttpClient okHttpClient) {
		return new Retrofit.Builder()
			.addCallAdapterFactory(rxJava2CallAdapterFactory)
			.addConverterFactory(factory)
			.client(okHttpClient)
			.baseUrl(BASE_URL)
			.build();
	}
}