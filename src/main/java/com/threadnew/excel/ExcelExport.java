package com.threadnew.excel;

import java.io.OutputStream;
import java.util.List;

public interface ExcelExport {
    void excelExport(OutputStream out, List result, Class target, String title, RowDefinition rowDefinition) throws Exception;

    void setRemoveMarks(RemoveMarks removeMarks);
}
