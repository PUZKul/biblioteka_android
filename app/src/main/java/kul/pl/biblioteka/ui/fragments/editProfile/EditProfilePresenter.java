package kul.pl.biblioteka.ui.fragments.editProfile;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
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
        if(!view.isCheckedBoxEditPassword()){
            if(!StringHelper.validateEmailRegistration(user.getEmail())){
                view.errorEmailIncorrect();
                //todo implement api method changed only email
            }
        }else{
            if (validateFields(user)) {
                if (InternetConnection.isConnection(MainActivity.getAppContext())) {
                    view.startProgressBar();
                    //todo implement api method changed only email)
                }
            }
        }
    }

    @Override
    public void setUserDetails() {
        api.getUserDetails(LocalDataAccess.getToken());
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        this.user = user;
        view.setEmail(user.getEmail());
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
    public void onNoInternet() {
        view.endProgressBar();
        view.openOnInternetActivity();
    }
}
