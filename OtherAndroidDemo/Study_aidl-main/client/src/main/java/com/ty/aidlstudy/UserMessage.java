package com.ty.aidlstudy;

import android.os.Parcel;
import android.os.Parcelable;

public class UserMessage implements Parcelable {


    public String messageContent;

    public UserMessage(String messageContent) {
        this.messageContent = messageContent;
    }

    protected UserMessage(Parcel in) {
        messageContent = in.readString();
    }

    public static final Creator<UserMessage> CREATOR = new Creator<UserMessage>() {
        @Override
        public UserMessage createFromParcel(Parcel in) {
            return new UserMessage(in);
        }

        @Override
        public UserMessage[] newArray(int size) {
            return new UserMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(messageContent);
    }
}
