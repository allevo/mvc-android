package com.example.prova;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import org.json.JSONException;

class DownloadCallable<T extends ImageModel> implements Callable<T> {
	
	private Class<T> type;
	private String url;

	public DownloadCallable(Class<T> type, String url) {
		this.type = type;
		this.url = url;
	}

	@Override
	public T call() throws JSONException {
		try {
			InputStream iStream = fetchInputStreamFromStringUlr(url);
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

	private InputStream fetchInputStreamFromStringUlr(String url) throws MalformedURLException, IOException {
		URLConnection conn = new URL(url).openConnection();
		conn.connect();
		return conn.getInputStream();
	}

}