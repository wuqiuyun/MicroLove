package com.mmt.microlove.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mmt.microlove.BaseApplication;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @Description 所有Activity的基类
 * @Author wuqiuyun
 * @Time 2017/4/17
 */
public class BaseActivity extends Activity {

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.addActivity(this);
    }

    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void startEnterActivity(Class cla) {
        startActivity(new Intent(this, cla));
        animLeftToRight();
    }

    public void startEnterActivity(Class cla, Bundle bundle) {
        startActivity(new Intent(this, cla).putExtras(bundle));
        animLeftToRight();
    }

    public void startExitActivity(Class cla) {
        startActivity(new Intent(this, cla));
        animRightToLeft();
    }

    /**
     * @Description 当前Activity进入时的动画
     */
    public void animLeftToRight() {
    }

    /**
     * @Description 当前Activity退出时的动画
     */
    public void animRightToLeft() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        BaseApplication.finishActivity(this);
//        if (this.mCompositeSubscription != null) {
//            this.mCompositeSubscription.unsubscribe();
//        }
    }
}
