package com.example.googledeveloper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    private ViewPager viewPager;
    private LearnersFragment learnersFragment;
    private SkillsFragment skillsFragment;

    private static final String TAG = "Main activity logging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigate to the submit activity
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
                Log.d(TAG, "onClick: Starting Submit Activity");
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        viewPager = findViewById(R.id.view_pager_container);
        setUpViewPager();
    }

    public void  setUpViewPager(){
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), 0);
        learnersFragment = new LearnersFragment();
        skillsFragment = new SkillsFragment();
        myPagerAdapter.addFragment(learnersFragment);
        myPagerAdapter.addFragment(skillsFragment);

        // set the adapter to the view pager
        viewPager.setAdapter(myPagerAdapter);
        Log.d(TAG, "setUpViewPager: Setting up Adapter");

        TabLayout tabLayout = findViewById(R.id.tabs_top);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(getString(R.string.learning_fragment_tag));
        tabLayout.getTabAt(1).setText(getString(R.string.skill_fragment_tag));

    }

}