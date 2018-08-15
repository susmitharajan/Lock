package com.example.susmitharajan.applock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class CreatePatternActivity extends AppCompatActivity {

    private PatternLockView mPatternLockView;
    SharedPreferences sf;
    public static final String preferences = "patternDetails";
    public static final String patternVal = "pattern";

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {

        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {

        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Toast.makeText(getApplicationContext() , PatternLockUtils.patternToString(mPatternLockView, pattern),Toast.LENGTH_SHORT).show();
            String patternValue = PatternLockUtils.patternToString(mPatternLockView, pattern);
            int patternlength = patternValue.length();
            sf = getSharedPreferences(preferences, Context.MODE_PRIVATE);


            if(patternlength <= 3)
            {
                Toast.makeText(getApplicationContext(), "Minimum 4 dots should be connected", Toast.LENGTH_SHORT).show();
            }
            else{
                SharedPreferences.Editor editor = sf.edit();
                editor.putString(patternVal, patternValue);
                editor.commit();
                String savedPattern = sf.getString(patternVal, "");
                Toast.makeText(getApplicationContext(), "Pattern Saved" + savedPattern,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), InputPatternActivity.class);
                startActivity(intent);
            }
        }

        @Override
        public void onCleared() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_pattern);

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);
    }
}
