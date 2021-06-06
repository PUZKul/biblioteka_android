package kul.pl.biblioteka.ui.fragments.editProfile;

import android.os.Handler;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.EditUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.ui.activity.main.MainActivity;
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
            if (EditProfileValidation.validateEditProfileFields(view))
                view.openDialog();
        } else {
            if (validateFields(user)) {
                view.openDialog();
            }
        }
    }

    @Override
    public void setUserDetails() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext()))
            api.getUserDetails(LocalDataAccess.getToken());
        else {
            openNoInternetDialog();
        }
    }

    @Override
    public void changeUserData(EditUserModel model) {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext()))
            api.editUserData(LocalDataAccess.getToken(), model);
        else {
            openNoInternetDialog();
        }
    }

    private void openNoInternetDialog() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.openNoInternetDialog();
            }
        }, 5000);
    }

    @Override
    public void onEditUserReceive() {
        view.onRefresh();;
        //view.endProgressBar();
        view.showToast(MainActivity.getAppContext().getString(R.string.the_data_has_been_edited));
        view.closeFragment();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        this.user = user;
        view.setEmail(user.getEmail());
        if (user.getPhone() != null) view.setPhone(user.getPhone());
        if (user.getLastName() != null) view.setLastName(user.getLastName());
        if (user.getFirstName() != null) view.setFistName(user.getFirstName());
        if (user.getAddress() != null) view.setAddress(user.getAddress());
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

//    @Override
//    public void onRefreshServer() {
//        view.onRefresh();
//    }
}
