package com.allcheer.bpos.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public final class ZipUtil {

	private ZipUtil() {
		// empty
	}

	/**
	 * 压缩文件
	 * 
	 * @param filePath
	 *            待压缩的文件路径
	 * @return 压缩后的文件
	 */
	public static String zip(String filePath) {
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(filePath+".zip");
			zipFile.addFolder(filePath, parameters);
		} catch (ZipException e) {
			throw new RuntimeException("压缩命令执行失败");
		}
		return filePath+".zip";
	}

	/**
	 * 解压文件
	 * 
	 * @param zipFileSource
	 *            压缩文件
	 * @param destPath
	 * 			  解压缩目录
	 * @return 压缩后的路径
	 */
	public static String unzip(String zipFileSource,String destPath) {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFileSource);
			zipFile.extractAll(destPath);
		} catch (ZipException e) {
			throw new RuntimeException("解压命令执行失败");
		}
		return destPath;
	}
}