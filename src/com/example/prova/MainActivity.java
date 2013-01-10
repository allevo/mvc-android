package com.example.prova;

import java.util.Observable;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;

import com.example.prova.DownloadService.MyServiceBinder;
import com.example.prova.ObserserverView.ObserverViewRunnable;

public class MainActivity extends Activity {

	protected MyServiceBinder mBinder;
	protected boolean linkedToService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.e("MainActivity", "start connecting to DownloadService");
		bindService(new Intent(this, DownloadService.class), conn,
				Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		stopService(new Intent(this, DownloadService.class));
	}

	protected void setModels() {
		ObservableFutureTask<ImageModel> model = mBinder.getService().get(ImageModel.class, "https://www.google.it/images/srpr/logo3w.png");
		ImageView view = (ImageView) findViewById(R.id.image);
		ObserserverView<ImageView> ob = new ObserserverView<ImageView>(view, new ObserverViewRunnable<ImageView>() {
			@Override
			public void run(Observable observable, ImageView view) {
				view.setImageBitmap(((ImageModel)observable).getBitmap());
				view.invalidate();
			}
		});
		model.addObserver(ob);
		view = (ImageView) findViewById(R.id.image2);
		ob = new ObserserverView<ImageView>(view, new ObserverViewRunnable<ImageView>() {
			@Override
			public void run(Observable observable, ImageView view) {
				view.setImageBitmap(((ImageModel)observable).getBitmap());
				view.invalidate();
			}
		});
		model.addObserver(ob);
		view = (ImageView) findViewById(R.id.image3);
		ob = new ObserserverView<ImageView>(view, new ObserverViewRunnable<ImageView>() {
			@Override
			public void run(Observable observable, ImageView view) {
				view.setImageBitmap(((ImageModel)observable).getBitmap());
				view.invalidate();
			}
		});
		model.addObserver(ob);
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e("MainActivity", "disconnect service");
			linkedToService = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
			Log.e("MainActivity", "connect service");
			mBinder = ((MyServiceBinder) serviceBinder);
			linkedToService = true;
			setModels();
		}
	};

}
