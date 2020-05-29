package com.threadnew.annotation;

import com.threadnew.excel.annotation.CellType;
import com.threadnew.excel.annotation.ExcelRow;

import java.util.Date;

/**
 * @Package: com.threadnew.annotation
 * @ClassName: UserInfo
 * @Author: hase
 * @Description: TODO
 * @Date: 2020/5/28 15:03
 * @Version: 1.0
 */
public class UserInfo {
    @ExcelRow(name="name",title = "姓名",cellType = CellType.TextType,cellNum = 0)
    private String name;
    @ExcelRow(name="age",title = "年龄",cellType = CellType.TextType,cellNum = 1)
    private int age;
    @ExcelRow(name="address",title = "地址",cellType = CellType.TextType,cellNum = 2)
    private String address;
    @ExcelRow(name="datetime",title = "日期",cellType = CellType.DateType,cellNum = 4,dateFormat = "yyyy-MM-dd")
    private Date datetime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
