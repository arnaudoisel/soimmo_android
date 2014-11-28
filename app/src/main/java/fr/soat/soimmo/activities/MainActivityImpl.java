package fr.soat.soimmo.activities;

import fr.soat.soimmo.R;
import fr.soat.soimmo.activities.modules.MainActivityModule;

import java.util.Arrays;
import java.util.List;

public class MainActivityImpl extends BaseActivity implements MainActivity {
	
	@Override
	protected int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	protected int getContainer() {
		return R.id.main_layout;
	}

	@Override
	protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainActivityModule(this));
	}
	
}
