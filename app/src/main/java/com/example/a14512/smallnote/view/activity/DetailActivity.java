package com.example.a14512.smallnote.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a14512.smallnote.C;
import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.base.BaseActivity;
import com.example.a14512.smallnote.mode.Note;
import com.example.a14512.smallnote.view.IDetailView;

/**
 * @author 14512 on 2018/11/12
 */
public class DetailActivity extends BaseActivity implements IDetailView {
    private TextView mTvTitle, mTvContent, mTvDate;
    private int mPos = -1;

    public static void starActivityForResult(Activity activity, Note note, int pos, int requestCode) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(C.NOTE_INTENT_KEY, note);
        intent.putExtra(C.NOTE_POSITION, pos);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView imgBack = findViewById(R.id.imgToolbarLeft);
        ImageView imgDel = findViewById(R.id.imgToolbarRight);
        mTvTitle = findViewById(R.id.tvTitleShow);
        mTvContent = findViewById(R.id.tvContentShow);
        mTvDate = findViewById(R.id.tvDateShow);
        setSupportActionBar(toolbar);
        imgBack.setVisibility(View.VISIBLE);
        imgDel.setVisibility(View.VISIBLE);
        getData();
        imgBack.setOnClickListener(v -> finish());
        imgDel.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra(C.NOTE_POSITION, mPos));
            finish();
        });
    }

    private void getData() {
        if (getIntent() != null) {
            Note note = (Note) getIntent().getSerializableExtra(C.NOTE_INTENT_KEY);
            mPos = getIntent().getIntExtra(C.NOTE_POSITION, -1);
            showNote(note);
        }
    }

    @Override
    public void showNote(Note note) {
        mTvTitle.setText(note.getTitle());
        mTvContent.setText(note.getContent());
        mTvDate.setText(String.valueOf(note.getDate()));
    }
}
