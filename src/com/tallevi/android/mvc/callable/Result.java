package com.tallevi.android.mvc.callable;

import com.tallevi.android.mvc.model.MVCModel;

import android.view.View;

public interface Result {

	
	public abstract void onSuccess(MVCModel model, View view);

	public abstract void onFail(View view);

}