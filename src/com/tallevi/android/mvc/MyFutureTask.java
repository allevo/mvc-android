package com.tallevi.android.mvc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import android.util.Log;

public class MyFutureTask<T extends MVCObservable> extends FutureTask<T> {

	private Result callable;

	public MyFutureTask(Callable<T> callable) {
		super(callable);
	}
	
	@Override
	public void done() {
		super.done();
		Log.e("MyFutureTask", "done");
		if(isCancelled()) {
			callable.onFail(null);
		} else {
			try {
				callable.onSuccess(get(), null);
			} catch (InterruptedException e) {
				e.printStackTrace();
				callable.onFail(null);
			} catch (ExecutionException e) {
				e.printStackTrace();
				callable.onFail(null);
			}
		}
	}
	
	public void onResult(Result callable) {
		this.callable = callable;
	}

}
