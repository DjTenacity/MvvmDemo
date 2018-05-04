package dj.com.mvvmdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import dj.com.mvvmdemo.bindstudy.MessageService;
import dj.com.mvvmdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //android.databing
    User user;
    Handler handler=new Handler();
    UserField userField=new UserField();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不能用 setContentView
//        setContentView(R.layout.activity_main);
        final ActivityMainBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_main);

        user=new User("李四","12345678","http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        binding.setUser(user);
        binding.setField(userField);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                userField.name.set("sindy");
                userField.password.set("123456978");
            }
        },2000);

        startService(new Intent(this, MessageService.class));


        //yinshi隐示意图
        Intent intent = new Intent(this,MessageService.class);
        bindService(intent,mServiceConn, Context.BIND_AUTO_CREATE);
    }
    private UserAidl mUserAidl;

    //客户端一定要获取aidl 的事例
    private ServiceConnection mServiceConn =new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //链接成功
            mUserAidl=UserAidl.Stub.asInterface(iBinder);

            //asInterface ---->返回的是Proxy
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //失败

        }
    };

    void aidlTest(){
        try {
            Toast.makeText(this,mUserAidl.getUsername(),Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
