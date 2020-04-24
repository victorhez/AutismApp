package com.example.autismapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Splash extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.page_0_title))
                        .setContent(getString(R.string.page_0_content))
                        .setBackgroundColor(Color.parseColor("#FF0957"))//  #CA70F3
                        .setDrawable(R.drawable.aware)
                        .setSummary(getString(R.string.page_0_summary))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.page_1_title))
                        .setContent(getString(R.string.page_1_content))
                        .setBackgroundColor(Color.parseColor("#99FF0957"))
                        .setDrawable(R.drawable.logo)
                        .setSummary(getString(R.string.page_1_summary))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.page_2_title))
                        .setContent(getString(R.string.page_2_content))
                        .setBackgroundColor(Color.parseColor("#00D4BA"))
                        .setDrawable(R.drawable.child)
                        .setSummary(getString(R.string.page_2_summary))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.page_3_title))
                        .setContent(getString(R.string.page_3_content))
                        .setBackgroundColor(Color.parseColor("#1098FE"))
                        .setDrawable(R.drawable.autism)
                        .setSummary(getString(R.string.page_3_summary))
                        .build());

        addFragment(
                new PermissionStep
                        .Builder()
                        .setPermissions(new String[]{Manifest.permission.READ_SMS,Manifest.permission.ACCESS_FINE_LOCATION})
                        .setTitle(getString(R.string.page_4_title))
                        .setContent(getString(R.string.page_4_content))
                        .setBackgroundColor(Color.parseColor("#4cd137"))//#8BCEAB
                        .setDrawable(R.drawable.logo)
                        .setSummary(getString(R.string.page_4_summary))
                        .build());

    }
    @Override
    public void finishTutorial() {
        Toast.makeText(this, "Tutorial finished", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Splash.this,LoginMain.class);
        startActivity(intent);
        //finish();
    }
    @Override
    public void currentFragmentPosition(int position) {

    }
}
