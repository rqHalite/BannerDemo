package com.rock.bannerdemo.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Rock on 2018/9/4.
 * 类似3d效果
 */

public class ScalePageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        float abs = Math.abs(position - 1 / 3f);
        float scale = (2 * (abs * abs));
        page.setScaleX(1 - scale);
        page.setScaleY(1 - scale);
    }
}