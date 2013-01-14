package com.tallevi.android.mvc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tallevi.android.mvc.callable.FutureCallable;
import com.tallevi.android.mvc.callable.Result;
import com.tallevi.android.mvc.model.MVCModel;
import com.tallevi.android.mvc.model.MVCModelWrapper;
import com.tallevi.android.mvc.view.MVCViewWrapper;

import android.util.Log;
import android.view.View;

public class MVCController<T extends MVCModel> implements Result {
	
	private HashMap<MVCViewWrapper, Result> views = new HashMap<MVCViewWrapper, Result>();
	private MVCModelWrapper<T> model;

	public MVCController(MVCModelWrapper<T> model) {
		this.model = model;
	}

	public void link(MVCViewWrapper view, Result result) {
		Log.e("MVCController", "link");
		views.put(view, result);
	}
	

	public void link(View view, Result result) {
		Log.e("MVCController", "link");
		views.put(new MVCViewWrapper(view), result);
	}

	public void execute(FutureCallable<T> callable) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		MVCFutureTask<T> futureCallable = new MVCFutureTask<T>(callable);
		futureCallable.onResult(this);
		executor.execute(futureCallable);
	}

	@Override
	public void onSuccess(final MVCModel model, View view) {
		Log.e("MVCControll", "onSuccess");
		this.model.set(model);
		Iterator<Entry<MVCViewWrapper, Result>> it = views.entrySet().iterator();
		while(it.hasNext()) {
			final Entry<MVCViewWrapper, Result> entry = it.next();
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
		Iterator<Entry<MVCViewWrapper, Result>> it = views.entrySet().iterator();
		while(it.hasNext()) {
			final Entry<MVCViewWrapper, Result> entry = it.next();
			entry.getKey().getView().post(new Runnable() {
				@Override
				public void run() {
					entry.getValue().onFail(entry.getKey().getView());
				}
			});
		}
	}

	public static <T extends  MVCModel> MVCController<T> createControllerFromModel() {
		return new MVCController<T>(new MVCModelWrapper<T>());
	}


}
