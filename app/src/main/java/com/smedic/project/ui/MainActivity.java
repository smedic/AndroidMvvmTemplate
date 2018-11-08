package com.smedic.project.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.smedic.project.R;
import com.smedic.project.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "SMEDIC";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		mainActivityViewModel.getMockData().observe(this, s -> Log.d(TAG, "onChanged: data: " + s
		));

	}
}
