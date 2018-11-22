package com.kery.total2demo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/9/3.
 */

public class ParcelableModel implements Parcelable {

    private String a1;
    private String a2;
    private String a3;
    private int b1;
    private boolean c1;
    private double d1;

    @Override
    public String toString() {
        return "ParcelableModel{" +
                "a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", a3='" + a3 + '\'' +
                ", b1=" + b1 +
                ", c1=" + c1 +
                ", d1=" + d1 +
                '}';
    }

    protected ParcelableModel(Parcel in) {
        a1 = in.readString();
        a2 = in.readString();
        a3 = in.readString();
        b1 = in.readInt();
        c1 = in.readByte() != 0;
        d1 = in.readDouble();
    }

    public static final Creator<ParcelableModel> CREATOR = new Creator<ParcelableModel>() {
        @Override
        public ParcelableModel createFromParcel(Parcel in) {
            return new ParcelableModel(in);
        }

        @Override
        public ParcelableModel[] newArray(int size) {
            return new ParcelableModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(a1);
        dest.writeString(a2);
        dest.writeString(a3);
        dest.writeInt(b1);
        dest.writeByte((byte) (c1 ? 1 : 0));
        dest.writeDouble(d1);
    }
}
