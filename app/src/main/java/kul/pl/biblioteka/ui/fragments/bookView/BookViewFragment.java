package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;

public class BookViewFragment extends Fragment implements BookViewFragmentContract.View{

    //Todo add all fields
    private TextView authorTextView;
    private Button backBtn;
    private BookViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_view, container, false);
         initComponents(view);
        //ToDo add progresbar in layout
        presenter=new BookViewFragmentPresenter(this,getBookId());
   //     presenter.setBookDetails();
        setOnClickListeners();
        return view;
    }

    private int getBookId() {
        Bundle bundle =new Bundle();
        return bundle.getInt("idBook");
    }

    private void setOnClickListeners() {
        backBtn.setOnClickListener(backOnClickListener);
        //todo add second onClickListener do borrow without implementation (only create method because we don't have created borrowDialog)
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeFragment();
            }
        };

    private void openHomeFragment(){
        //todo open fragment "HomeFragment"
    }

    private void initComponents(View view) {
        authorTextView =view.findViewById(R.id.BookView_text_Autor);
        backBtn=view.findViewById(R.id.BookView_button_back);
        //todo init  rest of the components
    }

    @Override
    public void setAuthor(String author) {
        authorTextView.setText(author);
    }

    @Override
    public void setPublisher(String publisher) {

    }

    @Override
    public void setPages(String pages) {

    }

    @Override
    public void setImage(Uri uriImage) {

    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public void setAvailable(String available) {

    }

    @Override
    public void setStars(double number) {

    }

    @Override
    public void startProgressBar() {

    }

    @Override
    public void endProgressBar() {

    }
}