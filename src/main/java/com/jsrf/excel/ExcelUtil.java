package com.jsrf.excel;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jsrf
 */
public class ExcelUtil {
    public static void main(String[] args) throws Exception {
        List<Person> data = getData();
        ExcelUtil.createExcel(data, ExcelType.SXSSF, true, "/Users/jsrf/Desktop/data.xlsx");
    }

    private static List<Person> getData() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            int num = i + 1;
            Person person = new Person();
            person.setId(num);
            person.setName("张三-" + (num));
            person.setAddress("花园路" + num + "号" + (int) Math.ceil(Math.random() * 10) + "号楼");
            person.setAge(i + 18);
            person.setHobby("洗脸刷牙打DOTA");
            person.setJob("程序员");
            person.setFather("程序员");
            person.setList(Lists.newArrayList("haha"));
            list.add(person);
        }
        return list;
    }

    /**
     * 构造 Workbook 对象，具体实例化哪种对象由 type 参数指定
     *
     * @param data               要导出的数据
     * @param type               Excel 生成方式
     * @param isNeedFatherFields 是否要导出父类的属性
     * @param file               生成的文件
     * @return 对应 type 的工作簿实例对象
     */
    public static void createExcel(List data, @ExcelType String type, Boolean isNeedFatherFields, String file) throws Exception {
        FileOutputStream out = null;
        try {
            if (CollectionUtils.isEmpty(data)) {
                throw new Exception("数据不能为空");
            }
            if (type == null) {
                type = ExcelType.SXSSF;
            }
            switch (type) {
                case ExcelType.HSSF:
                    Assert.isTrue(file.endsWith(".xls"), "文件路径应以.xls结尾");
                    break;
                default:
                    Assert.isTrue(file.endsWith(".xlsx"), "文件路径应以.xlsx结尾");
            }
            out = new FileOutputStream(file);
            //根据类型生成工作簿
            Workbook workbook = (Workbook) Class.forName(type).newInstance();
            //新建表格
            Sheet sheet = workbook.createSheet();
            //生成表头
            Row header = sheet.createRow(0);
            String[] fieldsName = getFieldsName(data.get(0).getClass(), isNeedFatherFields);
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(fieldsName[i]);
            }
            //保存所有属性的getter方法名
            Method[] methods = new Method[fieldsName.length];
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Object obj = data.get(i);
                for (int j = 0; j < fieldsName.length; j++) {
                    //加载第一行数据时，初始化所有属性的getter方法
                    if (i == 0) {
                        String fieldName = fieldsName[j];
                        //处理boolean类型的属性
                        try {
                            methods[j] = obj.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                        } catch (NoSuchMethodException e) {
                            methods[j] = obj.getClass().getMethod("is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                        }
                    }
                    Cell cell = row.createCell(j);
                    Object value = methods[j].invoke(obj);
                    //注意判断 value 值是否为空
                    if (value == null) {
                        value = "";
                    }
                    cell.setCellValue(value.toString());
                }
            }
            workbook.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("文件创建失败,请确认文件路径和文件后缀");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 获取对象的属性名数组
     *
     * @param clazz          Class 对象，用于获取该类的信息
     * @param isFatherFields 是否需要导出父类中的属性
     * @return 该类的所有属性名数组
     */
    private static String[] getFieldsName(Class clazz, Boolean isFatherFields) {
        Field[] fields;
        if (isFatherFields != null && isFatherFields) {
            fields = FieldUtils.getAllFields(clazz);
        } else {
            fields = clazz.getDeclaredFields();
        }
        Set<String> set = new LinkedHashSet<>();
        for (Field field : fields) {
            set.add(field.getName());
        }
        String[] fieldNames = new String[set.size()];
        int i = 0;
        for (String s : set) {
            fieldNames[i++] = s;
        }
        return fieldNames;
    }
}