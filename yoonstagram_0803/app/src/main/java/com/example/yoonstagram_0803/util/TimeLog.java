package com.example.yoonstagram_0803.util;

import android.util.TimingLogger;

public class TimeLog {
    public TimingLogger tl;
    public TimeLog  (String tag, String label){
        tl = new TimingLogger(tag,label);

    }

}
