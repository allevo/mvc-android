package com.example.prova.service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.prova.ObservableFutureTask;
import com.example.prova.model.ObservableModel;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service {

	ArrayList<String> list;

	@Override
	public void onCreate() {
		Log.e("MyService", "on create");
		list = new ArrayList<String>();
	}

	public <T extends ObservableModel> ObservableFutureTask<T> get(Class<T> type, String url) {
		ExecutorService eService = Executors.newFixedThreadPool(10);
		ObservableFutureTask<T> th = new ObservableFutureTask<T>(type, url);
		eService.execute(th);
		return th;
	}

	private IBinder binder = new DownloadServiceBinder();

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("MyService", "on bind");
		return binder;
	}

	public class DownloadServiceBinder extends Binder {
		public DownloadService getService() {
			return DownloadService.this;
		}
	}
}