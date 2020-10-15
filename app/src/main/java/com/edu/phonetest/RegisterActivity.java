package com.edu.phonetest;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPhoneNumber;        // 电话号码
    private Button sendVerificationCode;   // 发送验证码
    private EditText etVerificationCode;   // 验证码
    private Button nextStep;               // 下一步
    private int time= 30;
    private String phoneNumber;         // 电话号码
    private String verificationCode;    // 验证码
    private boolean flag;   // 操作是否成功


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        final Context context = RegisterActivity.this;
        final  String AppKey="30f93a8d3b202";
       final  String AppSecret="6881f3124d3ec41933f288eb4880798f";



        MobSDK.init(this,AppKey,AppSecret);
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object data) {
                Message msg = new Message();
                msg.arg1=i;
                msg.arg2=i1;
                msg.obj=data;
               handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void init() {

        etPhoneNumber = (EditText) findViewById(R.id.editText);
        etVerificationCode = findViewById(R.id.yanzhengma);
        sendVerificationCode = findViewById(R.id.btn1);
        nextStep = findViewById(R.id.btn2);
        sendVerificationCode.setOnClickListener(this);
        nextStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                if (!TextUtils.isEmpty(etPhoneNumber.getText())) {
                    if (etPhoneNumber.getText().length() == 11) {
                        phoneNumber = etPhoneNumber.getText().toString();
                        SMSSDK.getVerificationCode("86", phoneNumber); // 发送验证码给号码的 phoneNumber 的手机
                        etVerificationCode.requestFocus();
                    }
                    else {
                        Toast.makeText(this, "请输入完整的电话号码", Toast.LENGTH_SHORT).show();
                        etPhoneNumber.requestFocus();
                    }
                } else {
                    Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    etPhoneNumber.requestFocus();
                }
                CountDownTimer countDownTimer = new CountDownTimer(60000,1000) {
                    @Override
                    public void onTick(long l) {
                        sendVerificationCode.setEnabled(false);
                        sendVerificationCode.setText("已发送("+ l/1000+")");
                    }

                    @Override
                    public void onFinish() {
                        sendVerificationCode.setText("重新获取验证码");
                        sendVerificationCode.setEnabled(true);
                    }
                }.start();

                break;

            case R.id.btn2:
                if (!TextUtils.isEmpty(etVerificationCode.getText())){
                    if (etVerificationCode.getText().length()==6){
                        verificationCode = etVerificationCode.getText().toString();
                        SMSSDK.submitVerificationCode("86",phoneNumber,verificationCode);
                        flag = false;
                    }else {
                        Toast.makeText(RegisterActivity.this,"请输入6位验证码",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
//            switch (msg.what){
//                case 1:
//                    sendVerificationCode.setText("重新发送"+time);
//                    sendVerificationCode.setClickable(false);
//                    break;
//                case 2:
//                    sendVerificationCode.setClickable(true);
//                    sendVerificationCode.setText("发送验证码");
//                    break;
//
//            }

            if (result == SMSSDK.RESULT_COMPLETE) {
                // 如果操作成功
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // 校验验证码，返回校验的手机和国家代码
                    Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 获取验证码成功，true为智能验证，false为普通下发短信
                    Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    // 返回支持发送验证码的国家列表
                }
            } else {
                // 如果操作失败
                if (flag) {
                    Toast.makeText(RegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    etPhoneNumber.requestFocus();
                } else {
                    ((Throwable) data).printStackTrace();
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
