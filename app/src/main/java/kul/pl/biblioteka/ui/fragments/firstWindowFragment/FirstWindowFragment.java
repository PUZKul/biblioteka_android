package kul.pl.biblioteka.ui.fragments.firstWindowFragment;

import android.content.Intent;
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
import kul.pl.biblioteka.adapter.VerticalSpaceItemDecoration;
import kul.pl.biblioteka.adapter.homeList.HomeListRecycleViewAdapter;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.ui.activity.noInternet.NoInternetActivity;
import kul.pl.biblioteka.ui.fragments.bookView.BookViewFragment;


public class FirstWindowFragment extends Fragment implements FirstWindowFragmentContract.View{

    private ImageButton sortBtn;
    private PopupMenu menu;
    private RecyclerView recommendedRecyclerView;
    private RecyclerView theMostPopularRecyclerView;
    private SearchView searchExitText;
    private FirstWindowFragmentPresenter presenter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_window_fragment, container, false);
        initComponents(view);
        setAdapters();
        setOnClickListener();
        presenter = new FirstWindowFragmentPresenter(this);
        presenter.setListTopBooks();
        presenter.setPaginationComponent(view);
        return view;
    }

    private void initComponents(View view) {
        sortBtn=view.findViewById(R.id.first_window_btn_sort);
        menu=new PopupMenu(view.getContext(), sortBtn);
        recommendedRecyclerView=view.findViewById(R.id.first_window_recycle_view1);
        theMostPopularRecyclerView=view.findViewById(R.id.first_window_recycle_view2);
        searchExitText=view.findViewById(R.id.first_window_searchView_search);
        progressBar=view.findViewById(R.id.first_window_progressBar);
    }

    private void setOnClickListener() {
        sortBtn.setOnClickListener(sortButtonOnClick);
        searchExitText.setOnQueryTextListener(searchOnSearchListener);
    }

    private SearchView.OnQueryTextListener searchOnSearchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {

            return false;
        }
    };

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
                    } else if (item.getItemId() == R.id.bottom_sort_date) {
                        presenter.setListSortByDate();
                    } else if (item.getItemId() == R.id.bottom_sort_discover) {
                        presenter.setListSortByDiscover();
                    } else if (item.getItemId() == R.id.bottom_sort_ranting) {
                        presenter.setListSortByRating();
                    } else {
                        presenter.setListTopBooks();
                    }
                    return true;
                }
            };

    private void setAdapters() {
        theMostPopularRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        theMostPopularRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setList(List<BookModel> booksList) {
        theMostPopularRecyclerView.setAdapter(new HomeListRecycleViewAdapter(getContext(), booksList, onItemClickListener));
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(int idBook) {
            openBookViewFragment(idBook);
        }
    };

    private void openBookViewFragment(int idBook) {
        getActivity().getSupportFragmentManager().beginTransaction().
                add(((ViewGroup) getView().getParent()).getId(), getBookViewFragmentWithSetArguments(idBook))
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
        bundle.putInt(getString(R.string.idBook), argument);
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

    @Override
    public void openOnInternetActivity() {
        Intent intent = new Intent(getActivity(), NoInternetActivity.class);
        startActivity(intent);
    }
}
