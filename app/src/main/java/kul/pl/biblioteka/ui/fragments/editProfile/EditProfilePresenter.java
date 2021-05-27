package kul.pl.biblioteka.ui.fragments.editProfile;

import android.os.CountDownTimer;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.EditUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class EditProfilePresenter extends APIAdapter implements EditProfileContract.Presenter {

    private EditProfileContract.View view;
    private LibraryAccess api;
    private UserModel user;


    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void onSaveClicked(RegistrationUserModel user) {
        if (!view.isCheckedBoxEditPassword()) {
            if (StringHelper.validateEmailRegistration(user.getEmail())) {
                view.errorEmailIncorrect();
            } else {
                view.openDialog();
            }
        } else {
            if (validateFields(user)) {
                view.openDialog();
            }
        }
    }

    @Override
    public void setUserDetails() {
        view.startProgressBar();
        api.getUserDetails(LocalDataAccess.getToken());
    }

    @Override
    public void changeUserData(EditUserModel model) {
        view.startProgressBar();
        api.editUserData(LocalDataAccess.getToken(), model);
    }

    @Override
    public void onEditUserReceive() {
        view.endProgressBar();
        view.showToast("The data has been edited");
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        view.showToast(error.getMessage());
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        this.user = user;
        view.setEmail(user.getEmail());
        view.endProgressBar();
    }

    private boolean validateFields(RegistrationUserModel user) {
        if (!StringHelper.validateEmailRegistration(user.getEmail())) {
            view.errorEmailIncorrect();
            view.endProgressBar();
            return false;
        } else if (!StringHelper.validatePasswordRegistration(user.getPasswordFirst())) {
            view.errorPasswordIncorrect();
            view.endProgressBar();
            return false;
        } else if (!StringHelper.validateTwoPasswordRegistration(user.getPasswordFirst(), user.getPasswordSecond())) {
            view.errorRepeatPasswordAreNotIdentical();
            view.endProgressBar();
            return false;
        }
        view.endProgressBar();
        return true;
    }

    @Override
    public void onRefreshServer() {
        new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                view.endProgressBar();
                view.openNoInternetDialog();
            }
        }.start();
    }
}
