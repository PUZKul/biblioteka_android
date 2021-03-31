package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class LoginActivityPresenter implements LoginActivityContract.Presenter{

    private LoginActivityContract.View view;
    private LibraryAccess api;

    public LoginActivityPresenter(LoginActivityContract.View view) {
        this.view = view;
        api=LibraryAccess.getInstance();
        this.view=view;
        //todo uncomment when will be implementation in api
        //api.setListener(this);
    }

    @Override
    public void onLoginClicked(UserModel user) {
        view.startProgressBar();
        if(!validateDate(user)){
            view.showToast("Incorrect data");
            view.endProgressBar();
        }
        else {
            //todo connect with api
            view.endProgressBar();
            view.openMainActivity();
        }
    }

    private boolean validateDate(UserModel user) {
        if(user.getEmail().isEmpty()||user.getNick().isEmpty())
            return false;
        else if(!StringHelper.validateNickLogin(user.getNick()))
            return false;
        else if(!StringHelper.validatePasswordLogin(user.getPassword()))
            return false;
        return true;
    }
}
