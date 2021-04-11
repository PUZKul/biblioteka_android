package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.UserModel;

public class RegisterActivityPresenter implements RegisterActivityContract.Presenter{
    private RegisterActivityContract.View view;
    private LibraryAccess api;

    public RegisterActivityPresenter(RegisterActivityContract.View view) {
        this.view=view;
        api=LibraryAccess.getInstance();

    }


    @Override
    public void onRegisterClicked(UserModel user) {
        view.startProgressBar();
        if(validateDate(user)){
            view.showToast("Incorrect data");
            view.endProgressBar();
        }else{
            view.endProgressBar();
            view.openMainActivity();
        }
    }

    private boolean validateDate(UserModel user){

        //nie wiem jak zrobic
        return true;
    }
}
