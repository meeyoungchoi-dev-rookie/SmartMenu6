package com.example.user.smartmenu6;

public class Time {



    final int SEC = 60;
    final int MIN = 60;
    final int HOUR = 24;
    final int DAY = 30;
    final int MONTH = 12;

    long curTime = System.currentTimeMillis();//현재시간
    long regTime = nowdate.getTime();//계산할 시간

    long diffTime = (curTime - regTime) / 1000;
    String msg = null;
    if (diffTime < SEC) {
        msg = "방금 전";
    } else if ((diffTime /= SEC) < MIN) {
        msg = diffTime + "분 전";
    } else if ((diffTime /= MIN) < HOUR) {
        msg = (diffTime) + "시간 전";
    } else if ((diffTime /= HOUR) < DAY) {
        msg = (diffTime) + "일 전";
    } else if ((diffTime /= DAY) < MONTH) {
        msg = (diffTime) + "달 전";
    } else {
        msg = (diffTime) + "년 전";
    }
    return msg;
}







