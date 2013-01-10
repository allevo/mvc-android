package com.example.prova.model;

import java.io.InputStream;
import java.util.Observable;

import android.graphics.BitmapFactory;

abstract public class ObservableModel extends Observable {

	public ObservableModel() {
		super();
	}

	abstract public void setFromInputStream(InputStream is);

	public void notifyChanges() {
		setChanged();
		notifyObservers(this);
	}

}