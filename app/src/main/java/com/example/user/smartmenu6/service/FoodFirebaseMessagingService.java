package com.example.user.smartmenu6.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.user.smartmenu6.KitchenMenuCheck;
import com.example.user.smartmenu6.MainActivity;
import com.example.user.smartmenu6.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

//파이어베이스 메시지 받아서 안드로이드 알림처리 기능 수행
public class FoodFirebaseMessagingService extends FirebaseMessagingService {

    HashMap<String, Integer> notifyIdMap = new HashMap();
    private int threadNumber = 10;
    //파이어베이스 메시지를 받았을때
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
                                              //메시지 하나로 받은것(메시지 관련 정보들이 들어있다.)

//겟 노티피케이션 안에는 바디와 타잉틀이 있는데 이게 제목과 내용이다.
        Log.d("pushMessage", remoteMessage.getNotification().getBody());
        //알림을 실행하라 메시지를 받아서 노티를 띄운다. (띵동)
              //메세지 내용이 무엇인지 들어있다.
        doNoti(remoteMessage.getNotification());
        super.onMessageReceived(remoteMessage);
    }


    /*public int count;
    //증가시킨
    public void countIncrement(){
      count++;
    };
    LOGNOWCOUNTS(){
        Log.D("","now count:"+count);
    }

    countIncrement();

    */
    protected static int count=0;

                       //수신된 메세지
    protected void doNoti(RemoteMessage.Notification  notification){
        //    NotificationCompat.Buildertmxkdlf
        // 만든다  빌더와 메시지가 있어야한다.
        //두노티안에서는 원격으로 받은 메시지와 크리에이트 노티 빌더를 이용해서 알림을 수행하기 위한 적합한 빌더가 나온다.
        //1.리모트 메시지가 있어야한다. 2.리모트 메시지를 조합을 한다. 3.
        //알림의 기본데이터 형식 리모트메시지를 형태를 맞춰서 노티피케이션 컴벳 빌더의 형태로 만들어진 변수+알림의 모든데이터가 담겨있다.
        //엠빌더는 완전 기본적인 것만 같고 있다
        //크리에이트 베이직
        NotificationCompat.Builder mBuilder = createBasicNotiBuilder(notification.getTitle(), notification.getBody());

        /*mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_VIBRATE);*/

        Intent resultIntent = new Intent(this, KitchenMenuCheck.class);
        mBuilder.setContentIntent(
                getSpeckPendingIntent(resultIntent, KitchenMenuCheck.class)
        )
                .setAutoCancel(true);

        count++;
        Log.d("","count:"+count);
        noti(mBuilder, count);
        //notiThread(mBuilder);
    }






    // 쓰래드를 이용해야 하는 알림의 공통함수
    private void notiThread(final NotificationCompat.Builder mBuilder) {
        final NotificationManager mNotifyManager =             //띵동하는 서비스
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 쓰래드로 해야 중복 알림없이 1개로 발생
        // When the loop is finished, updates the notification

        // 쓰래드로 통해 실행 됨으로 notiThread 함수는 즉시 종료 된고 쓰래드는 수행된다.
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        int id =getNotiId(Thread.currentThread().getId());

                        for (int incr = 0; incr <= 100; incr+=5) {
                            mBuilder.setProgress(100, incr, false);
                            mNotifyManager.notify(id,mBuilder.build());
                            if(incr==100){
                                mNotifyManager.cancel(id);
                            }
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(1*1000);
                                Log.d("Therad","Thread name: "+Thread.currentThread().getId());
                            } catch (InterruptedException e) {
                                Log.d("Error", "sleep failure");
                            }
                        }

                    }
                }
        );
    }


    protected NotificationCompat.Builder createBasicNotiBuilder(String title,String text){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher) // 알림창에 표기 아이콘
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true);
        //메시지가 어떤형태로 보여질지 담겨있다
        //아직 띄우지 않았다.

        return mBuilder;
    }
//노티피케이션 메니저한테 띄워달라고 한다. 컨텍스트 노티피케이션 서비스에서 메니저를  가져온다.
    private void noti(NotificationCompat.Builder notifyBuilder, int notifyID) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(
                notifyID,  //메니저가 노티를 하면 뜬다. 아이디는 노트리를 업데이트시키고 중복도 방지한다.
                notifyBuilder.build());
    }



    private PendingIntent getSpeckPendingIntent(Intent resultIntent, Class cls) {
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        return notifyPendingIntent;
    }


    // 확장알림내에 표현해야 되는 박스데이터
    protected NotificationCompat.InboxStyle getInBoxContent(){
        NotificationCompat.InboxStyle inBoxStyle =
                new NotificationCompat.InboxStyle();


        String[] events = new String[6];
        inBoxStyle.setBigContentTitle("Event tracker details:");



        for (int i=0; i < events.length; i++) {
            events[i]="숫자"+i;
            inBoxStyle.addLine(events[i]);
        }



        return inBoxStyle;
    }

    // 실행중인 쓰래드의 아이디와
    protected int getNotiId(Long notifyIdLong){
        String notifyId = Long.toString(notifyIdLong);

        if(notifyIdMap.get(notifyId)==null){
            threadNumber++;
            notifyIdMap.put(notifyId,threadNumber);
        }
        return notifyIdMap.get(notifyId);
    }

    protected int getNotiId(int notifyInt){
        String notifyId = Integer.toString(notifyInt);

        if(notifyIdMap.get(notifyId)==null){
            threadNumber++;
            notifyIdMap.put(notifyId,threadNumber);
        }
        return notifyIdMap.get(notifyId);
    }

}
