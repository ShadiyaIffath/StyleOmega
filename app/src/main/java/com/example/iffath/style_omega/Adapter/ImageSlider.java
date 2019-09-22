package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.iffath.style_omega.R;
import com.squareup.picasso.Picasso;

public class ImageSlider extends PagerAdapter {
    private String[] images;
    LayoutInflater inflater;
    private Context context;

    public ImageSlider(Context context, String[] images){
        this.context = context;
        this.images = images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = LayoutInflater.from(context);
        View imageLayout = inflater.inflate(R.layout.image_slider,container, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.image);


        Picasso.get()
                .load(images[position])
                .error(R.drawable.dressfail1)
                .placeholder(R.drawable.dressfail)
                .fit()
                .centerCrop()
                .into(imageView);

        container.addView(imageLayout, 0);

        return imageLayout;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
