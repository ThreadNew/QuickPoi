package com.threadnew.excel.export;

import com.threadnew.excel.ExcelExport;
import com.threadnew.excel.RowDefinition;

import java.io.OutputStream;
import java.util.List;

/**
 * @Package: com.threadnew.excel.export
 * @ClassName: AbstractExcel
 * @Author: hase
 * @Description: TODO
 * @Date: 2020/5/27 18:23
 * @Version: 1.0
 */
public abstract class AbstractExcel implements ExcelExport {
    //初始化
    protected abstract void initialize ();
    //结束
    protected abstract void close(OutputStream out);
}
