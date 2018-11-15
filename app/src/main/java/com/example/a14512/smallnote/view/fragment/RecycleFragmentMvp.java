package com.example.a14512.smallnote.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.base.BaseFragmentMvp;
import com.example.a14512.smallnote.mode.Note;
import com.example.a14512.smallnote.presenter.RecyclePresenter;
import com.example.a14512.smallnote.view.IRecycleView;

import java.util.ArrayList;

/**
 * @author 14512 on 2018/11/5
 */
public class RecycleFragmentMvp extends BaseFragmentMvp<RecyclePresenter> implements IRecycleView {
    private RecyclerView mRecyclerView;

    @Override
    protected RecyclePresenter createPresenter() {
        return new RecyclePresenter(this);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recycle;
    }

    @Override
    public void setNotes(ArrayList<Note> notes) {

    }
}
