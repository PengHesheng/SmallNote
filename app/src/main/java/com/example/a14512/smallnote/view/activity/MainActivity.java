package com.example.a14512.smallnote.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.view.fragment.AllNotesFragment;
import com.example.a14512.smallnote.view.fragment.RecycleFragment;
import com.example.a14512.smallnote.view.fragment.SettingFragment;

/**
 * @author 14512
 */
public class MainActivity extends AppCompatActivity {
    private NavigationView mNavigationView;
    private AllNotesFragment mAllNotesFragment;
    private RecycleFragment mRecycleFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
    }

    private void onClick() {
        LinearLayout navHeader = (LinearLayout) mNavigationView.getHeaderView(0);
        navHeader.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        mNavigationView.setCheckedItem(0);
        mNavigationView.setNavigationItemSelectedListener(item -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.allNotes:
                    if (mAllNotesFragment == null) {
                        mAllNotesFragment = new AllNotesFragment();
                    }
                    transaction.replace(R.id.frameLayoutMain, mAllNotesFragment);
                    break;
                case R.id.recycle:
                    if (mRecycleFragment == null) {
                        mRecycleFragment = new RecycleFragment();
                    }
                    transaction.replace(R.id.frameLayoutMain, mRecycleFragment);
                    break;
                case R.id.settings:
                    if (mSettingFragment == null) {
                        mSettingFragment = new SettingFragment();
                    }
                    transaction.replace(R.id.frameLayoutMain, mSettingFragment);
                    break;
                default:
                    break;
            }
            transaction.commit();
            return true;
        });
    }

    private void initView() {
        mNavigationView = findViewById(R.id.navMain);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mAllNotesFragment = new AllNotesFragment();
        transaction.replace(R.id.frameLayoutMain, mAllNotesFragment);
        transaction.commit();
    }
}
