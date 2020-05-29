package com.threadnew.annotation;

import com.threadnew.excel.RowDefinition;
import com.threadnew.excel.export.ExcelType;
import com.threadnew.excel.export.SXSSFExcel;
import com.threadnew.excel.factory.ExcelExportFactory;
import com.threadnew.excel.parser.RowParser;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Package: com.threadnew.annotation
 * @ClassName: AnnotationTest
 * @Author: hase
 * @Description: TODO
 * @Date: 2020/5/27 16:29
 * @Version: 1.0
 */
public class AnnotationTest {
    @Test
    public void ParserTest(){
        RowDefinition rowDefinition=new RowDefinition();
        RowParser rowParser=new RowParser(Person.class,rowDefinition);
        rowParser.parser();
    }
    @Test
    public void StringTest(){
        String s = "get" + "name".substring(0, 1).toUpperCase() + "name".substring(1);
        System.out.println(s);
    }
    @Test
    public void ExcelExport() throws FileNotFoundException {
        String uploadPath = "D:/upload";
        String path= uploadPath + File.separator + "temp"+ File.separator +"15.xlsx";
        File file=new File(path);
        List<Person> list=new LinkedList<>();
        for(int i=0;i<1000;i++){
            Person p=new Person();
            p.setName("chen"+i);
            p.setAge("10"+i);
            list.add(p);
        }
        OutputStream out=new FileOutputStream(file);
        SXSSFExcel sxssfExcel = ExcelType.sxssfExcelInstance();
        sxssfExcel.setRowAccessWindowSize(100);
        ExcelExportFactory excelExportFactory=new ExcelExportFactory();
        excelExportFactory.setExcelExport(sxssfExcel);
        excelExportFactory.setOut(out);
        excelExportFactory.setTarget(Person.class);
        excelExportFactory.setTitle("ceshi");
        excelExportFactory.setResult(list);
        excelExportFactory.ExcelExport();


    }
    @Test
    public void ExcelExport1() throws FileNotFoundException {
        String uploadPath = "D:/upload";
        String path= uploadPath + File.separator + "temp"+ File.separator +"16.xlsx";
        File file=new File(path);
        List<UserInfo> list=new LinkedList<>();
        for(int i=0;i<1000;i++){
            UserInfo p=new UserInfo();
            p.setName("chen"+i);
            p.setAge(i);
            p.setDatetime(new Date());
            p.setAddress("jjjj"+i);
            list.add(p);
        }
        OutputStream out=new FileOutputStream(file);
        SXSSFExcel sxssfExcel = ExcelType.sxssfExcelInstance();
        sxssfExcel.setRowAccessWindowSize(100);
        ExcelExportFactory excelExportFactory=new ExcelExportFactory();
        excelExportFactory.setExcelExport(sxssfExcel);
        excelExportFactory.setOut(out);
        excelExportFactory.setTarget(UserInfo.class);
        excelExportFactory.setTitle("ceshi");
        excelExportFactory.setResult(list);
        excelExportFactory.ExcelExport();


    }
    @Test
    public void stringToDate() throws ParseException {
        String str1="2018-07-05";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse1 = sdf.parse(str1);
        String format = sdf.format(parse1);

        System.out.println(format);
    }
}
