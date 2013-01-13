package com.tallevi.android.mvc.model;



public class MVCModelWrapper<T extends MVCModel> {

	private T model;
	
	public T getModel() {
		return model;
	}

	@SuppressWarnings("unchecked")
	public void set(MVCModel model) {
		this.model = (T) model;
	}

}
