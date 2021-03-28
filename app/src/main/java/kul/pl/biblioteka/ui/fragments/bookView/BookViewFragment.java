package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import kul.pl.biblioteka.R;

public class BookViewFragment extends Fragment implements BookViewFragmentContract.View{

    private ImageView imageTextView;
    private TextView authorTextView;
    private TextView publisherTextView;
    private TextView pagesCountTextView;
    private TextView yearTextView;
    private TextView availableTextView;
    private RatingBar ratingBar;
   // private ProgressBar progressBar;
    private Button backBtn;
    private Button borrowBtn;
    private BookViewFragmentPresenter presenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_view, container, false);
         initComponents(view);
       // progressBar = view.findViewById(R.id.progressBar);
        presenter=new BookViewFragmentPresenter(this,this.getArguments().getInt("idBook"));
        setOnClickListeners();
        return view;
    }

    private void setOnClickListeners() {
        backBtn.setOnClickListener(backOnClickListener);
        borrowBtn.setOnClickListener(borrowOnClickListener);
    }

    private final View.OnClickListener backOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeFragment();
            }
        };

    private final View.OnClickListener borrowOnClickListener = new View.OnClickListener(){
         @Override
        public void onClick(View v){
        }
    };

    private void openHomeFragment(){
        openHomeFragment();
    }

    private void initComponents(View view) {
        imageTextView = view.findViewById(R.id.imageView);
        authorTextView = view.findViewById(R.id.BookView_text_Author);
        publisherTextView = view.findViewById(R.id.BookView_text_Publisher);
        pagesCountTextView = view.findViewById(R.id.BookView_text_pages);
        yearTextView = view.findViewById(R.id.BookView_text_year);
        availableTextView = view.findViewById(R.id.BookView_text_available);
        ratingBar = view.findViewById(R.id.ratingBar);
        backBtn = view.findViewById(R.id.BookView_button_back);
    }

    @Override
    public void setAuthor(String author) {
        authorTextView.setText(author);
    }

    @Override
    public void setPublisher(String publisher) {
        publisherTextView.setText(publisher);
    }

    @Override
    public void setPages(String pages) {
        pagesCountTextView.setText(pages);
    }

    @Override
    public void setImage(Uri uriImage) {
        imageTextView.setImageURI(uriImage);
    }

    @Override
    public void setDate(String date) {
        yearTextView.setText(date);
    }

    @Override
    public void setAvailable(String available) {
        availableTextView.setText(available);
    }

    @Override
    public void setStars(double number) {
        ratingBar.setRating((float) number);
        ratingBar.setMax(5);
    }

    @Override
    public void startProgressBar() {
        //progressBar.setIndeterminate(true);
    }

    @Override
    public void endProgressBar() {
       // progressBar.setIndeterminate(false);
    }
}