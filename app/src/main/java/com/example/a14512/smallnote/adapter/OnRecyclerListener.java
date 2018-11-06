package com.example.a14512.smallnote.adapter;

import android.view.View;

/**
 * @author 14512 on 2018/11/5
 */
public interface OnRecyclerListener {

    /**
     * click
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * long click
     * @param view
     * @param position
     */
    boolean onItemLongClick(View view, int position);
}
