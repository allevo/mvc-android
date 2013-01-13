package com.example.prova;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.util.Log;
import android.view.View;

public class MVCController<T extends MVCObservable> implements Result {
	
	private HashMap<ObserverView, Result> views = new HashMap<ObserverView, Result>();
	private ObservableModel<T> model;

	public MVCController(ObservableModel<T> model) {
		this.model = model;
	}

	public void link(ObserverView view, Result result) {
		Log.e("MVCController", "link");
		views.put(view, result);
	}

	public void execute(FutureCallable<T> callable) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		MyFutureTask<T> futureCallable = new MyFutureTask<T>(callable);
		futureCallable.onResult(this);
		executor.execute(futureCallable);
	}

	@Override
	public void onSuccess(final MVCObservable model, View view) {
		Log.e("MVCControll", "onSuccess");
		this.model.set(model);
		Iterator<Entry<ObserverView, Result>> it = views.entrySet().iterator();
		while(it.hasNext()) {
			final Entry<ObserverView, Result> entry = it.next();
			entry.getKey().getView().post(new Runnable() {
				@Override
				public void run() {
					entry.getValue().onSuccess(model, entry.getKey().getView());
				}
			});
		}
	}

	@Override
	public void onFail(View view) {
		Log.e("MVCControll", "onFail");
		Iterator<Entry<ObserverView, Result>> it = views.entrySet().iterator();
		while(it.hasNext()) {
			final Entry<ObserverView, Result> entry = it.next();
			entry.getKey().getView().post(new Runnable() {
				@Override
				public void run() {
					entry.getValue().onFail(entry.getKey().getView());
				}
			});
		}
	}

	public static <T extends  MVCObservable> MVCController<T> createControllerFromModel() {
		return new MVCController<T>(new ObservableModel<T>());
	}


}
