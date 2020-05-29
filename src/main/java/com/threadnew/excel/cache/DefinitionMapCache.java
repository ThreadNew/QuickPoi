package com.threadnew.excel.cache;

import com.threadnew.excel.RowDefinition;
import com.threadnew.excel.exception.ExcelExportException;


import java.util.Map;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.threadnew.excel.cache
 * @ClassName: DefinitionMapCache
 * @Author: hase
 * @Description: TODO 缓存所解析的类的@ExcelRow属性
 * @Date: 2020/5/27 18:38
 * @Version: 1.0
 */
public class DefinitionMapCache {
    private static final Map<String, RowDefinition> definitionMap = new ConcurrentHashMap<String, RowDefinition>();

    public static void putDefinitionMap(String name, RowDefinition rowDefinition) {
        if (name != null && !name.equals("")) {
            RowDefinition rowDefinition1 = definitionMap.get(name);
            if (rowDefinition == null) {
                definitionMap.put(name, rowDefinition);
            }
        } else {

            try {
                throw new ExcelExportException("this name is null and '' ");
            } catch (ExcelExportException e) {
                e.printStackTrace();
            }

        }
    }

    public static RowDefinition getDefinitionByName(String name) {
        return definitionMap.get(name);
    }
}
