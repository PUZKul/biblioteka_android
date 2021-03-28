package kul.pl.biblioteka.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.adapter.homeList.HomeListRecycleViewAdapter;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.ui.fragments.bookView.BookViewFragment;

public class HomeFragment extends Fragment implements HomeFragmentContract.View {

    private ImageButton sortBtn;
    private PopupMenu menu;
    private RecyclerView recyclerView;
    private SearchView searchExitText;
    private HomeFragmentPresenter presenter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);
        setOnClickListener();
        presenter = new HomeFragmentPresenter(this);
        presenter.setListTopBooks();
        presenter.setPaginationComponent(view);
        return view;
    }

    private void setOnClickListener() {
        sortBtn.setOnClickListener(sortButtonOnClick);
    }

    private View.OnClickListener sortButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menu.setOnMenuItemClickListener(menuOnClickListener);
            menu.show();
        }
    };

    private PopupMenu.OnMenuItemClickListener menuOnClickListener =
            new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.bottom_sort_title) {
                        presenter.setListSortByTitle();
                    } else if (item.getItemId()  == R.id.bottom_sort_date) {
                        presenter.setListSortByDate();
                    } else if (item.getItemId()  == R.id.bottom_sort_discover) {
                        presenter.setListSortByDiscover();
                    } else if (item.getItemId()  == R.id.bottom_sort_ranting) {
                        presenter.setListSortByRating();
                    } else {
                        presenter.setListTopBooks();
                    }
                    return true;
                }
            };

    private void initComponents(View view) {
        sortBtn = view.findViewById(R.id.home_btn_sort);
        menu = new PopupMenu(view.getContext(), sortBtn);
        menu.getMenuInflater().inflate(R.menu.sort_list_menu, menu.getMenu());
        recyclerView = view.findViewById(R.id.home_recycleView);
        searchExitText = view.findViewById(R.id.home_searchView_search);
        progressBar=view.findViewById(R.id.home_progressBar);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setList(List<BookModel> booksList) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new HomeListRecycleViewAdapter(getContext(), booksList, onItemClickListener));
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(int idBook) {
            openBookViewFragment(idBook);
        }
    };

    private void openBookViewFragment(int idBook) {
        getActivity().getSupportFragmentManager().beginTransaction().
                add(((ViewGroup) getView().getParent()).getId(),getBookViewFragmentWithSetArguments(idBook))
                .addToBackStack(getView().getClass().getName())
                .commit();
    }

    private BookViewFragment getBookViewFragmentWithSetArguments(int argument) {
        BookViewFragment bookViewFragment = new BookViewFragment();
        bookViewFragment.setArguments(getBundleAndPutString(argument));
        return bookViewFragment;
    }

    private Bundle getBundleAndPutString(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt("idBook", argument);
        return bundle;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getSearchText() {
        return searchExitText.toString();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}