package com.example.prova;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ObserserverView<T extends View> implements Observer {

	private ObserverViewRunnable<T> runnable;
	private T view;

	public ObserserverView(T view, ObserverViewRunnable<T> runnable) {
		this.runnable = runnable;
		this.view = view;
	}

	@Override
	public void update(final Observable observable, Object arg1) {
		Log.e("ObserverView", "update");
		((Activity) view.getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.e("ObservarView", "run runnable");
				runnable.run(observable, view);
			}
		});
	}

	
	interface ObserverViewRunnable<T> {
		public void run(Observable observable, T view);
	}
}
