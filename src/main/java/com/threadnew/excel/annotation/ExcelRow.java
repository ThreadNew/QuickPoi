package com.threadnew.excel.annotation;


import java.lang.annotation.*;
/*
 * excel行的注解
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ExcelRow {

    String title() default "";//列的标题

    int cellNum() default 0;//列数

    int cellWidth() default 20;//单元格的宽度

    CellType cellType() default CellType.TextType;//常见的类型

    String dateFormat() default "yyyy-MM-dd HH:mm:ss";//日期格式

    boolean remove() default false;

    int[] rmvGroup() default 0; //移除该列的分组


}
