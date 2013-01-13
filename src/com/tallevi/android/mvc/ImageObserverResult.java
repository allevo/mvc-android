package com.tallevi.android.mvc;

import java.util.Observable;

import android.util.Log;
import android.widget.ImageView;

public class ImageObserverResult implements ObserverResult<ImageView> {

	@Override
	public void onSuccess(Observable observable, ImageView view) {
		Log.e("ImageObserverResult", "OnSuccess");
	}

	@Override
	public void onFail(ImageView view) {
		Log.e("ImageObserverResult", "OnFail");
	}

}
