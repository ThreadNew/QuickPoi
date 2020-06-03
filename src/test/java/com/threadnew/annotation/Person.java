package com.threadnew.annotation;

import com.threadnew.excel.annotation.CellType;
import com.threadnew.excel.annotation.ExcelRow;

/**
 * @Package: com.threadnew.annotation
 * @ClassName: Person
 * @Author: hase
 * @Description: TODO
 * @Date: 2020/5/27 16:27
 * @Version: 1.0
 */
public class Person {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
