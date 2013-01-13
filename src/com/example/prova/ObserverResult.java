package com.example.prova;

import java.util.Observable;

import android.view.View;

public interface ObserverResult<T extends View> {
	
	public void onSuccess(Observable observable, T view);
	public void onFail(T view);

}
