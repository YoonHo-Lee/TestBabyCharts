package com.example.tacademy.testbabycharts;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class BabyItemView extends FrameLayout {
    public BabyItemView(Context context) {
        super(context);
        init();
    }

    ImageView iconView;
    TextView nameView, birthView;
    BabyItem mItem;
    ImageLoader imageLoader;

    DisplayImageOptions options;

    private void init() {

        inflate(getContext(), R.layout.view_movie_item, this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        nameView = (TextView)findViewById(R.id.text_name);
        birthView = (TextView)findViewById(R.id.text_birth);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(50))
                .build();

    }

    public void setMovieItem(BabyItem item) {
        nameView.setText(item.name);
        birthView.setText(item.birth+"");

        ImageLoader.getInstance().displayImage(item.image, iconView, options);
    }
}
