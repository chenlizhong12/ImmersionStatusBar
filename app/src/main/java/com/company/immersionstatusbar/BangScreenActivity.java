package com.company.immersionstatusbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BangScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bang_screen);
        findViewById(R.id.topButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BangScreenActivity.this, LeakCanaryActivity.class);
                startActivity(intent);
            }
        });
        View decodeView = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decodeView.setSystemUiVisibility(options);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(layoutParams);
        }
    }

    //适配刘海屏遮住控件显示，进行适配
    private void matchScreen() {
        final FrameLayout frameLayout = findViewById(R.id.rootLayout);
        final Button topButton = findViewById(R.id.topButton);
        final Button side = findViewById(R.id.sideButton);
        frameLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                if (Build.VERSION.SDK_INT >= 28) {
                    DisplayCutout displayCutout = insets.getDisplayCutout();
                    if (displayCutout != null) {
                        int right = displayCutout.getSafeInsetRight();
                        int left = displayCutout.getSafeInsetLeft();
                        int bottom = displayCutout.getSafeInsetBottom();
                        int top = displayCutout.getSafeInsetTop();
                        FrameLayout.LayoutParams topParams = (FrameLayout.LayoutParams) topButton.getLayoutParams();
                        topParams.setMargins(left, top, right, bottom);
                        FrameLayout.LayoutParams sideParams = (FrameLayout.LayoutParams) side.getLayoutParams();
                        sideParams.setMargins(left, top, right, bottom);
                    }

                }
                return insets.consumeSystemWindowInsets();
            }
        });
    }
}
