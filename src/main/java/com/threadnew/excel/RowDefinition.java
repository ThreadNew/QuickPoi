package com.threadnew.excel;

import java.util.LinkedList;
import java.util.List;

/**
 * @Package: com.threadnew.excel
 * @ClassName: RowDefinition
 * @Author: hase
 * @Description: TODO  自定义的数据结构：一行中包含的列。
 * @Date: 2020/5/27 15:48
 * @Version: 1.0
 */
public class RowDefinition {
    private List<CellDefinition> cellDefinitions = new LinkedList<>();//excel中每列数据

    public void addCellDefinition(CellDefinition cellDefinition) {
        this.cellDefinitions.add(cellDefinition);
    }

    public List<CellDefinition> getCellDefinitions() {
        return cellDefinitions;
    }

    //找出最大的列数
    public int maxCellNum() {
        //
        int max = 0;
        for (CellDefinition cel : cellDefinitions) {
            if (max <= cel.getCellNum()) max = cel.getCellNum();
        }
        return max;
    }
}
