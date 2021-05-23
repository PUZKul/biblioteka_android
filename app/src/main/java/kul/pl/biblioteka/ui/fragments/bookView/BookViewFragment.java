package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.squareup.picasso.Picasso;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.dialogs.copiesOfBooks.CopiesOfBooksDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;
import kul.pl.biblioteka.ui.fragments.firstWindow.FirstWindowFragment;

public class BookViewFragment extends Fragment implements BookViewFragmentContract.View, NoInternetDialogListener {

    private ImageView imageView;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView publisherTextView;
    private TextView pagesCountTextView;
    private TextView yearTextView;
    private TextView availabilityStatus;
    private TextView numberOfStars;
    private RatingBar ratingBar;
    private ProgressBar progressBar;
    private Button backBtn;
    private Button borrowBtn;
    private Balloon balloon;
    private ImageView infoImage;
    private BookViewFragmentPresenter presenter;
    private NoInternetDialog noInternetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_view, container, false);
       initComponents(view);
        noInternetDialog=new NoInternetDialog(this);
       presenter = new BookViewFragmentPresenter(this, this.getArguments().getInt("idBook"));
       presenter.setBook();
       setOnClickListeners();
        return view;
    }

    private void setOnClickListeners() {
        backBtn.setOnClickListener(backOnClickListener);
        borrowBtn.setOnClickListener(borrowOnClickListener);
        infoImage.setOnClickListener(infoImageOnClickedListener);
    }

    private final View.OnClickListener infoImageOnClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            initBalloon("");
            balloon.show(v);
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
            if(InternetConnection.isConnection(MainActivity.getAppContext()))
            {
                CopiesOfBooksDialog dialog=new CopiesOfBooksDialog();
                Bundle bundle=new Bundle();
                bundle.putInt("id",presenter.getIdBook());
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(),presenter.getIdBook()+"");
            }
            else {
                openNoInternetDialog();
            }
        }
    };

    private void openHomeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().
        replace(((ViewGroup) getView().getParent()).getId(),new FirstWindowFragment())
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
        backBtn = view.findViewById(R.id.BookView_button_back);
        borrowBtn=view.findViewById(R.id.BookView_button_borrow);
        infoImage=view.findViewById(R.id.BookView_image_info);
        progressBar = view.findViewById(R.id.BookView_progressBar);
        numberOfStars=view.findViewById(R.id.BookView_text_numberOfStars);
    }

    private void initBalloon(String message) {
        balloon= new Balloon.Builder(MainActivity.getAppContext())
                .setArrowSize(12)
                .setArrowOrientation(ArrowOrientation.BOTTOM)
                .setArrowVisible(true)
                .setWidthRatio(1.0f)
                .setHeight(78)
                .setTextSize(18f)
                .setArrowPosition(0.83f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText(message)
                .setMarginLeft(16)
                .setMarginRight(16)
                .setMarginBottom(12)
                .setTextColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorWhite))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorPrimaryGreen))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .setAutoDismissDuration(5000L)
                .build();
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
    public void setNumberOfStars(String number) {
        numberOfStars.setText(number);
    }

    @Override
    public void setDate(String date) {
        yearTextView.setText(date);
    }

    @Override
    public void setAvailability(String status) {
        availabilityStatus.setText(status);
    }

    @Override
    public void setStars(double number) {
       ratingBar.setRating((float) number);
    }

    @Override
    public void startProgressBar() {
            progressBar.setIndeterminate(true);
    }

    @Override
    public void endProgressBar() {
         progressBar.setIndeterminate(false);
    }

    @Override
    public void openNoInternetDialog() {
        noInternetDialog.show(getActivity().getSupportFragmentManager(),getString(R.string.no_internet_dialog));
        noInternetDialog.setOnClickedBack();
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setBook();
                noInternetDialog.closeDialog();
            }
        },5000);
    }

    @Override
    public void showToast() {
        Toast.makeText(MainActivity.getAppContext(),R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }
}
