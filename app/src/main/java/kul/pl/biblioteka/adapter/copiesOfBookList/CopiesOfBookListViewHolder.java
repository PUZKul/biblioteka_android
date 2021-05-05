package kul.pl.biblioteka.adapter.copiesOfBookList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class CopiesOfBookListViewHolder extends RecyclerView.ViewHolder {

    private TextView bookId;
    private TextView status;
    private ImageView image;
    private OnItemClickListener onItemClickListener;
    private View view;
    private Balloon balloon;
    private ImageView informationImage;

    public CopiesOfBookListViewHolder(@NonNull View itemView) {
        super(itemView);
        setComponents(itemView);
        view = itemView;
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        informationImage.setOnClickListener(onImageClicked);
    }

    private View.OnClickListener onImageClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            balloon.show(v);
        }
    };

    private void setComponents(View view) {
        bookId = view.findViewById(R.id.CopiesOfBook_text_copyNumber);
        status = view.findViewById(R.id.CopiesOfBook_text_status);
        image = view.findViewById(R.id.CopiesOfBook_image_bookmark);
        informationImage=view.findViewById(R.id.CopiesOfBook_image_info);
    }

    public void setImage(int image) {
        this.image.setBackgroundResource(image);
    }

    public void setBookId(String bookId) {
        this.bookId.setText(bookId);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void setBalloon(String message) {
        balloon = new Balloon.Builder(MainActivity.getAppContext())
                .setArrowVisible(false)
                .setWidthRatio(1.0f)
                .setHeight(78)
                .setTextSize(18f)
                .setArrowPosition(0.62f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText(message)
                .setMarginBottom(5)
                .setMarginLeft(16)
                .setMarginRight(16)
                .setTextColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorWhite))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.getAppContext(), R.color.colorPrimaryGreen))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .setAutoDismissDuration(5000L)
                .build();
    }

}
