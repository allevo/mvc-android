package com.example.prova;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.json.JSONException;

import android.util.Log;

class DownloadCallable<T extends ImageModel> implements Callable<T> {
	public Future<T> obj;
	private Class<T> type;
	private String url;

	public DownloadCallable(Class<T> type, String url) {
		this.type = type;
		this.url = url;
	}

	@Override
	public T call() throws JSONException {
		Log.e("MyThread", "call");
		try {
			URL url = new URL(this.url);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream iStream = conn.getInputStream();
			T model = type.newInstance();
			model.setFromInputStream(iStream);
			return model;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}