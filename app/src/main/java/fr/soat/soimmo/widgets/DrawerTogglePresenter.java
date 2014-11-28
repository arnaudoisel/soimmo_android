package fr.soat.soimmo.widgets;

import android.content.res.Configuration;
import android.view.MenuItem;

import fr.soat.soimmo.widgets.DrawerToggle.DrawerOpenedClosedListener;

public interface DrawerTogglePresenter {

	void syncState();
	
	void onConfigurationChanged(Configuration newConfig);
	
	boolean onOptionsItemSelected(MenuItem item);
	
	void setDrawerOpenedClosedListener(DrawerOpenedClosedListener listener);
	
}
