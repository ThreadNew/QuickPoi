package com.threadnew.excel.annotation;
// 文本类型和日期类型处理
public enum CellType {
    TextType(0),
    DateType(1);

    CellType(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }
}
