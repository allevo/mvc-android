package com.example.prova.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import com.example.prova.model.ObservableModel;

public class DownloadCallable<T extends ObservableModel> implements Callable<T> {
	
	private String url;

	public DownloadCallable(String url) {
		this.url= url; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public T call() {
		try {
			InputStream iStream = fetchInputStreamFromStringUlr(url);
			T model = (T) ((Class<T>)((ParameterizedType)this.getClass().
				       getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
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