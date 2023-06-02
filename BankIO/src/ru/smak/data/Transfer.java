package ru.smak.data;

import java.io.Serializable;
import java.util.Date;

public class Transfer implements Serializable
{
    private String acc1;
    private String acc2;
    private float sum;
    private float fee;
    private Date date;


    public String getAcc1() {
        return acc1;
    }

    public void setAcc1(String acc1) {
        this.acc1 = acc1;
    }

    public String getAcc2() {
        return acc2;
    }

    public void setAcc2(String acc2) {
        this.acc2 = acc2;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
