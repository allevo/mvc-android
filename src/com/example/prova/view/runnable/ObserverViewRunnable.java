package com.example.prova.view.runnable;

import java.util.Observable;

public interface ObserverViewRunnable<T> {
	public void run(Observable observable, T view);
}