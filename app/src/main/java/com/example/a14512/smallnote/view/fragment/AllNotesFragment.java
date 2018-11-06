package com.example.a14512.smallnote.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.adapter.AllNotesAdapter;
import com.example.a14512.smallnote.adapter.OnRecyclerListener;
import com.example.a14512.smallnote.base.BaseFragment;
import com.example.a14512.smallnote.mode.Note;
import com.example.a14512.smallnote.presenter.AllNotesPresenter;
import com.example.a14512.smallnote.view.IAllNotesView;
import com.example.a14512.smallnote.view.activity.EditActivity;

import java.util.ArrayList;

/**
 * @author 14512 on 2018/11/5
 */
public class AllNotesFragment extends BaseFragment<AllNotesPresenter> implements IAllNotesView {
    private RecyclerView mRecyclerView;
    private AllNotesAdapter mAdapter;

    @Override
    protected AllNotesPresenter createPresenter() {
        return new AllNotesPresenter(this);
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerAllNotes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AllNotesAdapter(RecyclerView.VERTICAL);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getNotes();
        mAdapter.setListener(new OnRecyclerListener() {
            @Override
            public void onItemClick(View view, int position) {
                EditActivity.startActivity(getContext(), mAdapter.getNote(position));
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                return false;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all_notes;
    }

    @Override
    public void setDatas(ArrayList<Note> notes) {
        mAdapter.setNotes(notes);
    }
}
