package com.allcheer.bpos.util;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fireWorks on 2016/3/6.
 */
public class ExcelReader {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * 读取 office 2003 excel
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public List<List<Object>> read2003Excel(File file) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        int counter = 0;
        int rowNbr = sheet.getPhysicalNumberOfRows();
        System.out.println(rowNbr);
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
//            if ((row == null)) {
            if ((row == null)||(i<1)) {
                continue;
            } else {
                counter++;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null || cell.toString().trim().equals("")) {
                    value = " ";
                    linked.add(value);
                    continue;
                }
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        System.out.println(i + "行" + j + " 列 is String type"
                                + "\tValue:" + value);
                        break;
//                    case XSSFCell.CELL_TYPE_NUMERIC:
//                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
//                            value = df.format(cell.getNumericCellValue());
//                        } else if ("General".equals(cell.getCellStyle()
//                                .getDataFormatString())) {
//                            value = nf.format(cell.getNumericCellValue());
//                        } else {
//                            value = sdf.format(HSSFDateUtil.getJavaDate(cell
//                                    .getNumericCellValue()));
//                        }
//                        System.out.println(i + "行" + j
//                                + " 列 is Number type ; DateFormt:"
//                                + cell.getCellStyle().getDataFormatString()
//                                + "\tValue:" + value);
//                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        System.out.println(i + "行" + j
                                + " 列 is Number type ; ");
                        Double doubleVal = cell.getNumericCellValue();
                        long longVal = Math.round(cell.getNumericCellValue());
                        if(Double.parseDouble(longVal + ".0") == doubleVal)
                            value = longVal+"";
                        else
                            value = doubleVal+"";
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        System.out.println(i + "行" + j + " 列 is Boolean type"
                                + "\tValue:" + value);
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        value = "";
                        System.out.println(i + "行" + j + " 列 is Blank type"
                                + "\tValue:" + value);
                        break;
                    default:
                        value = cell.toString();
                        System.out.println(i + "行" + j + " 列 is default type"
                                + "\tValue:" + value);
                }
                if (value == null || "".equals(value)) {
                    continue;
                }
                linked.add(value);
            }

            list.add(linked);

        }
        return list;
    }

    public List<List<Object>> read2007Excel(InputStream in)throws IOException{
    	 List<List<Object>> list = new LinkedList<List<Object>>();
         // 构造 XSSFWorkbook 对象，strPath 传入文件路径
         XSSFWorkbook xwb = new XSSFWorkbook(in);
         // 读取第一章表格内容
         XSSFSheet sheet = xwb.getSheetAt(0);
//         sheet.
         Object value = null;
         XSSFRow row = null;
         XSSFCell cell = null;
         int counter = 0;
         for (int i = sheet.getFirstRowNum(); counter < sheet
                 .getPhysicalNumberOfRows(); i++) {
             row = sheet.getRow(i);
             if ((row == null)) {
                 continue;
             } else {
                 counter++;
             }
             List<Object> linked = new LinkedList<Object>();
             for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                 cell = row.getCell(j);
                 if (cell == null) {
                     continue;
                 }
                 DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                 // 字符
                 SimpleDateFormat sdf = new SimpleDateFormat(
                         "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                 DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                 switch (cell.getCellType()) {
                     case XSSFCell.CELL_TYPE_STRING:
                         System.out.println(i + "行" + j + " 列 is String type");
                         value = cell.getStringCellValue();
                         break;
                     case XSSFCell.CELL_TYPE_NUMERIC:
                         System.out.println(i + "行" + j
                                 + " 列 is Number type ; ");
                         Double doubleVal = cell.getNumericCellValue();
                         long longVal = Math.round(cell.getNumericCellValue());
                         if(Double.parseDouble(longVal + ".0") == doubleVal)
                             value = longVal+"";
                         else
                             value = doubleVal+"";
                         break;
                     case XSSFCell.CELL_TYPE_BOOLEAN:
                         System.out.println(i + "行" + j + " 列 is Boolean type");
                         value = cell.getBooleanCellValue();
                         break;
                     case XSSFCell.CELL_TYPE_BLANK:
                         System.out.println(i + "行" + j + " 列 is Blank type");
                         value = "";
                         break;
                     default:
                         System.out.println(i + "行" + j + " 列 is default type");
                         value = cell.toString();
                 }
                 if (value == null || "".equals(value)) {
                     continue;
                 }
                 linked.add(value);

             }
             list.add(linked);
         }
         return list;
    }
    /**
     * 读取Office 2007 excel
     */
    public List<List<Object>> read2007Excel(File file) throws IOException {
    	FileInputStream fi=new FileInputStream(file);
    	return read2007Excel(fi);
    }

    public List<String[]> readCsv(File file) throws IOException {

        FileReader fReader = new FileReader(file);
        CSVReader csvReader = new CSVReader(fReader);
        String[] strs = csvReader.readNext();
//        if(strs != null && strs.length > 0){
//            for(String str : strs)
//                if(null != str && !str.equals(""))
//                    System.out.print(str + " , ");
//            System.out.println("\n---------------");
//        }
        List<String[]> list = csvReader.readAll();
//        for(String[] ss : list){
//            for(String s : ss)
//                if(null != s && !s.equals(""))
//                    System.out.print(s + " , ");
//            System.out.println();
//        }
        csvReader.close();
        return list;
    }

    public List<String[]> readText(File file) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(file));
        String str;
        List<String[]> list = new ArrayList<>();

        while((str=bf.readLine())!=null)
        {
            String[] s=str.split("\\|");
            if (s.length > 1) {
                list.add(s);
            }
        }

        return list;
    }
}
