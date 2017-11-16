package com.allcheer.bpos.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.RichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by APPLE on 16/6/30.
 */
public class ExcelUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private String srcXlsPath = "";// // excel模板路径
    private String desXlsPath = "";
    private String sheetName = "";
    POIFSFileSystem fs = null;
    HSSFWorkbook wb = null;
    HSSFSheet sheet = null;

    /**
     * 设置excel模板路径
     * @param srcXlsPath
     */
    public void setSrcPath(String srcXlsPath) {
        this.srcXlsPath = srcXlsPath;
    }

    /**
     * 设置要生成excel文件路径
     * @param desXlsPath
     */
    public void setDesPath(String desXlsPath) {
        this.desXlsPath = desXlsPath;
    }

    /**
     * 设置模板中哪个Sheet列
     * @param sheetName
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * 获取所读取excel模板的对象
     */
    public void getSheet() {
        try {
            File fi = new File(srcXlsPath);
            if(!fi.exists()){
                logger.error("模板文件{}不存在",srcXlsPath);
               throw new RuntimeException("模板文件不存在"+srcXlsPath);
            }
            fs = new POIFSFileSystem(new FileInputStream(fi));
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheet(sheetName);
            sheet = wb.getSheetAt(0);
        } catch (FileNotFoundException e) {
            logger.error("模板文件{}不存在", srcXlsPath);
            throw new RuntimeException("模板文件不存在"+srcXlsPath);
        } catch (IOException e) {
            logger.error("获取模板文件{}IO异常", srcXlsPath);
            throw new RuntimeException("获取模板文件IO异常"+srcXlsPath);
        }
    }

    /**
     * 获取所读取excel模板的对象
     */
    public void getSheetByIndex(int i) {
        try {
            File fi = new File(srcXlsPath);
            if(!fi.exists()){
                logger.error("模板文件{}不存在", srcXlsPath);
                throw new RuntimeException("模板文件不存在"+srcXlsPath);
            }
            fs = new POIFSFileSystem(new FileInputStream(fi));
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(i);
            logger.error("{}sheet名称为{}",i,sheet.getSheetName());
        } catch (FileNotFoundException e) {
            logger.error("模板文件{}不存在", srcXlsPath);
            throw new RuntimeException("模板文件不存在"+srcXlsPath);
        } catch (IOException e) {
            logger.error("获取模板文件{}IO异常", srcXlsPath);
            throw new RuntimeException("获取模板文件IO异常"+srcXlsPath);
        }
    }

    /**
     * 设置字符串类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--字符串类型的数据
     */
    public void setCellStrValue(int rowIndex, int cellnum, String value) {
        if(sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        if(cell == null) {
            cell = sheet.getRow(rowIndex).createCell(cellnum);
        }
        cell.setCellValue(value);
    }

    /**
     * 设置日期/时间类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--日期/时间类型的数据
     */
    public void setCellDateValue(int rowIndex, int cellnum, Date value) {
        if(sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        if(cell == null) {
            cell = sheet.getRow(rowIndex).createCell(cellnum);
        }
        cell.setCellValue(value);
    }

    /**
     * 设置浮点类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--浮点类型的数据
     */
    public void setCellDoubleValue(int rowIndex, int cellnum, double value) {
        if(sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        if(cell == null) {
            cell = sheet.getRow(rowIndex).createCell(cellnum);
        }
        cell.setCellValue(value);
    }

    /**
     * 设置Bool类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--Bool类型的数据
     */
    public void setCellBoolValue(int rowIndex, int cellnum, boolean value) {
        if(sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        if(cell == null) {
            cell = sheet.getRow(rowIndex).createCell(cellnum);
        }
        cell.setCellValue(value);
    }

    /**
     * 设置日历类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--日历类型的数据
     */
    public void setCellCalendarValue(int rowIndex, int cellnum, Calendar value) {
        if(sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        if(cell == null) {
            cell = sheet.getRow(rowIndex).createCell(cellnum);
        }
        cell.setCellValue(value);
    }

    /**
     * 设置富文本字符串类型的数据。可以为同一个单元格内的字符串的不同部分设置不同的字体、颜色、下划线
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--富文本字符串类型的数据
     */
    public void setCellRichTextStrValue(int rowIndex, int cellnum,
                                        RichTextString value) {
        if(sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        if(cell == null) {
            cell = sheet.getRow(rowIndex).createCell(cellnum);
        }
        cell.setCellValue(value);
    }

    /**
     * 完成导出
     */
    public void exportToNewFile() {
        FileOutputStream out;
        try {
            out = new FileOutputStream(desXlsPath);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            logger.error("目标文件{}不存在", desXlsPath);
            throw new RuntimeException("目标文件不存在"+desXlsPath);
        } catch (IOException e) {
            logger.error("目标文件{}IO流异常", desXlsPath);
            throw new RuntimeException("目标文件IO流异常"+desXlsPath);
        }
    }

    /**
     * 完成导出
     */
    public void exportToNewFile(ServletOutputStream servletOutputStream) {

        try {
            wb.write(servletOutputStream);
            servletOutputStream.close();
        } catch (FileNotFoundException e) {
            logger.error("目标文件不存在{}", e);
            throw new RuntimeException("目标文件不存在"+e);
        } catch (IOException e) {
            logger.error("目标文件IO流异常{}", e);
            throw new RuntimeException("目标文件IO流异常"+e);
        }
    }
}
