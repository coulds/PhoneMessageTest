package com.edu.phonetest;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private ImageView imageView;
    private EditText et_phoneNum, et_password;
    private Button btn_login, btn_getMsg;
    private int i = 30;//计时器
    private final static String APPKEY="30f93a8d3b202";
    private final static String APPSECRET="6881f3124d3ec41933f288eb4880798f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MobSDK.init(this);
//        if (Build.VERSION.SDK_INT >= 23) {
//            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
//            ActivityCompat.requestPermissions(this, mPermissionList, 123);
//        }
//
//        //onCreate里注册
//        initView();
//        // 启动短信验证sdk
//        MobSDK.init(this, APPKEY, APPSECRET);
//    }
//
//    EventHandler eventHandler = new EventHandler(){
//        @Override
//        public void afterEvent(int i, int i1, Object o) {
//            super.afterEvent(i, i1, o);
//            Message message = new Message();
//            message.arg1=i;
//            message.arg2=i1;
//            message.obj=o;
//
//        }
//    };
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == -9) {
//                btn_getMsg.setText("重新发送(" + i + ")");
//            } else if (msg.what == -8) {
//                btn_getMsg.setText("获取验证码");
//                btn_getMsg.setClickable(true);
//                i = 30;
//            } else {
//                int i = msg.arg1;
//                int i1 = msg.arg2;
//                Object o = msg.obj;
//                if (i1 == SMSSDK.RESULT_COMPLETE) {
//                    // 短信注册成功后，返回MainActivity,然后提示
//                    if (i == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        Toast.makeText(MainActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("userName", et_phoneNum.getText().toString().trim());
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//
//                    } else if (i == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) {
//                        Toast.makeText(LoginActivity.this, "正在获取验证码", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//    };
//
//    private void initView() {
//
//        et_phoneNum = findViewById(R.id.et_phoneNum);
//        et_password = findViewById(R.id.user_password);
//        btn_getMsg = findViewById(R.id.btn_getMsg);
//        btn_login = findViewById(R.id.btn_login);
//        imageView = findViewById(R.id.wechat_login);
//        btn_login.setOnClickListener(this);
//        btn_getMsg.setOnClickListener(this);
    }
}
