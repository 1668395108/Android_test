package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("服务连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("服务断开");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudioManager am= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_start:
                MainActivity.this.startService(new Intent(MainActivity.this,MyService.class));
                break;
            case R.id.button_stop:
                MainActivity.this.stopService(new Intent(MainActivity.this,MyService.class));
                break;
            case R.id.button_bindstart:
                MainActivity.this.bindService(new Intent(MainActivity.this,MyService.class),MainActivity.this.serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.button_bindstop:
                MainActivity.this.unbindService(MainActivity.this.serviceConnection);
        }
    }
}
