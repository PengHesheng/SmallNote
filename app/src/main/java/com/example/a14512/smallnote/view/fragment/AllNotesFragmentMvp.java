package com.example.a14512.smallnote.view.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.a14512.smallnote.C;
import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.adapter.AllNotesAdapter;
import com.example.a14512.smallnote.adapter.OnRecyclerListener;
import com.example.a14512.smallnote.base.BaseFragmentMvp;
import com.example.a14512.smallnote.mode.Note;
import com.example.a14512.smallnote.presenter.AllNotesPresenter;
import com.example.a14512.smallnote.utils.LogUtil;
import com.example.a14512.smallnote.view.IAllNotesView;
import com.example.a14512.smallnote.view.activity.EditActivityMvp;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * @author 14512 on 2018/11/5
 */
public class AllNotesFragmentMvp extends BaseFragmentMvp<AllNotesPresenter> implements IAllNotesView {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mActionButton;
    private AllNotesAdapter mAdapter;

    @Override
    protected AllNotesPresenter createPresenter() {
        return new AllNotesPresenter(this);
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerAllNotes);
        mActionButton = view.findViewById(R.id.floatingAcBtn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AllNotesAdapter(RecyclerView.VERTICAL);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getNotes(getContext());
        mAdapter.setListener(new OnRecyclerListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), EditActivityMvp.class);
                intent.putExtra(C.NOTE_INTENT_KEY, mAdapter.getNote(position));
                startActivityForResult(intent, RESULT_OK);
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                return true;
            }
        });
        mActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), EditActivityMvp.class);
            startActivityForResult(intent, RESULT_OK);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all_notes;
    }

    @Override
    public void setDatas(ArrayList<Note> notes) {
        mAdapter.addNotes(notes);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO onActivityResult error
        LogUtil.e(requestCode + "  " + resultCode);
        if (requestCode == RESULT_OK) {
            switch (resultCode) {
                case C.RESULT_NOTE:
                    int pos = data.getIntExtra(C.NOTE_POSITION, mAdapter.getItemCount());
                    mAdapter.updateNote(pos, (Note) data.getSerializableExtra(C.NOTE_INTENT_KEY));
                    break;
                case C.RESULT_NEW_NOTE:
                    mAdapter.addNote((Note) data.getSerializableExtra(C.NOTE_INTENT_KEY));
                    LogUtil.e(((Note) data.getSerializableExtra(C.NOTE_INTENT_KEY)).getContent());
                    break;
                default:
                    break;
            }
        }
    }
}
