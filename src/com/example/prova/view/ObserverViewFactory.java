package com.example.prova.view;

import com.example.prova.view.runnable.ObserverViewRunnable;

import android.view.View;

public class ObserverViewFactory {

	public static <T extends View> ObserverView<T> createFromView(T view) {
		return new ObserverView<T>(view);
	}
	public static <T extends View> ObserverView<T> createFromView(T view, ObserverViewRunnable<T> runnable) {
		return new ObserverView<T>(view, runnable);
	}

}
