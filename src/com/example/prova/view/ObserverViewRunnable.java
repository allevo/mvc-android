package com.example.prova.view;

import java.util.Observable;

public interface ObserverViewRunnable<T> {
	public void run(Observable observable, T view);
}