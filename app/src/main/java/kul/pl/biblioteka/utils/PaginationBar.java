package kul.pl.biblioteka.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kul.pl.biblioteka.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PaginationBar {
    private final Context view;
    private LinearLayout panel;
    private ImageButton previous, next;
    private TextView[] textViews;
    private int currentPage;
    private int previousPage;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;

    public PaginationBar(Context context) {
        this.view = context;
        initComponents();
    }

    public void setPage(PageHolder<?> page){
        totalPages = page.getTotalPages();
        isFirst = page.isFirst();
        isLast = page.isLast();

        previousPage = currentPage;
        currentPage = page.getCurrentPage();
        update();
    }

    public void setOnPreviousClickListener(View.OnClickListener listener){
        previous.setOnClickListener(listener);
    }

    public void setOnNextClickListener(View.OnClickListener listener){
        next.setOnClickListener(listener);
    }

    public void setOnPageClickListener(View.OnClickListener listener){
        textViews[0].setOnClickListener(listener);
        textViews[1].setOnClickListener(listener);
        textViews[2].setOnClickListener(listener);
        textViews[3].setOnClickListener(listener);
        textViews[4].setOnClickListener(listener);
    }

    public int nextPage(){
        return currentPage + 1;
    }

    public int previousPage(){
        return currentPage - 1;
    }


    private void update(){
        setComponentsVisibility();
        if(totalPages >= 5){
            clearPreviousElement();
            showCurrentPage();
            setPageNumbers();
        }
        else {
            showCurrentPage2();
            setPageNumbers2();
        }
        previous.setClickable(!isFirst);
        next.setClickable(!isLast);
    }

    private void setPageNumbers2() {
        for(int i=1; i<4; i++)
            textViews[i].setText(String.valueOf(i + 1));
    }

    private void showCurrentPage2() {
        clearElement(textViews[previousPage]);
        emphasiseElement(textViews[currentPage]);
    }

    private void setPageNumbers() {
        if (currentPage <= 2){
            textViews[1].setText("2");
            textViews[2].setText("3");
            textViews[3].setText("4");
        }
        else if(currentPage < totalPages-2){
            // in the middle
            textViews[1].setText(String.valueOf(currentPage));     // one page back
            textViews[2].setText(String.valueOf(currentPage + 1)); // current page (+1 because we count from 0)
            textViews[3].setText(String.valueOf(currentPage + 2)); // one page forward
        }
        else if (currentPage == totalPages-1){
            textViews[1].setText(String.valueOf(totalPages - 3));
            textViews[2].setText(String.valueOf(totalPages-2));
            textViews[3].setText(String.valueOf(totalPages-1));
        }
        textViews[4].setText(String.valueOf(totalPages));
    }

    private void showCurrentPage() {
        if(currentPage == 0){
            emphasiseElement(textViews[0]);
        }
        else if (currentPage == 1){
            emphasiseElement(textViews[1]);
        }
        else if(currentPage == totalPages - 2){
            emphasiseElement(textViews[3]);
        }
        else if(currentPage == totalPages-1){
            emphasiseElement(textViews[4]);
        }
        else{
            emphasiseElement(textViews[2]);
        }
    }

    private void clearPreviousElement() {
        if(previousPage == 0){
            clearElement(textViews[0]);
        }
        else if (previousPage == 1){
            clearElement(textViews[1]);
        }
        else if(previousPage == totalPages - 2){
            clearElement(textViews[3]);
        }
        else if(previousPage == totalPages-1){
            clearElement(textViews[4]);
        }
        else{
            clearElement(textViews[2]);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void emphasiseElement(TextView textView){
        textView.setBackground(view.getDrawable(R.drawable.current_page));
        textView.setTextColor(view.getResources().getColor(R.color.colorWhite));
    }

    private void clearElement(TextView textView){
        textView.setBackground(null);
        textView.setTextColor(view.getResources().getColor(R.color.colorSecondaryDark));
    }

    private void setComponentsVisibility(){
        if(totalPages < 5){
            if(totalPages <= 1) panel.setVisibility(GONE);
            else {
                panel.setVisibility(VISIBLE);
                for(int i = 0; i<5; i++){
                    if(i<totalPages) textViews[i].setVisibility(VISIBLE);
                    else textViews[i].setVisibility(GONE);
                }
            }
        }
        else{
            for(int i = 0; i<5; i++){
                textViews[i].setVisibility(VISIBLE);
                panel.setVisibility(VISIBLE);
            }
        }
    }

    private void initComponents() {
        previous = ((Activity) view).findViewById(R.id.pageBar_btn_previous);
        next = ((Activity) view).findViewById(R.id.pageBar_btn_next);


        textViews = new TextView[5];
        textViews[0] = ((Activity) view).findViewById(R.id.pageBar_text_first);
        textViews[1] = ((Activity) view).findViewById(R.id.pageBar_text_middle1);
        textViews[2] = ((Activity) view).findViewById(R.id.pageBar_text_middle2);
        textViews[3] = ((Activity) view).findViewById(R.id.pageBar_text_middle3);
        textViews[4] = ((Activity) view).findViewById(R.id.pageBar_text_last);
        panel = ((Activity) view).findViewById(R.id.pageBar_linear_parent);
    }
}
