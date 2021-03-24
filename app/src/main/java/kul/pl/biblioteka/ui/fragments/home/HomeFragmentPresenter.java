package kul.pl.biblioteka.ui.fragments.home;

import kul.pl.biblioteka.api.API;

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter {

    private HomeFragmentContract.View view;
    private API api;

    public HomeFragmentPresenter(HomeFragmentContract.View view) {
        this.view = view;
        this.api = new API();
    }

    @Override
    public void setListSortByTitle() {
        // view.setList();
    }

    @Override
    public void setListSortByRating() {
        //view.setList();
    }

    @Override
    public void setListSortByDate() {
        //view.setList();
    }

    @Override
    public void setListTopBooks() {
        //view.setList();
    }

    @Override
    public void setListSortByDiscover() {
        //view.setList();
    }

    @Override
    public void setListByName(String bookName) {
        //view.setList();
    }
}
