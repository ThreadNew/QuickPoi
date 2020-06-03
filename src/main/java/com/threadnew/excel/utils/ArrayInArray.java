package com.threadnew.excel.utils;

import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName ArrayInArray
 * @Description TODO
 * @Author ThreadNew
 * @Date 2020/6/3 14:33
 * Version 1.0
 **/
public class ArrayInArray {
    public static boolean isContain(int[] arr1,int[] arr2){
        List<Integer> list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        List<Integer> collect = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        Set<Integer> s1 = new HashSet<Integer>(list1);
        Set<Integer> s2 = new HashSet<Integer>(collect);
        s1.retainAll(s2);
        if(s1.size()>0) return true;
        return false;
    }
}
