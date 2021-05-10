package kul.pl.biblioteka.ui.fragments.readingAndHistory.reservation;

public class ReservationFragmentPresenter implements ReservationFragmentContact.Presenter{

    private ReservationFragmentContact.View view;

    public ReservationFragmentPresenter(ReservationFragmentContact.View view) {
        this.view = view;
    }

    @Override
    public void setList() {
        view.setDarkList();
    }
}
