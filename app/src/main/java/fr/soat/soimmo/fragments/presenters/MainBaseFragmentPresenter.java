package fr.soat.soimmo.fragments.presenters;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import javax.inject.Inject;

import fr.soat.soimmo.fragments.MainFragment;
import fr.soat.soimmo.service_helpers.LoginManager;
import fr.soat.soimmo.widgets.DrawerToggle.DrawerOpenedClosedListener;
import fr.soat.soimmo.widgets.DrawerTogglePresenter;

public abstract class MainBaseFragmentPresenter extends BaseFragmentPresenter implements MainFragmentPresenter {
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			MainBaseFragmentPresenter.this.view.displayView(position);
		}
	}

    @Inject
	MainFragment view;
	
	@Inject
	LoginManager loginManager;
	
	protected DrawerTogglePresenter drawerTogglePresenter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
				
		view.setDrawerListItemClickListener(new DrawerItemClickListener());
		
		drawerTogglePresenter = view.getDrawerTogglePresenter();
		drawerTogglePresenter.setDrawerOpenedClosedListener(new DrawerOpenedClosedListener() {
			
			@Override
			public void onDrawerOpened(View drawerView) {
				view.setDrawerTitleAndHideActionBarIcons();
			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				view.setNewTitleAndShowActionBarIcons();
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerTogglePresenter.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		// Pass any configuration change to the drawer toggle
		drawerTogglePresenter.onConfigurationChanged(newConfig);
	}

    @Override
    protected boolean hasOptionsMenu() {
        return true;
    }

    @Override
    protected boolean needRegistrationToFragmentEventBus() {
        return false;
    }

}
