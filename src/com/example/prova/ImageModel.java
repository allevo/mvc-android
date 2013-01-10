package com.example.prova;

import java.io.InputStream;
import java.util.Observable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageModel extends Observable {

	private Bitmap bitmap;

	public void setFromInputStream(InputStream is) {
		bitmap = BitmapFactory.decodeStream(is);
	}

	public void notifyChanges() {

		Log.e("MyModel", "notifyChanges ");
		setChanged();
		notifyObservers(this);
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

}
