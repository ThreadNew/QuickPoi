package com.threadnew.excel.export;

import com.threadnew.excel.CellDefinition;
import com.threadnew.excel.RowDefinition;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Package: com.threadnew.excel.export
 * @ClassName: SXSSFExcel
 * @Author: hase
 * @Description: TODO
 * @Date: 2020/5/27 19:18
 * @Version: 1.0
 */
public class SXSSFExcel extends AbstractExcel {
    private SXSSFWorkbook wb;
    private int rowAccessWindowSize = 100;//keep 100 rows in memory, exceeding rows will be flushed to disk
    private CellStyle style;

    protected void startup() {
        wb = new SXSSFWorkbook(rowAccessWindowSize);
        wb.setCompressTempFiles(true);
        style = wb.createCellStyle();//创建样式/

    }

    public void setRowAccessWindowSize(int rowAccessWindowSize) {
        this.rowAccessWindowSize = rowAccessWindowSize;
    }

    protected void close(OutputStream out) {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void excelExport(OutputStream out, List result, Class target, String title, RowDefinition rowDefinition) throws Exception {
        startup();
        SXSSFSheet sheet = wb.createSheet();
        int size = rowDefinition.maxCellNum();
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, size ));
        SXSSFRow row = sheet.createRow(0);
        SXSSFCell cell = row.createCell(0);
        style.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(style);
        cell.setCellValue(title);
        List<CellDefinition> cellDefinitions = rowDefinition.getCellDefinitions();
        SXSSFRow titleRow = sheet.createRow(1);
        for (CellDefinition cellDefinition : cellDefinitions) {
            //列名
            SXSSFCell cell1 = titleRow.createCell(cellDefinition.getCellNum());
            cell1.setCellValue(cellDefinition.getTitle());
        }
        if (result != null && result.size() != 0) {
            for (int i = 0; i < result.size(); i++) {
                Object o = result.get(i);
                SXSSFRow row1 = sheet.createRow(i + 2);
                for (CellDefinition cellDefinition : cellDefinitions) {
                    SXSSFCell cell1 = row1.createCell(cellDefinition.getCellNum());
                    sheet.setColumnWidth(cellDefinition.getCellNum(), cellDefinition.getCellWidth()* 256);
                    String methodName = "get" + cellDefinition.getName().substring(0, 1).toUpperCase() + cellDefinition.getName().substring(1);
                    if (!cellDefinition.isDateType()) {
                        Method declaredMethod = target.getDeclaredMethod(methodName);
                        Object obj = declaredMethod.invoke(o);
                        Object o1 = Optional.ofNullable(obj).orElse("");
                        cell1.setCellValue(String.valueOf(o1));
                    } else {
                        //日期处理
                        Method declaredMethod = target.getDeclaredMethod(methodName);
                        Object obj = declaredMethod.invoke(o);
                        String temp = "";
                        if (obj != null) {
                            SimpleDateFormat sdf=new SimpleDateFormat(cellDefinition.getDateFormat());
                            temp = sdf.format(obj);
                        }
                        cell1.setCellValue(temp);
                    }
                }
            }
        }
        wb.write(out);
        close(out);


    }
}
