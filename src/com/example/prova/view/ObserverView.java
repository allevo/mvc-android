package com.example.prova.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.view.View;

import com.example.prova.ObservableFutureTask;
import com.example.prova.model.ObservableModel;

public class ObserverView<T extends View> implements Observer {

	private ObserverViewRunnable<T> runnable;
	private T view;

	@Override
	public void update(final Observable observable, Object arg1) {
		((Activity) view.getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				runnable.run(observable, view);
			}
		});
	}
	
	ObserverView(T view, ObserverViewRunnable<T> runnable) {
		this.runnable = runnable;
		this.view = view;
	}

	ObserverView(T view) {
		this(view, null);
	}
	
	public ObserverView<T> setRunnable(ObserverViewRunnable<T> runnable) {
		this.runnable = runnable;
		return this;
	}

	public <E extends ObservableModel> void observe(ObservableFutureTask<E> imageModel) {
		imageModel.addObserver(this);
	}
}
