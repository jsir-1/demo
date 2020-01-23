package com.jsrf.excel;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jsrf
 */
public class ExcelUtil {
    public static void main(String[] args) throws Exception {
//        List<Person> data = getData();
//        ExcelUtil.createExcel(data, ExcelType.SXSSF, true, "/Users/jsrf/Desktop/data.xlsx");
        List<Person> list = readExcel("/Users/jsrf/Desktop/data.xlsx", Person.class);
    }

    private static List<Person> getData() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int num = i + 1;
            Person person = new Person();
            person.setId(num);
            person.setName("张三-" + (num));
            person.setAddress("花园路" + num + "号" + (int) Math.ceil(Math.random() * 10) + "号楼");
            person.setAge(i + 18);
            person.setHobby("洗脸刷牙打DOTA");
            person.setJob("程序员");
            person.setFather("程序员");
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

    /**
     * 获取所有的属性名
     *
     * @param clazz
     * @param isFatherFields
     * @return
     */
    private static Map<String, Field> getFieldsNameMap(Class clazz, Boolean isFatherFields) {
        HashMap<String, Field> map = Maps.newHashMap();
        Field[] fields;
        if (isFatherFields != null && isFatherFields) {
            fields = FieldUtils.getAllFields(clazz);
        } else {
            fields = clazz.getDeclaredFields();
        }
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        return map;
    }


    /**
     * 读取对应的文件
     *
     * @param file  文件
     * @param clazz 要转换的对象类型
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> readExcel(String file, Class<T> clazz) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(file), "必须指定要解析的文件");
        FileInputStream in = null;
        List<T> list = new ArrayList<>();
        try {
            Workbook workbook;
            in = new FileInputStream(file);
            if (file.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(in);
            } else if (file.endsWith(".xls")) {
                workbook = new HSSFWorkbook(in);
            } else {
                throw new RuntimeException("文件格式非法");
            }
            //读取的是第一张工作簿
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取所有字段名
            Map<String, Field> nameFieldsMap = getFieldsNameMap(clazz, false);
            HashMap<String, Method> methodMap = Maps.newHashMap();
            HashMap<Integer, String> colFieldMap = Maps.newHashMap();
            HashSet<Integer> unExistCol = Sets.newHashSet();
            //去除表头，从第 1 行开始打印
            for (int i = 0; i < rows; i++) {
                T obj = clazz.newInstance();
                Row row = sheet.getRow(i);
                //获取总列数
                int cols = row.getPhysicalNumberOfCells();
                for (int j = 0; j < cols; j++) {
                    //第一次循环时初始化所有 setter 方法名
                    Cell cell = row.getCell(j);
                    if (i == 0) {
                        String fieldName = cell.getStringCellValue();
                        Field field = nameFieldsMap.get(fieldName);
                        if (field == null) {
                            //某一列数据对象里面没有对应的属性
                            unExistCol.add(j);
                            continue;
                        }
                        colFieldMap.put(j, fieldName);
                        Method method = obj.getClass().getMethod("set" +
                                fieldName.substring(0, 1).toUpperCase() +
                                fieldName.substring(1), field.getType());
                        methodMap.put(fieldName, method);
                        continue;
                    }
                    if (unExistCol.contains(j)) {
                        continue;
                    }
                    //先将单元格中的值按 String 保存
                    String cellValue = cell.getStringCellValue();
                    //排除空值
                    if (StringUtils.isEmpty(cellValue)) {
                        continue;
                    }
                    //属性的类型
                    Field field = nameFieldsMap.get(colFieldMap.get(j));
                    //对象里面的字段表中没有对应的列
                    if (field == null) {
                        continue;
                    }
                    String typeName = field.getType().getSimpleName();
                    //set 方法
                    Method method = methodMap.get(colFieldMap.get(j));
                    //根据对象的不同属性字段转换单元格中的数据类型并调用 set 方法赋值
                    if ("Integer".equals(typeName) || "int".equals(typeName)) {
                        method.invoke(obj, Integer.parseInt(cellValue));
                    } else if ("String".equals(typeName)) {
                        method.invoke(obj, cellValue);
                    } else if ("Date".equals(typeName)) {
                        String pattern;
                        if (cellValue.contains("CST")) {
                            //java.util.Date 的默认格式
                            pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                        } else if (cellValue.contains(":")) {
                            //带有时分秒的格式
                            pattern = "yyyy-MM-dd HH:mm:ss";
                        } else if (cellValue.contains("/")) {
                            pattern = "yyyy/MM/dd HH:mm:ss";
                        } else {
                            //简单格式
                            pattern = "yyyy-MM-dd";
                        }
                        method.invoke(obj, new SimpleDateFormat(pattern, Locale.UK).parse(cellValue));
                    } else if ("Long".equalsIgnoreCase(typeName)) {
                        method.invoke(obj, Long.parseLong(cellValue));
                    } else if ("Double".equalsIgnoreCase(typeName)) {
                        method.invoke(obj, Double.parseDouble(cellValue));
                    } else if ("Boolean".equalsIgnoreCase(typeName)) {
                        method.invoke(obj, Boolean.parseBoolean(cellValue));
                    } else if ("Short".equalsIgnoreCase(typeName)) {
                        method.invoke(obj, Short.parseShort(cellValue));
                    } else if ("Character".equals(typeName) || "char".equals(typeName)) {
                        method.invoke(obj, cellValue.toCharArray()[0]);
                    } else {
                        //若是其他格式的数据，暂时认为没法解析
                        throw new RuntimeException("只能解析基本数据类型以及字符串");
                    }
                }
                //不要忘记这句
                if (i == 0) {
                    continue;
                }
                list.add(obj);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件读取错误");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException("参数解析失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}