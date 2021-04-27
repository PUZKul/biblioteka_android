package kul.pl.biblioteka.ui.dialogs.copiesOfBooks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.VerticalSpaceItemDecoration;
import kul.pl.biblioteka.adapter.copiesOfBookList.CopiesOfBookListRecycleViewAdapter;
import kul.pl.biblioteka.models.CopiesOfBookModel;

public class CopiesOfBooksDialog extends AppCompatDialogFragment implements CopiesOfBooksDialogContract.View{

    private RecyclerView recyclerView;
    private Button back;
    private Button borrow;
    private CopiesOfBooksDialogPresenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_copies_of_books,null);
        initComponents(view);
        presenter=new CopiesOfBooksDialogPresenter(this,this.getArguments().getInt("id"));
        setOnClickListener();
        builder.setView(view);
        return builder.create();
    }

    private void setOnClickListener(){
        back.setOnClickListener(onBackClicked);
        borrow.setOnClickListener(onBorrowClicked);
    }

    private View.OnClickListener onBackClicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        //todo back to bookView fragment
        }
    };

    private View.OnClickListener onBorrowClicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo borrow book
        }
    };

    private void initComponents(View view) {
        back=view.findViewById(R.id.chose_book_option_btn_cancel);
        borrow=view.findViewById(R.id.chose_book_option_btn_confirm);
        recyclerView=view.findViewById(R.id.chose_book_option_btn_recycle_view);
    }

    @Override
    public void setList(List<CopiesOfBookModel> books) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new CopiesOfBookListRecycleViewAdapter(getContext(), books));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }
}
