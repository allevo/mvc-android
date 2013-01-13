package com.tallevi.android.mvc.view;

import android.view.View;


public class MVCViewWrapper {

	private View view;

	public MVCViewWrapper(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

}
