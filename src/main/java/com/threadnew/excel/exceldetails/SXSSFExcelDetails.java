package com.threadnew.excel.exceldetails;

import com.threadnew.excel.CellDefinition;
import com.threadnew.excel.RemoveMarks;
import com.threadnew.excel.RowDefinition;
import com.threadnew.excel.utils.ArrayInArray;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName SXSSFExcelDetails
 * @Description TODO
 * @Author ThreadNew
 * @Date 2020/6/3 14:19
 * Version 1.0
 **/
public class SXSSFExcelDetails implements ExcelDetails<SXSSFWorkbook, SXSSFSheet> {
    private RemoveMarks removeMarks;

    public SXSSFExcelDetails() {
    }

    public RemoveMarks getRemoveMarks() {
        return removeMarks;
    }

    public void setRemoveMarks(RemoveMarks removeMarks) {
        this.removeMarks = removeMarks;
    }

    @Override
    public SXSSFSheet createTitleRow(SXSSFWorkbook wb, String title, RowDefinition rowDefinition) {
        SXSSFSheet sheet = wb.createSheet();
        int size = rowDefinition.maxCellNum();
        //处理剔除的类
        if(removeMarks.isRemove()) {
            int[] rmvGroup = removeMarks.getRmvGroup();
            long count = rowDefinition.getCellDefinitions().stream().filter(u -> ArrayInArray.isContain(u.getRmvGroup(), rmvGroup)).count();
            size=(int)count;
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, size));
        SXSSFRow row = sheet.createRow(0);
        SXSSFCell cell = row.createCell(0);
         CellStyle style = wb.createCellStyle();//创建样式/
        style.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(style);
        cell.setCellValue(title);

        return sheet;
    }

    @Override
    public void createContentRow(SXSSFSheet sheet,SXSSFWorkbook wb, OutputStream out, List result, Class target, RowDefinition rowDefinition) throws Exception {
        List<Integer> removeList=null;
        List<CellDefinition> cellDefinitions = rowDefinition.getCellDefinitions();
        SXSSFRow titleRow = sheet.createRow(1);
        for (CellDefinition cellDefinition : cellDefinitions) {
            //列名
            SXSSFCell cell1 = titleRow.createCell(cellDefinition.getCellNum());
            cell1.setCellValue(cellDefinition.getTitle());
        }
        //处理
        if(removeMarks.isRemove()){
            int[] rmvGroup = removeMarks.getRmvGroup();
             removeList = rowDefinition.getCellDefinitions().stream().filter(u -> ArrayInArray.isContain(u.getRmvGroup(), rmvGroup)).map(u -> u.getCellNum()).collect(Collectors.toList());
            for(Integer i:removeList){
                titleRow.removeCell(titleRow.getCell(i));
            }
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
                if(removeMarks.isRemove()){
                    for(Integer ii:removeList){
                        row1.removeCell(row1.getCell(ii));
                    }
                }
            }
        }
        wb.write(out);
        wb.dispose();
    }
}
