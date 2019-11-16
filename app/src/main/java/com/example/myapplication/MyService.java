package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;

public class MyService extends Service {
    private Callback callback;//回调函数
    private Timer timer=new Timer();
    private int number;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public int getNumber() {
        return number;
    }

    private String TAG="Mylog";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"服务已经开启：onCreate");
        timer.schedule(task,0,1000);
    }

    private IBinder myBinder=new Binder(){
        public String getInterfaceDescriptor(){
            return "MyServiceUtil class.";
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"绑定：onBind");
         return myBinder;
//        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void onRebind(Intent intent){
        Log.d(TAG,"重新绑定：onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"解除绑定：onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"服务已经销毁：onDestroy");
    }

    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            number++;
            if(callback==null){
                callback.getNumber(number);
            }
        }
    };

    public interface Callback{
        void getNumber(int num);
    }
}
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Binder;
//import android.os.IBinder;
//
///**
// * 服务类（需要在项目清单文件中注册服务）
// *
// * @author lenovo
// *
// */
//public class MyService extends Service {
//    private DownLoadBinder downLoadBinder=new DownLoadBinder();
//    /**
//     * 回调
//     */
//    private Callback callback;
//    /**
//     * Timer实时更新数据的
//     */
//    private Timer mTimer=new Timer();
//    /**
//     *
//     */
//    private int num;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO Auto-generated method stub
//        System.out.println("=====onBind=====");
//        return downLoadBinder;
//    }
//
//    /**
//     * 内部类继承Binder
//     * @author lenovo
//     *
//     */
//    public class DownLoadBinder extends Binder{
//        /**
//         * 声明方法返回值是MyService本身
//         * @return
//         */
//        public MyService getService() {
//            return MyService.this;
//        }
//    }
//    /**
//     * 服务创建的时候调用
//     */
//    @Override
//    public void onCreate() {
//        // TODO Auto-generated method stub
//        super.onCreate();
//        /*
//         * 执行Timer 2000毫秒后执行，5000毫秒执行一次
//         */
//        mTimer.schedule(task, 0, 1000);
//    }
//
//    /**
//     * 提供接口回调方法
//     * @param callback
//     */
//    public void setCallback(Callback callback) {
//        this.callback = callback;
//    }
//
//    /**
//     *
//     */
//    TimerTask task = new TimerTask(){
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            num++;
//            if(callback!=null){
//                /*
//                 * 得到最新数据
//                 */
//                callback.getNum(num);
//            }
//
//        }
//
//    };
//
//
//    /**
//     * 回调接口
//     *
//     * @author lenovo
//     *
//     */
//    public static interface Callback {
//        /**
//         * 得到实时更新的数据
//         *
//         * @return
//         */
//        void getNum(int num);
//    }
//    /**
//     * 服务销毁的时候调用
//     */
//    @Override
//    public void onDestroy() {
//        // TODO Auto-generated method stub
//        System.out.println("=========onDestroy======");
//        /**
//         * 停止Timer
//         */
//        mTimer.cancel();
//        super.onDestroy();
//    }
//}
