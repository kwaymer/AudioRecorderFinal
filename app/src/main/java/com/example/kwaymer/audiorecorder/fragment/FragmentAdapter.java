package com.example.kwaymer.audiorecorder.fragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kwaymer.audiorecorder.R;

/**
 * Created by kwaymer on 4/12/17.
 */

public class FragmentAdapter extends PagerAdapter {
    private int[] images = {R.drawable.chance,R.drawable.dumbfoundead,R.drawable.gambino};
    private Context context;
    private LayoutInflater layoutInflater;

    public FragmentAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return (view==(LinearLayout)o);
        //casting o is redundant but error is returned if not done this way

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
            ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);
            TextView textView = (TextView)item_view.findViewById(R.id.image_count);
            imageView.setImageResource(images[position]);
            textView.setText("Rapper : " +position);
            container.addView(item_view);

        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((LinearLayout)object);
    }
}
