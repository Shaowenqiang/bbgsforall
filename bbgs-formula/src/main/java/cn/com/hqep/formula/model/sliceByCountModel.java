package cn.com.hqep.formula.model;

/**
 * Created by spring on 2017/10/11.
 */
public class sliceByCountModel {
    int mStart, mEnd, cutLow, cutHight, type;
    public static int TYPE_MATERIAL = 0;
    public static int TYPE_NOTMATERIAL = 0;

    public sliceByCountModel(int mStart, int mEnd, int cutLow, int cutHight) {
        this.mStart = mStart;
        this.mEnd = mEnd;
        this.cutLow = cutLow;
        this.cutHight = cutHight;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getmStart() {
        return mStart;
    }

    public void setmStart(int mStart) {
        this.mStart = mStart;
    }

    public int getmEnd() {
        return mEnd;
    }

    public void setmEnd(int mEnd) {
        this.mEnd = mEnd;
    }

    public int getCutLow() {
        return cutLow;
    }

    public void setCutLow(int cutLow) {
        this.cutLow = cutLow;
    }

    public int getCutHight() {
        return cutHight;
    }

    public void setCutHight(int cutHight) {
        this.cutHight = cutHight;
    }
}
