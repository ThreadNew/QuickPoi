package com.threadnew.excel;

import java.util.Arrays;

/**
 * @ClassName RemoveMarks
 * @Description TODO 移除标志
 * @Author ThreadNew
 * @Date 2020/6/3 14:10
 * Version 1.0
 **/
public class RemoveMarks {
    private boolean remove=false;
    private int[] rmvGroup;
    public RemoveMarks(){

    }

    public RemoveMarks(boolean remove, int[] rmvGroup) {
        this.remove = remove;
        this.rmvGroup = rmvGroup;
    }

    public int[] getRmvGroup() {
        return rmvGroup;
    }

    public void setRmvGroup(int[] rmvGroup) {
        this.rmvGroup = rmvGroup;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    @Override
    public String toString() {
        return "RemoveMarks{" +
                "remove=" + remove +
                ", rmvGroup=" + Arrays.toString(rmvGroup) +
                '}';
    }
}
