package com.company.immersionstatusbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * ┏┓　   ┏┓
 * ┏┛┻━━━━━┛┻━┓
 * ┃　　　　   ┃
 * ┃　━　━　   ┃
 * ████━████   ┃
 * ┃　　　　   ┃
 * ┃　 ┻　    ┃
 * ┗━┓      ┏━┛
 * 　┃      ┃
 * 　┃ 0BUG ┗━━━┓
 * 　┃0Error     ┣┓
 * 　┃0Warning   ┏┛
 * 　┗┓┓┏━┳┓┏┛ ━
 * 　　┃┫┫ ┃┫┫
 * 　　┗┻┛ ┗┻┛
 * Created by clz on 2019/12/2
 */
public class TestView extends View {
    Context context;
    public TestView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        setLayoutParams(params);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        TextView textView = new TextView(context);
        Scroller scroller = new Scroller(context);
        scroller.getCurrX();
        ObjectAnimator.ofFloat(textView,"translationX", 0, 300).setDuration(1000).start();
    }
}
