package com.threadnew.excel.exceldetails;

import com.threadnew.excel.RowDefinition;

import java.io.OutputStream;
import java.util.List;

public interface ExcelDetails<T,F> {
    //标题行处理
    F createTitleRow(T wb, String title, RowDefinition rowDefinition);
    //内容创建
    void createContentRow(F sheet,T wb, OutputStream out, List result, Class target,RowDefinition rowDefinition)throws Exception;
}
