package com.example.prova;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.example.prova.model.ObservableModel;
import com.example.prova.service.DownloadCallable;

public class ObservableFutureTask<T extends ObservableModel> extends FutureTask<T> {

	private ArrayList<Observer> observers;

	public ObservableFutureTask(Class<T> type, String url) {
		super(new DownloadCallable<T>(type, url));
		observers = new ArrayList<Observer>();
	}

	@Override
	public void done() {
		super.done();
		if (isDone()) {
			try {
				ObservableModel t = this.get();
				for(Observer observer : observers)
					t.addObserver(observer);
				t.notifyChanges();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

}