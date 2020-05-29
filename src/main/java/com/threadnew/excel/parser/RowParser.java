package com.threadnew.excel.parser;

import com.threadnew.excel.CellDefinition;
import com.threadnew.excel.RowDefinition;
import com.threadnew.excel.annotation.CellType;
import com.threadnew.excel.annotation.ExcelRow;

import java.lang.reflect.Field;

/**
 * @Package: com.threadnew.excel.parser
 * @ClassName: RowParser
 * @Author: hase
 * @Description: TODO 解析@ExcelRow注解
 * @Date: 2020/5/27 15:56
 * @Version: 1.0
 */
public class RowParser {
    private Class target;
    private RowDefinition rowDefinition;

    public RowParser() {

    }

    public RowParser(Class target, RowDefinition rowDefinition) {
        this.target = target;
        this.rowDefinition = rowDefinition;
    }

     //利用Java反射解析
    public void parser() {
        //1 获取Class
        Class clazz = getTargetClass();
        //2 解析
        getAllRowAnno(clazz);

    }

    //得到类对象
    protected Class getTargetClass() {
        return this.target;
    }

    protected CellDefinition getForFiled(Field field) {
        ExcelRow excelRow = field.getAnnotation(ExcelRow.class);
        CellType cellType = excelRow.cellType();
        boolean isDateType = false;
        if (cellType == CellType.DateType) isDateType = true;
        CellDefinition cellDefinition = new CellDefinition(excelRow.name(), excelRow.title(), excelRow.cellNum(), excelRow.cellWidth(), excelRow.cellType(), excelRow.dateFormat(), isDateType);
        return cellDefinition;

    }

    protected void getAllRowAnno(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            this.rowDefinition.addCellDefinition(getForFiled(field));
        }
    }

    public void setTarget(Class target) {
        this.target = target;
    }

    public void setRowDefinition(RowDefinition rowDefinition) {
        this.rowDefinition = rowDefinition;
    }
}
