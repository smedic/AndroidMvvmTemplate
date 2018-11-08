package com.smedic.project.repository;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
public interface NetworkApi {

	@GET("bins/11epii")
	Single<ResponseBody> getData();
}
