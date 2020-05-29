package com.threadnew.excel.factory;

import com.threadnew.excel.ExcelExport;
import com.threadnew.excel.RowDefinition;
import com.threadnew.excel.cache.DefinitionMapCache;
import com.threadnew.excel.parser.RowParser;

import java.io.OutputStream;
import java.util.List;

/**
 * @Package: com.threadnew.excel.factory
 * @ClassName: ExcelExportFactory
 * @Author: hase
 * @Description: TODO
 * @Date: 2020/5/27 18:27
 * @Version: 1.0
 */
public class ExcelExportFactory {
    private ExcelExport excelExport;
    private OutputStream out;
    private List result;
    private String title;
    private Class target;
    private Object lock="";

    public ExcelExport getExcelExport() {
        return excelExport;
    }

    public void setExcelExport(ExcelExport excelExport) {
        this.excelExport = excelExport;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getTarget() {
        return target;
    }

    public void setTarget(Class target) {
        this.target = target;
    }

    //模板方法
    public void ExcelExport() {
        //缓存中得到
        RowDefinition single = getSingle(target.getName());
        try {
            excelExport.excelExport(out,result,target,title,single);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // 缓存中找，没有在解析
    public RowDefinition getSingle(String name) {
        RowDefinition rowDefinition = DefinitionMapCache.getDefinitionByName(name);
        if (rowDefinition == null) {
            synchronized (lock) {
                rowDefinition = DefinitionMapCache.getDefinitionByName(name);
                if (rowDefinition == null) {
                    RowDefinition temp = new RowDefinition();
                    RowParser rowParser = new RowParser(target, temp);
                    rowParser.parser();
                    rowDefinition = temp;
                    DefinitionMapCache.putDefinitionMap(target.getName(), temp);
                }
            }
        }
        return rowDefinition;
    }

}
