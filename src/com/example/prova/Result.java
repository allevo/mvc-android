package com.example.prova;

import android.view.View;

public interface Result {

	
	public abstract void onSuccess(MVCObservable model, View view);

	public abstract void onFail(View view);

}