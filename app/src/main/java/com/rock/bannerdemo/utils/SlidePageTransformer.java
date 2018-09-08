package com.rock.bannerdemo.utils;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Rock on 2018/9/4.
 */

public class SlidePageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position > 0 && position <= 1) {
            page.setPivotX(0);
            page.setScaleX(1 - position);
        } else if (position >= -1 && position < 0) {
            page.setPivotX(page.getWidth());
            page.setScaleX(1 + position);
        }
    }
}
