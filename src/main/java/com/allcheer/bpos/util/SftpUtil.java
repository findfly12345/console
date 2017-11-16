package com.allcheer.bpos.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SftpUtil {
	private Logger logger = LoggerFactory.getLogger(SftpUtil.class);
	private String key;
	private String user;
	private String ip;
	private int port;
	private String passwd;

	/**
	 * @param ip
	 * @param port
	 * @param user
	 * @param passwd
	 *            不用传null
	 * @param key
	 *            ssh免密钥，不用传null
	 */
	public SftpUtil(String ip, int port, String user, String passwd, String key) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.passwd = passwd;
		this.key = key;
	}

	private Channel getConnection() throws JSchException {
		JSch jsch = new JSch();
		Session session = null;
		if (!StringUtils.isBlank(key)) {
			jsch.addIdentity(key);
		}
		if (port == -1) {
			session = jsch.getSession(user, ip);
		} else {
			session = jsch.getSession(user, ip, port);
		}

		if (!StringUtils.isBlank(passwd)) {
			session.setPassword(passwd);
		}

		session.setConfig("StrictHostKeyChecking", "no");
		session.connect(30000); // 通过Session建立链接
		logger.info("连接到sftp服务器");
		Channel channel = session.openChannel("sftp"); // 打开SFTP通道
		channel.connect(10000); // 建立SFTP通道的连接
		logger.info("打开通道");
		return channel;
	}

	public InputStream getFile(String sftpDir, String fileName) throws Exception {
		String systemSeparator = File.separator;
		String dir = com.allcheer.bpos.constant.SystemConstant.CHECK_FILE_DIR;
		File tmpDir = new File(dir);
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
		ChannelSftp channel = (ChannelSftp) getConnection();
		channel.get(sftpDir + "/" + fileName, dir);
		channel.quit();
		File file = new File(dir + systemSeparator + fileName);

		if (file.exists()) {
			return new FileInputStream(file);
		}
		return null;
	}

	private void putFile() throws Exception {
		ChannelSftp channel = (ChannelSftp) getConnection();
		channel.put("D:\\DZ101120160322.txt", "upload");
		channel.quit();
	}

	public static void main(String[] args) throws Exception {
		SftpUtil sftp = new SftpUtil("192.168.242.128", 22, "mysftp", "mysftp", null);
		sftp.putFile();
		// sftp.getFile("upload","112233.xlsx");
	}
}
