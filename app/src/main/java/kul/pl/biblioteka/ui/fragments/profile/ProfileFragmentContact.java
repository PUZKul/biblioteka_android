package kul.pl.biblioteka.ui.fragments.profile;

public interface ProfileFragmentContact {
    interface view {
        void setNick(String nick);

        void setReadBooks(String readBooks);

        void setCurrentBooks(String currentBooks);

        void setLastName(String lastName);

        void setFirstName(String firstName);

        void setAddress(String address);

        void setPhone(String phone);

        void openMainActivity();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();

        void setInvisibilityComponents();

        void setVisibilityButton();

    }

    interface Presenter {

        void setUserDetails();

        void logoutUser();

    }
}
