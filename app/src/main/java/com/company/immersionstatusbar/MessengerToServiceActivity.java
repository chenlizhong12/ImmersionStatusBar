package com.company.immersionstatusbar;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MessengerToServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger_to_service);
        Intent intent = new Intent(MessengerToServiceActivity.this, MyService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain(null, MyService.MSG);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "这里是客户端，收到数据吗？");
            message.replyTo = new Messenger(mHandler);
            message.setData(bundle);
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MyService.MSG:
                    Log.i("clz", "客户端收到服务器的消息 " + msg.getData().get("reporterDate"));
            }
        }
    };
}
