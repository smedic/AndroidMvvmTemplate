package com.smedic.project.repository;

import io.reactivex.Single;

/**
 * @author Stevan Medic
 * <p>
 * Created on Oct 2018
 */
public interface NetworkApiRepository {
	Single<String> getMockData();
}
