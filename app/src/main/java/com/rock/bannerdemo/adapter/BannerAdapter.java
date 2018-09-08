package com.rock.bannerdemo.adapter;

import android.view.View;

/**
 * Created by Rock on 2018/9/4.
 */

public abstract class BannerAdapter {
    /**
     * 根据位置获取ViewPager的子View
     *
     * @param position
     * @return
     */
    public abstract View getView(int position, View convertView);

    /**
     * 返回数量
     *
     * @return
     */
    public abstract int getCount();
}
