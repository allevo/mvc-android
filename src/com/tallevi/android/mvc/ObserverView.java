package com.tallevi.android.mvc;

import android.view.View;


public class ObserverView {

	private View view;

	public ObserverView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

}
