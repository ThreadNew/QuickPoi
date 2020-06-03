package com.threadnew.annotation;

import com.threadnew.excel.utils.ArrayInArray;
import org.junit.Test;

/**
 * @ClassName ArrayInArrayTest
 * @Description TODO
 * @Author ThreadNew
 * @Date 2020/6/3 14:56
 * Version 1.0
 **/
public class ArrayInArrayTest {
    @Test
    public void test1(){
        int[] arr1={1,2,3};
        int[] arr2={7};
        System.out.println(ArrayInArray.isContain(arr1, arr2));
    }

}
