package com.mmt.microlove.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmt.microlove.R;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.mmt.microlove.utils.UIUtils;
import com.mmt.microlove.view.SelectPicPopupWindow;

/**
 * 设置用户头像的界面
 * Created by wuqiuyun on 2017/5/11.
 */
public class ResetUserDataActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEAT_PHONE_CODE = 0X102;
    private TextView tvTitle;
    private TextView tvBack;
    private ImageView ivPhoto;
    private EditText edt_nickname;
    private TextView tvPhone;
    private TextView tvGender;
    private Button btnFirst;
    private Button btnSecond;
    private String tag = Constants.TAG;

    //自定义的弹出框类
    SelectPicPopupWindow menuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_userdata);
        initView();
        setData();
        setListener();
    }

    private void initView() {
        tvBack = (TextView) findViewById(R.id.tv_titlebar_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        edt_nickname = (EditText) findViewById(R.id.edt_nickname);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        btnFirst = (Button) findViewById(R.id.btn_first);
        btnSecond = (Button) findViewById(R.id.btn_second);
    }

    private void setData() {
        tvBack.setText("back");
        tvBack.setTextColor(UIUtils.getColor(R.color.white));
        Drawable left = getResources().getDrawable(R.mipmap.back);
        int w = (int) UIUtils.getResources().getDimension(R.dimen.img_tiny);
        left.setBounds(0, 0, w, w);//前两个是组件左上角在容器中的坐标,后两个是组件的宽度和高度
        tvBack.setCompoundDrawables(getResources().getDrawable(R.mipmap.back), null, null, null);
        tvTitle.setText("编辑资料");
    }

    private void setListener() {
        tvBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        tvGender.setOnClickListener(this);
        tvPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPhoto:

                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(ResetUserDataActivity.this, itemsPicOnClick);
//                btnFirst.setText("拍照");
//                btnSecond.setText("从相册中选择");
                //显示窗口
                menuWindow.showAtLocation(ResetUserDataActivity.this.findViewById(R.id.ivPhoto), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_titlebar_left:
                finish();
                break;
            case R.id.tv_gender:
                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(ResetUserDataActivity.this, itemsGenderOnClick);
                //显示窗口
                menuWindow.showAtLocation(ResetUserDataActivity.this.findViewById(R.id.ivPhoto), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_phone:
                Intent intent = new Intent(ResetUserDataActivity.this, ResetPhoneActivity.class);
                startActivityForResult(intent, REQUEAT_PHONE_CODE);
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsPicOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_first:
                    ToastUtil.shortShow("拍照");
                    break;
                case R.id.btn_second:
                    ToastUtil.shortShow("选择照片");
                    break;
                default:
                    break;
            }
        }

    };

    //为弹出窗口实现监听类
    private View.OnClickListener itemsGenderOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_first:
                    tvGender.setText("女");
                    break;
                case R.id.btn_second:
                    tvGender.setText("男");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == REQUEAT_PHONE_CODE) {
            String phone = data.getStringExtra(Constants.phone);
            LogUtil.i(tag, "获取phone：" + phone);
            tvPhone.setText(phone);
        }
    }

}
