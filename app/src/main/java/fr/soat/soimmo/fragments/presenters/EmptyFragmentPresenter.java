package fr.soat.soimmo.fragments.presenters;


import javax.inject.Inject;

public class EmptyFragmentPresenter extends BaseFragmentPresenter {

    @Inject
    public EmptyFragmentPresenter(){
        super();
    }

    @Override
    protected boolean hasOptionsMenu() {
        return false;
    }

    @Override
    protected boolean needRegistrationToFragmentEventBus() {
        return false;
    }
}
