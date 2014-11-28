package fr.soat.soimmo.fragments;

import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import fr.soat.soimmo.R;
import fr.soat.soimmo.fragments.modules.LoginFragmentModule;

public class LoginFragmentImpl extends BaseFragment implements LoginFragment {


    @InjectView(R.id.login_button)
    Button loginButton;

    @Inject
    public LoginFragmentImpl() {
        super();
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new LoginFragmentModule(this));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void setLoginClickListener(View.OnClickListener listener) {
        loginButton.setOnClickListener(listener);
    }

}
