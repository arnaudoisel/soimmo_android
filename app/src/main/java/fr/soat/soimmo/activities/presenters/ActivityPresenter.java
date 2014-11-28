package fr.soat.soimmo.activities.presenters;

import android.os.Bundle;

public interface ActivityPresenter {

	void onCreate(Bundle savedInstanceState);
	
	void onRestart();
	
	void onStart();
	
	void onResume();
	
	void onPause();
	
	void onStop();
	
	void onDestroy();
	
	void onSaveInstanceState(Bundle outState);

}
