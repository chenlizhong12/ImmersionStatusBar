package com.company.immersionstatusbar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BlueToothActivity extends AppCompatActivity {
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        mBluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        }
        if (!blueToothIsEnable()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1);
        }
        Button button = findViewById(R.id.bt_anim);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button, "alpha", 0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "scale", 1.0f, 2.0f);
        ValueAnimator animator1 = ValueAnimator.ofFloat(1.0f, 2.0f);
    }

    private boolean blueToothIsEnable () {
        if (mBluetoothAdapter.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }


}

