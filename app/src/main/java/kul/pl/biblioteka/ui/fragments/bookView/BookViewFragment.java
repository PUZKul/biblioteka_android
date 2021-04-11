package kul.pl.biblioteka.ui.fragments.bookView;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


import java.util.Objects;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.fragments.home.HomeFragment;

public class BookViewFragment extends Fragment implements BookViewFragmentContract.View {

    private ImageView imageView;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView publisherTextView;
    private TextView pagesCountTextView;
    private TextView yearTextView;
    private TextView availabilityStatus;
    private RatingBar ratingBar;
    private ProgressBar progressBar;
    private Button backBtn;
    private Button borrowBtn;
    private ImageView infoBtn;
    private BookViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_view, container, false);
       initComponents(view);
        //todo uncomment when will be added progressBar
        // progressBar = view.findViewById(R.id.progressBar);
       presenter = new BookViewFragmentPresenter(this, this.getArguments().getInt("idBook"));
       setOnClickListeners();
        return view;
    }



    private void setOnClickListeners() {
        infoBtn.setOnClickListener(infoOnClickListener);
        backBtn.setOnClickListener(backOnClickListener);
        borrowBtn.setOnClickListener(borrowOnClickListener);
    }

    private final View.OnClickListener infoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity().getApplicationContext()).create();
            alertDialog.setTitle("Information");
            alertDialog.setMessage("Five books you can borrow and two books is available to reading in the library");
            // Alert dialog button
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Alert dialog action goes here
                            // onClick button code here
                            dialog.dismiss();// use dismiss to cancel alert dialog
                        }
                    });
            alertDialog.show();
        }
    };


    private final View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openHomeFragment();
        }
    };

    private final View.OnClickListener borrowOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo open dialog
        }
    };

    

    private void openHomeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().
        replace(((ViewGroup) getView().getParent()).getId(),new HomeFragment())
        .addToBackStack(getView().getClass().getName())
        .commit();
    }

    private void initComponents(View view) {
        imageView = view.findViewById(R.id.imageView);
        titleTextView = view.findViewById(R.id.BookView_text_title);
        authorTextView = view.findViewById(R.id.BookView_text_Author);
        publisherTextView = view.findViewById(R.id.BookView_text_Publisher);
        pagesCountTextView = view.findViewById(R.id.BookView_text_pages);
        yearTextView = view.findViewById(R.id.BookView_text_year);
        availabilityStatus = view.findViewById(R.id.BookView_text_available);
        ratingBar = view.findViewById(R.id.ratingBar);
        infoBtn = view.findViewById(R.id.BookView_image_info);
        backBtn = view.findViewById(R.id.BookView_button_back);
        borrowBtn=view.findViewById(R.id.BookView_button_borrow);
    }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
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
       Picasso.with(getContext()).load(uriImage).into(imageView);
    }

    @Override
    public void setDate(String date) {
        yearTextView.setText(date);
    }

    @Override
    public void setAvailabilyty(String status) {
        availabilityStatus.setText(status);
    }

    @Override
    public void setStars(double number) {
       ratingBar.setRating((float) number);
    }

    @Override
    public void startProgressBar() {
           // progressBar.setIndeterminate(true);
    }

    @Override
    public void endProgressBar() {
        // progressBar.setIndeterminate(false);
    }

}
