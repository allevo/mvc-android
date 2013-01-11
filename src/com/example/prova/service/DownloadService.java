package com.example.prova.service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.prova.ObservableFutureTask;
import com.example.prova.model.ObservableModel;

public class DownloadService extends Service {

	ArrayList<String> list;

	@Override
	public void onCreate() {
		list = new ArrayList<String>();
	}

	private IBinder binder = new DownloadServiceBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	public class DownloadServiceBinder extends Binder {
		public DownloadService getService() {
			return DownloadService.this;
		}
	}

	public <T  extends ObservableModel> ObservableFutureTask<T> get(String url) {
		ExecutorService eService = Executors.newFixedThreadPool(10);
		ObservableFutureTask<T> th = new ObservableFutureTask<T>(url);
		eService.execute(th);
		return th;
	}
}
