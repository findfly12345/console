package com.allcheer.bpos.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;
/**
 * Created by fireWorks on 2016/2/29.
 */
public class HsmSession {

    private static final Logger logger = LoggerFactory
            .getLogger(HsmSession.class);
    private String sMainIP;
    private String sBackIP;
    private int nPort;
    private int nTimeOut;

    private Socket iSocketHandle = null;
    private InputStream iInputStream = null;
    private OutputStream iOutputStream = null;

    private int iLastErr = -1;

    // 创建连接
    public HsmSession() {
        iLastErr = 0;
        //初始化加密机连接
        try {
            InitSession(sMainIP,sBackIP,nPort,nTimeOut);
        }
        catch (Exception e) {
            iLastErr = HsmConst.ERR_CONFIG_FILE;
        }
    }

    // 创建连接
    public HsmSession(String aProfileFile) {
        iLastErr = 0;

        //初始化加密机连接
        try {
            InitSession(aProfileFile);
        }
        catch (Exception e) {
            iLastErr = HsmConst.ERR_CONFIG_FILE;
        }
    }

    private void InitSession(String ip, String back, int port, int timeout) throws Exception {

        //连接加密机
        connect(ip,port,timeout);
        if(iSocketHandle == null){
            connect(back,port,timeout);
            if(iSocketHandle == null){
                throw new Exception("无法与加密机建立连接.");
            }
        }
    }

    private void InitSession(String aProfileFile) throws Exception {
        String sDigit;

        //1、获取配制文件
        try {
            InputStream raf = HsmSession.class.getClassLoader().getResourceAsStream(aProfileFile);
            //FileInputStream raf = new FileInputStream(aProfileFile);
            Properties pr = new Properties();
            pr.load(raf);

            sMainIP = pr.getProperty("ENCRYPT_MAIN");
            sBackIP = pr.getProperty("ENCRYPT_BACK");

            sDigit = pr.getProperty("ENCRYPT_PORT");
            nPort = Integer.parseInt(sDigit);

            sDigit = pr.getProperty("ENCRYPT_TIMEOUT");
            nTimeOut = Integer.parseInt(sDigit);
            logger.debug("ENCRYPT_MAIN: [" + sMainIP + "]");
            logger.debug("ENCRYPT_BACK: [" + sBackIP+ "]");
            logger.debug("ENCRYPT_PORT: [" + nPort+ "]");
            logger.debug("ENCRYPT_TIMEOUT: [" + nTimeOut+ "]");

        }
        catch (Exception e) {
            throw new Exception("读取加密机连接配置文件[" + aProfileFile + "]发生错误.");
        }
        catch (Error err) {
            throw new Exception("读取加密机连接配置文件[" + aProfileFile + "]发生错误.");
        }

        //2、连接加密机
        connect(sMainIP,nPort,nTimeOut);
        if(iSocketHandle == null){
            connect(sBackIP,nPort,nTimeOut);
            if(iSocketHandle == null){
                throw new Exception("无法与加密机建立连接.");
            }
        }
    }

    public void connect(String sIP,int iPort, int iTimeout){
        try {
            iSocketHandle = new Socket();
            InetSocketAddress hsmAddr = new InetSocketAddress(sIP, iPort);
            logger.debug("before connect");
            iSocketHandle.connect(hsmAddr, iTimeout);
            logger.debug("after connect");
            iSocketHandle.setSoTimeout(iTimeout);
            iSocketHandle.setKeepAlive(true);
            iSocketHandle.setReceiveBufferSize(HsmConst.SECBUF_MAX_SIZE);
            iSocketHandle.setTcpNoDelay(true);
            iSocketHandle.setSoLinger(true, 0);//time_wait
            iInputStream = iSocketHandle.getInputStream();
            iOutputStream = iSocketHandle.getOutputStream();
            System.out.println("connect iOutputStream: " + iOutputStream);
            System.out.println("connect iInputStream: " + iInputStream);
        }
        catch (IOException e) {
            System.out.println("connect Error1: " + e);
            ReleaseSession();
        }
    }

    // 发送数据到加密机
    public int SendData(byte[] byteOut, int nLength) {
//		HsmApp.OutputDataHex("Data Send to HSM:",byteOut,nLength);

        try {
            iOutputStream.write(byteOut, 0, nLength);
        }
        catch (Exception e) {
            System.out.println("sendDate Error: " + e);
            return HsmConst.ERR_SENDTO_HSM;
        }
        catch (Error err) {
            System.out.println("sendDate Error: " + err);
            return HsmConst.ERR_SENDTO_HSM;
        }

        return HsmConst.T_SUCCESS;
    }

    // 从加密机接收数据
    public int RecvData(byte[] byteIn) {
        int rcvLen;

        try {
            rcvLen = iInputStream.read(byteIn, 0, HsmConst.SECBUF_MAX_SIZE);
        }
        catch (Exception e) {
            return HsmConst.ERR_RECVFORM_HSM;
        }
        catch (Error err) {
            return HsmConst.ERR_RECVFORM_HSM;
        }

//		HsmApp.OutputDataHex("Data Recv From HSM:",byteIn,rcvLen);
        return HsmConst.T_SUCCESS;
    }

    public void ReleaseSession() {
        if (iInputStream != null) {
            try {
                iInputStream.close();
            }
            catch (Exception e) {}
            iInputStream = null;
        }

        if (iOutputStream != null) {
            try {
                iOutputStream.close();
            }
            catch (Exception e) {}
            iOutputStream = null;
        }

        if (iSocketHandle != null) {
            try {
                iSocketHandle.close();
            }
            catch (Exception e) {}
            iSocketHandle = null;
        }
    }

    public int GetLastError() {
        return iLastErr;
    }


    public void SetErrCode(int nErrCode) {
        iLastErr = nErrCode;
        return;
    }

    public String ParseErrCode(int nErrCode) {
        String sMeaning;
        switch (nErrCode) {
            case 0:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "操作正确,状态正常";
                break;
            case HsmConst.ERR_CONFIG_FILE:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "配置文件异常";
                break;
            case HsmConst.ERR_CONNECT_HSM:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "连接密码机失败.";
                break;
            case HsmConst.ERR_SENDTO_HSM:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "发送数据至密码机失败.";
                break;
            case HsmConst.ERR_RECVFORM_HSM:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "接收密码机数据失败.";
                break;
            case HsmConst.ERR_SESSION_END:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "连接已关闭.";
                break;
            case HsmConst.ERR_HANDLE_FAULT:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "连接句柄状态异常.";
                break;
            default:
                sMeaning = "0x" + Integer.toHexString(nErrCode) + "异常操作,检查密码机日志.";
                break;
        }
        return sMeaning;
    }
}
