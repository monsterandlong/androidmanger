package com.gfd.phone.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gfd.phone.R;


public class GuidePagerAdapter extends PagerAdapter{

    private static final int[] images = {R.mipmap.guide01,R.mipmap.guide02,R.mipmap.guide03};
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView image = new ImageView(container.getContext());
        //设置充满全屏
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        image.setBackgroundResource(images[position]);
        container.addView(image);//将item添加到ViewPager中
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }
}
