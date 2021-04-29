package kul.pl.biblioteka.ui.fragments.editProfile;

import android.content.Context;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.RegistrationApiUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class EditProfilePresenter extends APIAdapter implements EditProfileContract.Presenter {

    private EditProfileContract.View view;
    private LibraryAccess api;
    private Context context;
    private UserModel user;


    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
        this.context = context;
    }

    @Override
    public void onSaveClicked(RegistrationUserModel user) {
        if (validateFields(user)) {
            if (InternetConnection.isConnection(context)) {
                view.startProgressBar();
                api.getRegistration(new RegistrationApiUserModel(
                        user.getNick()
                        , user.getEmail()
                        , user.getPasswordFirst()
                ));
            } else
                view.openOnInternetActivity();
        }
    }

    @Override
    public void setUserDetails() {
        api.getUserDetails(LocalDataAccess.getToken());
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        this.user = user;
        view.getEmail();
        view.getPassword();
        view.getRepeatPassword();
    }

    private boolean validateFields(RegistrationUserModel user) {
        if (!StringHelper.validateEmailRegistration(user.getEmail())) {
            view.errorEmailIncorrect();
            return false;
        } else if (!StringHelper.validatePasswordRegistration(user.getPasswordFirst())) {
            view.errorPasswordIncorrect();
            return false;
        } else if (!StringHelper.validateTwoPasswordRegistration(user.getPasswordFirst(), user.getPasswordSecond())) {
            view.errorRepeatPasswordAreNotIdentical();
            return false;
        }
        return true;
    }


    @Override
    public void onRegistrationSuccesses() {
        view.endProgressBar();
        view.showToast("Successful changed data");
        view.openMainActivity();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        if (error.getStatus() == 409)
            view.showToast("User with given email already exist");
    }

    @Override
    public void onNoInternet() {
        view.endProgressBar();
        view.openOnInternetActivity();
    }

}
