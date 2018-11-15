package com.example.a14512.smallnote.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.a14512.smallnote.C;
import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.base.BaseActivityMvp;
import com.example.a14512.smallnote.mode.Note;
import com.example.a14512.smallnote.presenter.EditPresenter;
import com.example.a14512.smallnote.utils.ImageUtil;
import com.example.a14512.smallnote.utils.LogUtil;
import com.example.a14512.smallnote.utils.ToastUtil;
import com.example.a14512.smallnote.view.IEditView;

/**
 * @author 14512 on 2018/11/5
 */
public class EditActivityMvp extends BaseActivityMvp<EditPresenter> implements IEditView {
    private Note mNote;
    private int mNotePos;
    private EditText mEdtTitle, mEdtContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getData();
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setSubtitle("写便签");
        mEdtTitle = findViewById(R.id.edtTitleEdit);
        mEdtContent = findViewById(R.id.edtContentEdit);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //让图标按钮展示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置图标在toolbar上
            actionBar.setHomeAsUpIndicator(R.drawable.left);
        }
    }

    private void getData() {
        mNote = (Note) getIntent().getSerializableExtra(C.NOTE_INTENT_KEY);
        if (mNote != null) {
            mNotePos = getIntent().getIntExtra(C.NOTE_POSITION, 0);
            setNoteTitle(mNote.getTitle());
            setNoteContent(mNote.getContent());
        } else {
            mNote = new Note();
            mNotePos = -1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.addImg:
                checkPermission();
                openAlbum();
                break;
            case R.id.repeal:
                mPresenter.repeal();
                break;
            case R.id.recover:
                mPresenter.recovery();
                break;
            case R.id.save:
                mPresenter.save(mNote, mNotePos < 0);
                break;
            default:
                break;
        }
        return true;
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, C.RESULT_CHOOSE_PHOTO);
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(EditActivityMvp.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditActivityMvp.this,
                    new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    ToastUtil.show(this, "You denied the permission");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C.RESULT_CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    addImg(data);
                }
                break;
            default:
                break;
        }
    }

    private void addImg(Intent data) {
        String path = ImageUtil.getImagePath(this, data);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(path);
//        Bitmap bitmap = ImageResizerUtil.deFromFile(path, 200, 200);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ImageSpan imageSpan = new ImageSpan(this, bitmap,
                DynamicDrawableSpan.ALIGN_BOTTOM);
        stringBuilder.setSpan(imageSpan, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Editable editable = mEdtContent.getText();
        int start = mEdtContent.getSelectionStart();
        editable.insert(start, stringBuilder);
        mEdtContent.setText(editable);
        mEdtContent.setSelection(start + stringBuilder.length());
    }

    @Override
    protected EditPresenter createPresenter() {
        return new EditPresenter(this);
    }

    @Override
    public String getNoteTitle() {
        return mEdtTitle.getText().toString();
    }

    @Override
    public String getNoteContent() {
        return mEdtContent.getText().toString();
    }

    @Override
    public void setNoteTitle(String title) {
        mEdtTitle.setText(title);
    }

    @Override
    public void setNoteContent(String content) {
        mEdtContent.setText(content);
    }

    @Override
    public void finishWithResult(Note note, boolean isNew) {
        LogUtil.e("finish");
        if (isNew) {
            setResult(C.RESULT_NEW_NOTE, new Intent().putExtra(C.NOTE_INTENT_KEY, note));
        } else {
            setResult(C.RESULT_NOTE, new Intent().putExtra(C.NOTE_INTENT_KEY, note)
                    .putExtra(C.NOTE_POSITION, mNotePos));
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        mPresenter.save(mNote, mNotePos < 0);
    }
}
