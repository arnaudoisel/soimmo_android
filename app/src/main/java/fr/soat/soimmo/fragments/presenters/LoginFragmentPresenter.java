package fr.soat.soimmo.fragments.presenters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import fr.soat.soimmo.fragments.LoginFragment;
import fr.soat.soimmo.modules.ForApplication;

public class LoginFragmentPresenter extends BaseFragmentPresenter {

    @Inject
    LoginFragment view;

    @Inject
    @ForApplication
    Context applicationContext;

    @Override
    protected boolean hasOptionsMenu() {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        view.setLoginClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        Toast.makeText(applicationContext, "Login !", Toast.LENGTH_LONG).show();
    }
}
