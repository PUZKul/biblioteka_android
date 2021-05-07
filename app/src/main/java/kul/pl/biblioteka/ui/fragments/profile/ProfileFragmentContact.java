package kul.pl.biblioteka.ui.fragments.profile;

public interface ProfileFragmentContact {
    interface view {
        void setNick(String nick);

        void setReadBooks(String readBooks);

        void setCurrentBooks(String currentBooks);

        void setCurrentLevel(String level);

        void setNextLevel(String level);

        void setExperience(int experience);

        void openMainActivity();

        void startProgressBar();

        void endProgressBar();
    }

    interface Presenter {

        void setUSeaDetails();

        void logoutUser();

    }
}
