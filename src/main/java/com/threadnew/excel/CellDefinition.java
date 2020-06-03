package com.threadnew.excel;

import com.threadnew.excel.annotation.CellType;

import java.util.Arrays;

/**
 * @Package: com.threadnew.excel
 * @ClassName: CellDefinition
 * @Author: hase
 * @Description: TODO   数据结构：存取@ExcelRow注解的属性
 * @Date: 2020/5/27 15:38
 * @Version: 1.0
 */
public class CellDefinition {
    private String name;
    private String title;
    private int cellNum;
    private int cellWidth;
    private CellType cellType;
    private String dateFormat;
    private boolean dateType = false;
    private boolean remove = false;
    private int[] rmvGroup = {0};

    public CellDefinition() {
    }

    public CellDefinition(String name, String title, int cellNum, int cellWidth, CellType cellType, String dateFormat, boolean dateType) {
        this.name = name;
        this.title = title;
        this.cellNum = cellNum;
        this.cellWidth = cellWidth;
        this.cellType = cellType;
        this.dateFormat = dateFormat;
        this.dateType = dateType;
    }

    public CellDefinition(String name, String title, int cellNum, int cellWidth, CellType cellType, String dateFormat, boolean dateType, boolean remove, int[] rmvGroup) {
        this.name = name;
        this.title = title;
        this.cellNum = cellNum;
        this.cellWidth = cellWidth;
        this.cellType = cellType;
        this.dateFormat = dateFormat;
        this.dateType = dateType;
        this.remove = remove;
        this.rmvGroup = rmvGroup;
    }

    public boolean isDateType() {
        return dateType;
    }

    public void setDateType(boolean dateType) {
        this.dateType = dateType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public boolean getRmove() {
        return this.remove;
    }

    public int[] getRmvGroup() {
        return rmvGroup;
    }

    public void setRmvGroup(int[] rmvGroup) {
        this.rmvGroup = rmvGroup;
    }

    @Override
    public String toString() {
        return "CellDefinition{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", cellNum=" + cellNum +
                ", cellWidth=" + cellWidth +
                ", cellType=" + cellType +
                ", dateFormat='" + dateFormat + '\'' +
                ", dateType=" + dateType +
                ", remove=" + remove +
                ", rmvGroup=" + Arrays.toString(rmvGroup) +
                '}';
    }
}
