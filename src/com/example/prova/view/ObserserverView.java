package com.example.prova.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
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
		((Activity) view.getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				runnable.run(observable, view);
			}
		});
	}
}
