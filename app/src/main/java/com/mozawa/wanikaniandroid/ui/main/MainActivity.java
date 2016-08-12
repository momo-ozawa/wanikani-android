package com.mozawa.wanikaniandroid.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.WaniKaniManager;
import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.WebViewActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.util.CircleTextView;
import com.mozawa.wanikaniandroid.ui.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainMvpView {

    // Toolbar
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    // Available Now
    @BindView(R.id.lessonsCircleTextView)
    CircleTextView lessonsCircleTextView;
    @BindView(R.id.lessonsButton)
    Button lessonsButton;
    @BindView(R.id.reviewsCircleTextView)
    CircleTextView reviewsCircleTextView;
    @BindView(R.id.reviewsButton)
    Button reviewsButton;

    // Coming Up
    @BindView(R.id.reviewDateTextView)
    TextView reviewDateTextView;
    @BindView(R.id.nextHourCircleTextView)
    CircleTextView nextHourCircleTextView;
    @BindView(R.id.nextDayCircleTextView)
    CircleTextView nextDayCircleTextView;

    // Critical Items
    @BindView(R.id.criticalItemsRecyclerView)
    RecyclerView criticalItemsRecyclerView;

    private MainPresenter presenter;
    private CriticalItemsAdapter criticalItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        toolbar.setTitle("Dashboard");

        presenter = new MainPresenter(new WaniKaniManager());
        presenter.attachView(this);

        presenter.loadStudyQueue();
        presenter.loadCriticalItems();

        lessonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toolbarTitle = "Lessons";
                String url = "https://www.wanikani.com/lesson/session";
                startActivity(WebViewActivity.getStartIntent(MainActivity.this, toolbarTitle, url));
            }
        });

        reviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toolbarTitle = "Reviews";
                String url = "https://www.wanikani.com/review/session";
                startActivity(WebViewActivity.getStartIntent(MainActivity.this, toolbarTitle, url));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadStudyQueue();
        Log.d("momo", "in on Resume");
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showAvailableStudyQueue(StudyQueue studyQueue) {
        lessonsCircleTextView.setText(studyQueue.studyQueueInformation.lessonsAvailable + "");
        reviewsCircleTextView.setText(studyQueue.studyQueueInformation.reviewsAvailable + "");
    }

    @Override
    public void showReviewStudyQueue(StudyQueue studyQueue) {
        long nextReviewDate = studyQueue.studyQueueInformation.nextReviewDate;
        reviewDateTextView.setText("Reviews available " + TimeUtil.getTimeUntil(nextReviewDate));
        nextHourCircleTextView.setText(studyQueue.studyQueueInformation.reviewsAvailableNextHour + "");
        nextDayCircleTextView.setText(studyQueue.studyQueueInformation.reviewsAvailableNextDay + "");
    }

    @Override
    public void showCriticalItems(CriticalItems criticalItems) {
//        CriticalItems.CriticalItemInformation firstItem = criticalItems.criticalItemsInformationList.get(0);
//        criticalItemTextView.setText(firstItem.character + " " + firstItem.meaning);

        criticalItemsAdapter = new CriticalItemsAdapter();
        criticalItemsAdapter.setCriticalItemInformationList(criticalItems.criticalItemsInformationList);

        criticalItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        criticalItemsRecyclerView.setAdapter(criticalItemsAdapter);
        criticalItemsAdapter.notifyDataSetChanged();
    }
}
