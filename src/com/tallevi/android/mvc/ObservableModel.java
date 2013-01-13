package com.tallevi.android.mvc;


public class ObservableModel<T extends MVCObservable> {

	private T model;
	
	public T getModel() {
		return model;
	}

	@SuppressWarnings("unchecked")
	public void set(MVCObservable model) {
		this.model = (T) model;
	}

}
