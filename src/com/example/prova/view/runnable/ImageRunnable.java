package com.example.prova.view.runnable;

import java.util.Observable;

import com.example.prova.model.ImageModel;

import android.widget.ImageView;

public class ImageRunnable implements ObserverViewRunnable<ImageView> {

	@Override
	public void run(Observable model, ImageView view) {
		view.setImageBitmap(((ImageModel) model).getBitmap());
		view.invalidate();
	}

}
