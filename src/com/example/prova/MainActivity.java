package com.example.prova;

import java.util.Observable;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageView;

import com.example.prova.DownloadService.DownloadServiceBinder;
import com.example.prova.ObserserverView.ObserverViewRunnable;

public class MainActivity extends Activity {

	private static final String LOGO_GOOGLE_URL = "https://www.google.it/images/srpr/logo3w.png";
	
	protected DownloadServiceBinder donwloadServiceBinder;
	protected boolean linkedToService;

	private ImageView view1;
	private ImageView view2;
	private ImageView view3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		view1 = (ImageView) findViewById(R.id.image1);
		view2 = (ImageView) findViewById(R.id.image2);
		view3 = (ImageView) findViewById(R.id.image3);
		
		bindService(new Intent(this, DownloadService.class), conn,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		stopService(new Intent(this, DownloadService.class));
	}

	protected void setModels() {
		ObservableFutureTask<ImageModel> imageModel = donwloadServiceBinder
				.getService().get(ImageModel.class,
						LOGO_GOOGLE_URL);

		ObserserverView<ImageView> ob = new ObserserverView<ImageView>(view1,
				new ObserverViewRunnable<ImageView>() {
					@Override
					public void run(Observable model, ImageView view) {
						view.setImageBitmap(((ImageModel) model).getBitmap());
						view.invalidate();
					}
				});
		imageModel.addObserver(ob);

		ob = new ObserserverView<ImageView>(view2,
				new ObserverViewRunnable<ImageView>() {
					@Override
					public void run(Observable model, ImageView view) {
						view.setImageBitmap(((ImageModel) model).getBitmap());
						view.invalidate();
					}
				});
		imageModel.addObserver(ob);

		ob = new ObserserverView<ImageView>(view3,
				new ObserverViewRunnable<ImageView>() {
					@Override
					public void run(Observable model, ImageView view) {
						view.setImageBitmap(((ImageModel) model).getBitmap());
						view.invalidate();
					}
				});
		imageModel.addObserver(ob);
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			linkedToService = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
			donwloadServiceBinder = ((DownloadServiceBinder) serviceBinder);
			linkedToService = true;
			setModels();
		}
	};

}
