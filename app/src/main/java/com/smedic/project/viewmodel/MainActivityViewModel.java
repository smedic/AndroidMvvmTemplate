package com.smedic.project.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.smedic.project.MyApplication;
import com.smedic.project.repository.NetworkApiRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Stevan Medic
 * @since Motiv version 1.0
 * <p>
 * Created on Oct 2018
 */
public class MainActivityViewModel extends ViewModel {

	private CompositeDisposable disposable = new CompositeDisposable();
	private MutableLiveData<String> stringLiveData = new MutableLiveData<>();

	public MainActivityViewModel() {
		MyApplication.getComponent().inject(this);
	}

	@Inject
	NetworkApiRepository networkApiRepository;

	public LiveData<String> getMockData() {
		disposable.add(networkApiRepository.getMockData().subscribeOn(Schedulers.computation())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(stringLiveData::setValue
				, error -> {
				}));
		return stringLiveData;
	}

	@Override
	protected void onCleared() {
		super.onCleared();

		if (!disposable.isDisposed()) {
			disposable.dispose();
		}
	}
}
