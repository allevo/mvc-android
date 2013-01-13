package com.example.prova;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final String LOGO_GOOGLE_URL = "https://www.google.it/images/srpr/logo3w.png";
	private static final String IAMBOO_LOGO_URL = "http://www.iamboo.it/images/stories/logoiamboo.png";

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
/*
		bindService(new Intent(this, DownloadService.class), conn,
				Context.BIND_AUTO_CREATE);
				*/
		
		setModels();
	}

	@Override
	protected void onStop() {
		super.onStop();
		//stopService(new Intent(this, DownloadService.class));
	}

	protected void setModels() {
		Log.e("MainActivity", "setModel");
		
		MVCController<ImageModel> controller = MVCController.createControllerFromModel();
		ObserverView view = new ObserverView(this.view1);
		controller.link(view, new ImageResult());
		view = new ObserverView(this.view2);
		controller.link(view, new ImageResult());
		view = new ObserverView(this.view3);
		controller.link(view, new ImageResult());
		controller.execute(new ImageCallable(LOGO_GOOGLE_URL));
	}
/*
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
	*/
}
