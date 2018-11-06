package com.example.a14512.smallnote.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.base.BaseActivity;
import com.example.a14512.smallnote.mode.Note;
import com.example.a14512.smallnote.presenter.EditPresenter;
import com.example.a14512.smallnote.view.IEditView;

/**
 * @author 14512 on 2018/11/5
 */
public class EditActivity extends BaseActivity<EditPresenter> implements IEditView {


    public static void startActivity(Context context, Note note) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("note", note);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    protected EditPresenter createPresenter() {
        return new EditPresenter(this);
    }

    @Override
    public String getNoteTitle() {
        return null;
    }

    @Override
    public String getNoteContent() {
        return null;
    }

    @Override
    public void setNoteTitle(String title) {

    }

    @Override
    public void setNoteContent(String content) {

    }

}
