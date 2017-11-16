package com.allcheer.bpos.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by APPLE on 16/9/5.
 */
public class UnixUtil {

    private static final Logger logger = LoggerFactory.getLogger(UnixUtil.class);


    public static String doExecUnixZipCMD(String folder) {
        String cmdString = "";
        try {
            // Process exec = null;
            int chmod = 0, zip = 0;

            // 1.赋文件夹权限
            cmdString = "chmod 777 " + folder;
            logger.debug("1.赋文件夹权限: " + cmdString);
            chmod = Runtime.getRuntime().exec(cmdString).waitFor();

            // 2.执行压缩命令
            cmdString = "zip -r " + folder + ".zip " + folder;
            logger.debug("2.执行压缩命令: " + cmdString);
            zip = Runtime.getRuntime().exec(cmdString).waitFor();

            logger.debug("chmod={}, zip={}",chmod,zip);

        } catch (Exception e) {
            logger.error("压缩命令执行失败:{}命令{}",e.getMessage(),cmdString);
            throw new RuntimeException("压缩命令执行失败");
        } finally {
            logger.debug("AAA压缩命令执行结束");
            return folder+".zip";
        }
    }

    public static String doExecUnixUNZipCMD(String zipFile,String destPath) {
        String cmdString = "";
        try {

            int chmod = 0, zip = 0;
            // 1.赋文件夹权限
            cmdString = "chmod 777 " + zipFile;
            logger.debug("1.赋文件夹权限: " + cmdString);
            chmod = Runtime.getRuntime().exec(cmdString).waitFor();

            // 2.执行压缩命令
            cmdString = "unzip " + zipFile + " -d " + destPath;
            logger.debug("2.执行解压命令: " + cmdString);
            zip = Runtime.getRuntime().exec(cmdString).waitFor();

            logger.debug("chmod={}, zip={}",chmod,zip);

        } catch (Exception e) {
            logger.error("解压命令执行失败:{}命令{}",e.getMessage(),cmdString);
            throw new RuntimeException("解压命令执行失败");
        } finally {
            logger.debug("解压命令执行结束");
            return destPath;
        }
    }

    public static void doExecUnixRMCMD(String filePath) {
        String cmdString = "";
        try {
            // Process exec = null;
            int chmod = 0, zip = 0;

            // 1.赋文件夹权限
            cmdString = "rm -rf "+filePath;
            logger.debug("删除文件: " + cmdString);
            chmod = Runtime.getRuntime().exec(cmdString).waitFor();


        } catch (Exception e) {
            logger.error("删除命令执行失败:{}命令{}",e.getMessage(),cmdString);
            throw new RuntimeException("删除命令执行失败");
        } finally {
            logger.debug("删除命令执行结束");
        }
    }

    public static void doExecUnixCPCMD(String srcFileFoldPath,String srcFileName,String destFileFoldPath) {
        String cmdString = "";
        try {
            int cp = 0;

            // 1.赋文件夹权限
            cmdString = "cp -r "+srcFileFoldPath+srcFileName+" "+destFileFoldPath;
            logger.debug("cp文件: " + cmdString);
            cp = Runtime.getRuntime().exec(cmdString).waitFor();
        } catch (Exception e) {
            logger.error("cp命令执行失败:{}命令{}",e.getMessage(),cmdString);
            throw new RuntimeException("cp命令执行失败");
        } finally {
            logger.debug("cp命令执行结束");
        }
    }
}
