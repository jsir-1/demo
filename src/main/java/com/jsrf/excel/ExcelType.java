package com.jsrf.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jsrf
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ExcelType {
    //HSSFWorkbook对应的就是一个 .xls 文件，兼容 Office97-2003 版本
    String HSSF = "org.apache.poi.hssf.usermodel.HSSFWorkbook";
    //XSSFWorkbook对应的是一个 .xlsx 文件，兼容 Office2007 及以上版本
    String XSSF = "org.apache.poi.xssf.usermodel.XSSFWorkbook";
    //大数据的时候使用,低内存占用的操作方式
    String SXSSF = "org.apache.poi.xssf.streaming.SXSSFWorkbook";
}