package com.example.a14512.smallnote.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.view.fragment.AllNotesFragmentMvp;
import com.example.a14512.smallnote.view.fragment.RecycleFragmentMvp;
import com.example.a14512.smallnote.view.fragment.SettingFragment;

/**
 * @author 14512
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ImageView mImgMenu;
    private TextView mTvTitle;
    private AllNotesFragmentMvp mAllNotesFragment;
    private RecycleFragmentMvp mRecycleFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
    }

    private void onClick() {
        mImgMenu.setOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.START));
        LinearLayout navHeader = (LinearLayout) mNavigationView.getHeaderView(0);
        navHeader.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LoginActivityMvp.class)));
        mNavigationView.setCheckedItem(R.id.allNotes);
        mNavigationView.setNavigationItemSelectedListener(item -> {
            mTvTitle.setText(item.getTitle());
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.allNotes:
                    if (mAllNotesFragment == null) {
                        mAllNotesFragment = new AllNotesFragmentMvp();
                    }
                    transaction.replace(R.id.frameLayoutMain, mAllNotesFragment);
                    break;
                case R.id.recycle:
                    if (mRecycleFragment == null) {
                        mRecycleFragment = new RecycleFragmentMvp();
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
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        mImgMenu = findViewById(R.id.imgToolbarLeft);
        mTvTitle = findViewById(R.id.tvToolbarTitle);
        mDrawerLayout = findViewById(R.id.drawerLayoutMain);
        mNavigationView = findViewById(R.id.navMain);
        setSupportActionBar(toolbar);
        mImgMenu.setImageResource(R.drawable.menu);
        mImgMenu.setVisibility(View.VISIBLE);
        mTvTitle.setText(R.string.menu_all_notes);
        mTvTitle.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mAllNotesFragment = new AllNotesFragmentMvp();
        transaction.replace(R.id.frameLayoutMain, mAllNotesFragment);
        transaction.commit();
    }

}
