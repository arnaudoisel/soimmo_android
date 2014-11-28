package fr.soat.soimmo.fragments;

import android.widget.AdapterView.OnItemClickListener;

import fr.soat.soimmo.widgets.DrawerTogglePresenter;

public interface MainFragment extends FragmentView {

	DrawerTogglePresenter getDrawerTogglePresenter();

	void displayView(int position);

	void setDrawerListItemClickListener(OnItemClickListener listener);

	void setDrawerTitleAndHideActionBarIcons();

	void setNewTitleAndShowActionBarIcons();

}
