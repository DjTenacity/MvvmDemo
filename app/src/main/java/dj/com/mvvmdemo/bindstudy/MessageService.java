package dj.com.mvvmdemo.bindstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import dj.com.mvvmdemo.UserAidl;

/**
 * fuwuduan
 */

public class MessageService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //绑定

        return mBinder;
    }

    private final UserAidl.Stub mBinder = new UserAidl.Stub() {
        @Override
        public String getUsername() throws RemoteException {
            return "";
        }

        @Override
        public String getUserPwd() throws RemoteException {
            return "";
        }
    };
}
