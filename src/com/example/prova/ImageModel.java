package com.example.prova;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageModel extends ObservableModel {

	Bitmap bitmap;

	public Bitmap getBitmap() {
		return bitmap;
	}

	@Override
	public void setFromInputStream(InputStream is) {
		bitmap = BitmapFactory.decodeStream(is);
	}

}
