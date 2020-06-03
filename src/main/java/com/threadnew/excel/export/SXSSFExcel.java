package com.threadnew.excel.export;

import com.threadnew.excel.RemoveMarks;
import com.threadnew.excel.RowDefinition;
import com.threadnew.excel.exceldetails.SXSSFExcelDetails;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
    private SXSSFExcelDetails excelDetails = new SXSSFExcelDetails();
    private RemoveMarks removeMarks;

    protected void initialize() {
        wb = new SXSSFWorkbook(rowAccessWindowSize);
        wb.setCompressTempFiles(true);
        excelDetails.setRemoveMarks(removeMarks);
    }

    public void setRemoveMarks(RemoveMarks removeMarks) {
        this.removeMarks = removeMarks;
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
        initialize();
        SXSSFSheet sheet = excelDetails.createTitleRow(wb, title, rowDefinition);
        excelDetails.createContentRow(sheet, wb, out, result, target, rowDefinition);
        close(out);

    }
}
