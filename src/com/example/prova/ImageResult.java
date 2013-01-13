package com.example.prova;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageResult implements Result {

	@Override
	public void onSuccess(MVCObservable model, View view) {
		ImageView imageView = (ImageView) view;
		imageView.setImageBitmap(((ImageModel) model).getBitmap());
	}

	@Override
	public void onFail(View view) {
		Toast.makeText(view.getContext(), "onFail", Toast.LENGTH_SHORT).show();
	}

}
