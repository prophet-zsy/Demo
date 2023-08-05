package com.ty.aidlstudy;

import com.ty.aidlstudy.UserMessage;

interface NetByte {

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                double aDouble, String aString);

    void sendMessage(in UserMessage message);

}