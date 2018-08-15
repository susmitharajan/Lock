package com.example.susmitharajan.applock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sf;
    public static final String preferences = "patternDetails";
    public static final String patternVal = "pattern";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        sf = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        if (sf.contains(patternVal)) {
            Intent intent = new Intent(getApplicationContext(),InputPatternActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), CreatePatternActivity.class);
            startActivity(intent);
        }
    }
}
