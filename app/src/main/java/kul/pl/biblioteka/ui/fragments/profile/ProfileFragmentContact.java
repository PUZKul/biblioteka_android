package kul.pl.biblioteka.ui.fragments.profile;

public interface ProfileFragmentContact {
    interface viev{
    void setNick(String nick);
    void setReadBooks(int readBooks);
    void setCurrentBooks(int currentBooks);
    void setCurrentLevel(int level);
    void setNextLevel(int level);
    void setExperience(int experience);
    }
    interface Presenter{

    }
}
