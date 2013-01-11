package com.example.prova;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageView;

import com.example.prova.model.ImageModel;
import com.example.prova.service.DownloadService;
import com.example.prova.service.DownloadService.DownloadServiceBinder;
import com.example.prova.view.ObserverViewFactory;
import com.example.prova.view.runnable.ImageRunnable;

public class MainActivity extends Activity {

	private static final String LOGO_GOOGLE_URL = "https://www.google.it/images/srpr/logo3w.png";

	private static final String IAMBOO_LOGO_URL = "http://www.iamboo.it/images/stories/logoiamboo.png";

	protected DownloadService downloadService;
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
		ObservableFutureTask<ImageModel> googleLogoModel = downloadService
				.get(LOGO_GOOGLE_URL);

		ObserverViewFactory.createFromView(view1)
				.setRunnable(new ImageRunnable()).observe(googleLogoModel);
		ObserverViewFactory.createFromView(view2)
				.setRunnable(new ImageRunnable()).observe(googleLogoModel);

		ObservableFutureTask<ImageModel> iambooLogoModel = downloadService
				.get(IAMBOO_LOGO_URL);
		ObserverViewFactory.createFromView(view3)
				.setRunnable(new ImageRunnable()).observe(iambooLogoModel);
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			linkedToService = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
			downloadService = ((DownloadServiceBinder) serviceBinder)
					.getService();
			linkedToService = true;
			setModels();
		}
	};

}
