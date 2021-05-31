package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Scanner;

import static com.example.collectxnew.R.*;


public class MainActivity extends AppCompatActivity  {

    GridLayout mainGrid;
    private GestureDetectorCompat mdetector;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    DrawerLayout drawer;

    ViewPager pager;
    TabLayout mTabLayout;
    TabItem firstItem, secondItem, thirdItem;
   /* PagerAdapter adapter;*/


    private ImageButton btn_scanner;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        View myView = findViewById(id.viewPager);
        mAuth = FirebaseAuth.getInstance();
        /*mdetector = new GestureDetectorCompat(this, new MyGestureListener());*/



        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

        drawerLayout = findViewById(id.drawer_layout);
        toolbar = findViewById(id.toolbar);
        frameLayout = findViewById(id.fragment_container);
        navigationView = findViewById(id.nav_drawer);
        drawer = findViewById(id.drawer_layout);


        pager = findViewById(id.viewPager);
        mTabLayout = findViewById(id.tablayout);
        firstItem = findViewById(id.firstitem);
        secondItem = findViewById(id.seconditem);
        thirdItem = findViewById(id.thirditem);
        btn_scanner = findViewById(id.img_button_scanner);

        Load_setting();




      /*  adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayout.getTabCount());
        pager.setAdapter(adapter);*/

       /* mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent in = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(in);
                        break;
                    case 2:
                        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(i);
                        break;
                }
                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

      pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));*/

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {

                    case id.nav_profile:
                        Intent p = new Intent(MainActivity.this, ProfileInformation.class);
                        startActivity(p);
                        break;
                    case id.nav_scanning:
                        Intent sc = new Intent(MainActivity.this, ScanningQRActivity.class);
                        startActivity(sc);
                        break;
                    case id.nav_chat:
                        Intent c = new Intent(MainActivity.this, ChattingActivity.class);
                        startActivity(c);
                        break;

                    case id.nav_notification:
                        Intent n = new Intent(MainActivity.this, CreatingNotification.class);
                        startActivity(n);

                        break;

                    case id.nav_help:
                        Intent h = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(h);
                        break;

                    case id.nav_aboutus:
                        Intent a = new Intent(MainActivity.this, AboutusActivity.class);
                        startActivity(a);
                        break;

                    case id.nav_setting:
                        Intent s = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(s);
                        break;

                    case id.nav_logout:


                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
                        builder.setTitle("Are you sure you want to log out? ");



                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                        builder.show();




                        /*
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));*/


                        break;
                }


                return false;
            }
        });


        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerIntent = new Intent(MainActivity.this, ScanningQRActivity.class);
                startActivity(scannerIntent);

            }
        });




    }

    private void Load_setting(){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean chk_night = sp.getBoolean("NIGHT", false);
        if (chk_night) {
            drawer.setBackgroundColor(Color.parseColor("#222222"));

        } else {
            drawer.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        String orien = sp.getString("ORIENTATION", "false");
        if ("1".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);


        } else if ("2".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        } else if ("3".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }

    }

    private void setSingleEvent(GridLayout mainGrid){
        for (int i =0; i<mainGrid.getChildCount();i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0)
                    {
                        Intent intent = new Intent(MainActivity.this, NewDisplayCustomer.class);
                        startActivity(intent);
                    }else if (finalI == 1)
                    {
                        Intent intent = new Intent(MainActivity.this, Example.class);
                        startActivity(intent);
                    }else if (finalI == 2)
                    {
                        Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                        startActivity(intent);
                    }else if (finalI == 3)
                    {
                        Intent intent = new Intent(MainActivity.this, QRcodeActivity.class);
                        startActivity(intent);
                    }else if (finalI == 4)
                    {
                        Intent intent = new Intent(MainActivity.this, EMICalculatorActivity.class);
                        startActivity(intent);
                    }else if (finalI == 5)
                    {
                        Intent intent = new Intent(MainActivity.this, Daily_Collection.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }

    @Override
    protected void onResume() {
        Load_setting();
        super.onResume();
    }

    /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mdetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }*/

 /*   class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;

        }

        private void onSwipeLeft() {
            Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        private void onSwipeRight() {
            Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        private void onSwipBottom() {
            Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
        }

        private void onSwipeTop() {
            Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
        }

        public boolean onTouch(View v, MotionEvent event) {
            return mdetector.onTouchEvent(event);
        }

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            public void onSwipeRight(){
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
            public void onSwipeLeft(){
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);

            }
            public void onSwipeBottom(){
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();

            }public void onSwipeTop(){
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();

            }
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mdetector.onTouchEvent(event);
            }
        };

    }*/





    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


  /* public void homeclick(View view) {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void historyclick(View view) {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);

    }

    public void profileclick(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }*/
}




