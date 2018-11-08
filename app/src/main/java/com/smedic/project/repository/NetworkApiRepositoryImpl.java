package com.smedic.project.repository;

import io.reactivex.Single;
import okhttp3.ResponseBody;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
public class NetworkApiRepositoryImpl implements NetworkApiRepository {

	private NetworkApi networkApi;

	public NetworkApiRepositoryImpl(final NetworkApi networkApi) {
		this.networkApi = networkApi;
	}

	public Single<String> getMockData() {
		return networkApi.getData()
			.map(ResponseBody::string);
	}
}
