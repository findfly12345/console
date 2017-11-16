package com.allcheer.bpos.util;

/**
 * Created by fireWorks on 2016/2/29.
 */
public class HsmApp {

    // 构造函数：
    public HsmApp() {
    }

    // 常用函数：数据打印：HEX格式
    static public void OutputDataHex(String sInfo, byte[] byteIn, int nDataLen) {
        int i, j, n, prev;

        System.out.println("[" + sInfo + "]" + "length" + "[" + nDataLen + "]");
        prev = n = 0;
        for (i = 0; i < nDataLen; i++) {
            if (i == (prev + 16)) {
                System.out.print("    ;");
                for (j = prev; j < prev + 16; j++) {
                    if (Character.isLetter((char) (byteIn[j] & 0xff)) == true) {
                        System.out.print((char) byteIn[j]);
                    } else {
                        System.out.print(" ");
                    }
                }
                prev += 16;
                System.out.println();
            }
            if (Integer.toHexString(byteIn[i] & 0xff).length() == 1) {
                System.out.print("0" + Integer.toHexString(byteIn[i] & 0xff)
                        + " ");
            } else {
                System.out.print(Integer.toHexString(byteIn[i] & 0xff) + " ");
            }
        }
        if (i != prev) {
            n = i;
            for (; i < prev + 16; i++) {
                System.out.print("   ");
            }
        }
        System.out.print("    ;");
        for (i = prev; i < n; i++) {
            if (Character.isLetter((char) byteIn[i]) == true) {
                System.out.print((char) byteIn[i]);
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static public boolean Str2Hex(byte[] in, byte[] out, int len) {
        byte[] asciiCode = { 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };

        if (len > in.length) {
            return false;
        }

        if (len % 2 != 0) {
            return false;
        }

        byte[] temp = new byte[len];

        for (int i = 0; i < len; i++) {
            if (in[i] >= 0x30 && in[i] <= 0x39) {
                temp[i] = (byte) (in[i] - 0x30);
            } else if (in[i] >= 0x41 && in[i] <= 0x46) {
                temp[i] = asciiCode[in[i] - 0x41];
            } else if (in[i] >= 0x61 && in[i] <= 0x66) {
                temp[i] = asciiCode[in[i] - 0x61];
            } else {
                return false;
            }
        }

        for (int i = 0; i < len / 2; i++) {
            out[i] = (byte) (temp[2 * i] * 16 + temp[2 * i + 1]);
        }

        return true;
    }

    static public boolean Hex2Str(byte[] in, byte[] out, int len) {
        byte[] asciiCode = { 0x41, 0x42, 0x43, 0x44, 0x45, 0x46 };

        if (len > in.length) {
            return false;
        }

        byte[] temp = new byte[2 * len];

        for (int i = 0; i < len; i++) {
            temp[2 * i] = (byte) ((in[i] & 0xf0) / 16);
            temp[2 * i + 1] = (byte) (in[i] & 0x0f);
        }

        for (int i = 0; i < 2 * len; i++) {
            if (temp[i] <= 9 && temp[i] >= 0) {
                out[i] = (byte) (temp[i] + 0x30);
            } else {
                out[i] = asciiCode[temp[i] - 0x0a];
            }
        }

        return true;
    }

    public static String byte2hex(byte[] b) { // 二行制转字符串
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;

            }
            if (n < b.length - 1) {
                hs = hs + " ";
            }
        }

        return hs.toUpperCase();

    }

    // 4字节长度
    /*
     * private int HSM_LINK(HsmSession hSession, byte[] bSecBufferIn, int
     * iSndLen, byte[] bSecBufferOut) { int nResult, nRetLen; byte[] tmpBuffer =
     * new byte[HsmConst.SECBUF_MAX_SIZE];
     *
     * // OutputDataHex("HSM_LINK Send:",bSecBufferIn,iSndLen); tmpBuffer[0] =
     * (byte)((iSndLen >> 24) & 0xFF); tmpBuffer[1] = (byte)((iSndLen >> 16) &
     * 0xFF); tmpBuffer[2] = (byte)((iSndLen >> 8) & 0xFF); tmpBuffer[3] =
     * (byte)(iSndLen & 0xFF);
     *
     * System.arraycopy(bSecBufferIn,0,tmpBuffer,4,iSndLen); // send message
     * nResult = hSession.SendData(tmpBuffer, iSndLen + 4); if (nResult !=
     * HsmConst.T_SUCCESS) { return nResult; }
     *
     * //receive message nResult = hSession.RecvData(tmpBuffer); if(nResult !=
     * HsmConst.T_SUCCESS) { return nResult; }
     *
     * if (tmpBuffer[4] != 'A') { return tmpBuffer[5]; }
     *
     * nRetLen = ((tmpBuffer[0] & 0xff )<< 24) | ((tmpBuffer[1] & 0xff) << 16) |
     * ((tmpBuffer[2] & 0xff) << 8) | (tmpBuffer[3] & 0xff);
     *
     * System.arraycopy(tmpBuffer,4,bSecBufferOut,0,nRetLen); //
     * OutputDataHex("HSM_LINK Receive:",bSecBufferOut,nRetLen); return
     * HsmConst.T_SUCCESS; }
     */
    // 无长度 短连接
    private int HSM_LINK(byte[] bSecBufferIn, int iSndLen, byte[] bSecBufferOut) {
        int nResult, nRetLen, nRet;
        byte[] tmpBuffer = new byte[HsmConst.SECBUF_MAX_SIZE];
        HsmSession hSession = new HsmSession();

        hSession = new
                HsmSession("system.properties");
        nRet = hSession.GetLastError();
        if (nRet != 0) {
            // System.out.println(hSession.ParseErrCode(nRet));
            // return nRet;
        }

        System.arraycopy(bSecBufferIn, 0, tmpBuffer, 0, iSndLen);
        // send message
        OutputDataHex("indata ", bSecBufferIn, iSndLen);
        nResult = hSession.SendData(tmpBuffer, iSndLen);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        // receive message
        nResult = hSession.RecvData(tmpBuffer);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        // OutputDataHex("outdata ", tmpBuffer, 30);
        nRet = bSecBufferIn[0];
        if (nRet == (byte) 0xB0) {
            if (tmpBuffer[0] != 'A') {
                // OutputDataHex("outdata1 ", tmpBuffer, 30);
                return tmpBuffer[9];
            }
        } else {
            if (tmpBuffer[0] != 'A') {
                // OutputDataHex("outdata2 ", tmpBuffer, nRet);
                return tmpBuffer[1];
            }
        }
        /*
         * if (tmpBuffer[0] != 'A') { OutputDataHex("outdata1 ", tmpBuffer, 30);
         * return tmpBuffer[9]; }
         */
        System.arraycopy(tmpBuffer, 0, bSecBufferOut, 0, 1000);

        hSession.ReleaseSession();

        return HsmConst.T_SUCCESS;
    }

    // 产生终端主密钥 返回主密钥明文和LMK加密的密文 B203
    public int HSMGenerateKEK(int iKEKLen, byte[] key_Lmk, byte[] key_Plain) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;
        byte len;
        if ((iKEKLen != HsmConst.S_Key_Len) && (iKEKLen != HsmConst.D_Key_Len)
                && (iKEKLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        bSecBufferIn[0] = (byte) 0xB2;
        bSecBufferIn[1] = (byte) 0x03;
        bSecBufferIn[2] = (byte) 0x00;
        bSecBufferIn[3] = (byte) 0x00;
        bSecBufferIn[4] = (byte) 0x00;
        bSecBufferIn[5] = (byte) 0x00;
        bSecBufferIn[6] = (byte) 0x00;
        bSecBufferIn[7] = (byte) 0x00;
        bSecBufferIn[8] = (byte) 0x00;
        bSecBufferIn[9] = (byte) 0x00;

        iSndLen = 2 + 8;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        System.arraycopy(bSecBufferOut, 9, key_Plain, 0, 8);

        len = 8;

        if (iKEKLen == HsmConst.D_Key_Len) {
            nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
            if (nResult != HsmConst.T_SUCCESS) {
                return nResult;
            }
            System.arraycopy(bSecBufferOut, 9, key_Plain, 8, 8);
            len = 16;
        }

        if (iKEKLen == HsmConst.T_Key_Len) {
            nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
            if (nResult != HsmConst.T_SUCCESS) {
                return nResult;
            }
            System.arraycopy(bSecBufferOut, 9, key_Plain, 8, 8);

            nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
            if (nResult != HsmConst.T_SUCCESS) {
                return nResult;
            }
            System.arraycopy(bSecBufferOut, 9, key_Plain, 16, 8);
            len = 24;
        }

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x08;
        bSecBufferIn[3] = (byte) 0x01;
        bSecBufferIn[2] = len;
        System.arraycopy(key_Plain, 0, bSecBufferIn, 4, len);

        iSndLen = 2 + 2 + len;
        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        System.arraycopy(bSecBufferOut, 2, key_Lmk, 0, len);

        return HsmConst.T_SUCCESS;
    }

    // 产生工作密钥 返回LMK和KEK加密的结果 D107
    public int HSMGenerateWorkingKey(int iKeyLen, int iKeyType, int iKEKLen,
                                     byte[] bKEKUndLMK, byte[] bCipherKeyUndLMK, byte[] bCipherKeyUndKEK) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;
        if ((iKeyLen != HsmConst.S_Key_Len) && (iKeyLen != HsmConst.D_Key_Len)
                && (iKeyLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if ((iKeyType != HsmConst.PIK_TYPE) && (iKeyType != HsmConst.MAC_TYPE)
                && (iKeyType != HsmConst.DATA_TYPE)
                && (iKeyType != HsmConst.KEY_TYPE))
            return HsmConst.ERROR_KEY_TYPE;

        if ((iKEKLen != HsmConst.S_Key_Len) && (iKEKLen != HsmConst.D_Key_Len)
                && (iKEKLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x07;
        bSecBufferIn[2] = (byte) (iKeyLen * 8);
        if (iKeyType == 1)
            bSecBufferIn[3] = (byte) iKeyType;
        else if (iKeyType == 2)
            bSecBufferIn[3] = (byte) 0x11;
        else if (iKeyType == 3)
            bSecBufferIn[3] = (byte) 0x12;
        else if (iKeyType == 4)
            bSecBufferIn[3] = (byte) 0x13;
        bSecBufferIn[4] = (byte) 0xFF;
        bSecBufferIn[5] = (byte) 0xFF;
        bSecBufferIn[6] = (byte) (iKEKLen * 8);

        System.arraycopy(bKEKUndLMK, 0, bSecBufferIn, 7, iKEKLen * 8);

        iSndLen = 2 + 1 + 1 + 2 + 1 + iKEKLen * 8;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        System.arraycopy(bSecBufferOut, 2, bCipherKeyUndLMK, 0, iKeyLen * 8);

        System.arraycopy(bSecBufferOut, 2 + iKeyLen * 8, bCipherKeyUndKEK, 0,
                iKeyLen * 8);

        return HsmConst.T_SUCCESS;
    }

    // 计算MAC D132
    public int HSMGenerateMac(int iKeyLen, int iAlg, byte[] bCipherMAKUndLMK,
                              int iDataLen, byte[] bData, byte[] bMac) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;

        if ((iKeyLen != HsmConst.S_Key_Len) && (iKeyLen != HsmConst.D_Key_Len)
                && (iKeyLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if ((iAlg != HsmConst.MAC_XOR) && (iAlg != HsmConst.MAC_99)
                && (iAlg != HsmConst.MAC_919))
            return HsmConst.ERROR_MAC_TYPE;

        if ((iDataLen > 2000) || (iDataLen < 0))
            return HsmConst.ERROR_MAC_LEN;

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x32;
        bSecBufferIn[2] = (byte) iAlg;
        bSecBufferIn[3] = (byte) (iKeyLen * 8);

        System.arraycopy(bCipherMAKUndLMK, 0, bSecBufferIn, 4, iKeyLen * 8);

        bSecBufferIn[4 + iKeyLen * 8] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 1] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 2] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 3] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 4] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 5] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 6] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 7] = (byte) 0x00;

        bSecBufferIn[4 + iKeyLen * 8 + 8] = (byte) ((iDataLen >> 8) & 0xff);
        bSecBufferIn[4 + iKeyLen * 8 + 9] = (byte) (iDataLen & 0xff);
        System
                .arraycopy(bData, 0, bSecBufferIn, 4 + iKeyLen * 8 + 10,
                        iDataLen);

        iSndLen = 2 + 1 + 1 + iKeyLen * 8 + 8 + 2 + iDataLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        System.arraycopy(bSecBufferOut, 1, bMac, 0, 8);
        return HsmConst.T_SUCCESS;
    }

    // 验证MAC D134
    public int HSMVerifyMac(int iKeyLen, int iAlg, byte[] bCipherMAKUndLMK,
                            byte[] bMac, int iDataLen, byte[] bData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;

        if ((iKeyLen != HsmConst.S_Key_Len) && (iKeyLen != HsmConst.D_Key_Len)
                && (iKeyLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if ((iAlg != HsmConst.MAC_XOR) && (iAlg != HsmConst.MAC_99)
                && (iAlg != HsmConst.MAC_919))
            return HsmConst.ERROR_MAC_TYPE;

        if ((iDataLen > 2000) || (iDataLen < 0))
            return HsmConst.ERROR_MAC_LEN;

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x34;
        bSecBufferIn[2] = (byte) iAlg;
        bSecBufferIn[3] = (byte) (iKeyLen * 8);

        System.arraycopy(bCipherMAKUndLMK, 0, bSecBufferIn, 4, iKeyLen * 8);

        bSecBufferIn[4 + iKeyLen * 8] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 1] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 2] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 3] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 4] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 5] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 6] = (byte) 0x00;
        bSecBufferIn[4 + iKeyLen * 8 + 7] = (byte) 0x00;
        System.arraycopy(bMac, 0, bSecBufferIn, 4 + iKeyLen * 8 + 8, 4);
        bSecBufferIn[4 + iKeyLen * 8 + 8 + 4] = (byte) ((iDataLen >> 8) & 0xff);
        bSecBufferIn[4 + iKeyLen * 8 + 9 + 4] = (byte) (iDataLen & 0xff);
        System.arraycopy(bData, 0, bSecBufferIn, 4 + iKeyLen * 8 + 10 + 4,
                iDataLen);

        iSndLen = 2 + 1 + 1 + iKeyLen * 8 + 8 + 4 + 2 + iDataLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        return HsmConst.T_SUCCESS;
    }

    // 解密PIN D126
    public int HSMDecryptPin(int iKeyLen, byte[] bCipherPIKUndLMK,
                             int PinFormat, byte[] InPIN, int AccNoLen, byte[] AccNo,
                             byte[] PlainPIN) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;

        if ((iKeyLen != HsmConst.S_Key_Len) && (iKeyLen != HsmConst.D_Key_Len)
                && (iKeyLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if ((PinFormat != 1) && (PinFormat != 6))
            return HsmConst.ERROR_PINFORMAT;

        if ((AccNoLen > 19) || (AccNoLen < 13))
            return HsmConst.ERROR_AccNo_Len;

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x26;
        bSecBufferIn[2] = (byte) (iKeyLen * 8);

        System.arraycopy(bCipherPIKUndLMK, 0, bSecBufferIn, 3, iKeyLen * 8);
        bSecBufferIn[3 + iKeyLen * 8] = (byte) PinFormat;

        System.arraycopy(InPIN, 0, bSecBufferIn, 3 + iKeyLen * 8 + 1, 8);
        System.arraycopy(AccNo, 0, bSecBufferIn, 3 + iKeyLen * 8 + 1 + 8,
                AccNoLen);

        iSndLen = 2 + 1 + iKeyLen * 8 + 1 + 8 + AccNoLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        i = bSecBufferOut[1];
        System.arraycopy(bSecBufferOut, 2, PlainPIN, 0, i);

        return HsmConst.T_SUCCESS;
    }

    // 转加密PIN D124
    public int HSMTransPin(int iKeyLen_In, byte[] bCipherPIK_InUndLMK,
                           int iKeyLen_Out, byte[] bCipherPIK_OutUndLMK, int PinFormat_In,
                           int PinFormat_Out, byte[] InPIN, int AccNoLen_In, byte[] AccNo_In,
                           int AccNoLen_Out, byte[] AccNo_Out, byte[] Out_Pin) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;

        if ((iKeyLen_In != HsmConst.S_Key_Len)
                && (iKeyLen_In != HsmConst.D_Key_Len)
                && (iKeyLen_In != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if ((iKeyLen_Out != HsmConst.S_Key_Len)
                && (iKeyLen_Out != HsmConst.D_Key_Len)
                && (iKeyLen_Out != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if ((PinFormat_In != 1) && (PinFormat_In != 6))
            return HsmConst.ERROR_PINFORMAT;

        if ((PinFormat_Out != 1) && (PinFormat_Out != 6))
            return HsmConst.ERROR_PINFORMAT;

        if (PinFormat_In == 1) {
            if ((AccNoLen_In > 19) || (AccNoLen_In < 13))
                return HsmConst.ERROR_AccNo_Len;
        }

        if (PinFormat_Out == 1) {
            if ((AccNoLen_Out > 19) || (AccNoLen_Out < 13))
                return HsmConst.ERROR_AccNo_Len;
        }

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x24;
        bSecBufferIn[2] = (byte) (iKeyLen_In * 8);
        i = 3;
        System.arraycopy(bCipherPIK_InUndLMK, 0, bSecBufferIn, i,
                iKeyLen_In * 8);
        i += iKeyLen_In * 8;
        bSecBufferIn[i] = (byte) (iKeyLen_Out * 8);
        i += 1;
        System.arraycopy(bCipherPIK_OutUndLMK, 0, bSecBufferIn, i,
                iKeyLen_Out * 8);
        i += iKeyLen_Out * 8;

        OutputDataHex("data111: ", bSecBufferIn, i);
        bSecBufferIn[i] = (byte) PinFormat_In;
        bSecBufferIn[i + 1] = (byte) PinFormat_Out;
        i += 2;

        OutputDataHex("data222: ", bSecBufferIn, i);

        System.arraycopy(InPIN, 0, bSecBufferIn, i, 8);
        i += 8;
        if (PinFormat_In == 1) {
            System.arraycopy(AccNo_In, 0, bSecBufferIn, i, AccNoLen_In);
            i += AccNoLen_In;
            bSecBufferIn[i] = (byte) 0x3b;
            i += 1;
        }
        if (PinFormat_Out == 1) {
            System.arraycopy(AccNo_Out, 0, bSecBufferIn, i, AccNoLen_Out);
            i += AccNoLen_Out;
            bSecBufferIn[i] = (byte) 0x3b;
            i += 1;
        }

        iSndLen = i;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        System.arraycopy(bSecBufferOut, 1, Out_Pin, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    // 产生随机密钥，由INDEX指定的ZMK加密后输出 0404
    public int HSMGenerateWKByZMK(int iKeyLen, short iKekIndex,
                                  byte[] bCipherKeyUndKEK, byte[] bWkCheckValue) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen;
        if ((iKeyLen != HsmConst.S_Key_Len) && (iKeyLen != HsmConst.D_Key_Len)
                && (iKeyLen != HsmConst.T_Key_Len))
            return HsmConst.ERROR_KEY_LEN;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0x04;
        bSecBufferIn[1] = (byte) 0x04;

        bSecBufferIn[2] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[3] = (byte) (iKekIndex & 0xFF);

        bSecBufferIn[4] = (byte) 0x01;

        bSecBufferIn[5] = (byte) (iKeyLen * 8);

        iSndLen = 2 + 2 + 1 + 1;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 2, bCipherKeyUndKEK, 0, iKeyLen * 8);
        System.arraycopy(bSecBufferOut, 2 + iKeyLen * 8, bWkCheckValue, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    // 由INDEX指定的ZMK解密数据 72
    public int HSMDecryptByZMK(short iDataLen, short iKekIndex,
                               byte[] bCipherData, byte[] bPlainData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen;
        if ((iDataLen & 7) != 0)
            return HsmConst.ERROR_DATA_LEN;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0x72;

        bSecBufferIn[1] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[2] = (byte) (iKekIndex & 0xFF);

        bSecBufferIn[11] = (byte) 0x00;
        bSecBufferIn[12] = (byte) 0x00;
        bSecBufferIn[13] = (byte) (iDataLen >> 8 & 0xFF);
        bSecBufferIn[14] = (byte) (iDataLen & 0xFF);

        System.arraycopy(bCipherData, 0, bSecBufferIn, 15, iDataLen);

        iSndLen = 15 + iDataLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 3, bPlainData, 0, iDataLen);

        return HsmConst.T_SUCCESS;
    }

    /************************************************************************
     * <0xD102> 导入密钥，将通信主密钥加密的工作密钥转换为用LMK加密（ZMK保存在加密机中） *
     ***********************************************************************/
    public int HSMTrnsKeyZMKtoLMK(int iKeyLen, int iKekIndex, int iKeyType,
                                  byte[] bCipherKeyUndKEK, byte[] bCipherKeyUndLMK,
                                  byte[] KeyCheckValue) {
        int iSndLen = 0, nResult = 0;
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        // Cmd code 0xD102
        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x02;
        iSndLen += 2;

        // key len & zmk len
        if (iKeyLen != 1 && iKeyLen != 2 && iKeyLen != 3)
            return HsmConst.ERROR_KEY_LEN;
        bSecBufferIn[2] = (byte) (iKeyLen * 8);
        bSecBufferIn[3] = (byte) 0x10;
        iSndLen += 2;

        if ((iKekIndex < 0) || (iKekIndex > 999))
            return HsmConst.ERROR_KEY_INDEX;
        // ZMK Index
        bSecBufferIn[4] = (byte) ((iKekIndex >> 8) & 0xff);
        bSecBufferIn[5] = (byte) (iKekIndex & 0xff);
        iSndLen += 2;

        if ((iKeyType != HsmConst.PIK_TYPE) && (iKeyType != HsmConst.MAC_TYPE)
                && (iKeyType != HsmConst.DATA_TYPE)
                && (iKeyType != HsmConst.KEY_TYPE))
            return HsmConst.ERROR_KEY_TYPE;
        if (iKeyType == 1)
            bSecBufferIn[6] = (byte) iKeyType;
        else if (iKeyType == 2)
            bSecBufferIn[6] = (byte) 0x11;
        else if (iKeyType == 3)
            bSecBufferIn[6] = (byte) 0x12;
        else if (iKeyType == 4)
            bSecBufferIn[6] = (byte) 0x13;
        iSndLen += 1;

        // Key by KEK
        System.arraycopy(bCipherKeyUndKEK, 0, bSecBufferIn, iSndLen,
                iKeyLen * 8);
        iSndLen += iKeyLen * 8;

        // send & receive message
        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 2, bCipherKeyUndLMK, 0, iKeyLen * 8);
        System.arraycopy(bSecBufferOut, 2 + iKeyLen * 8, KeyCheckValue, 0, 8);

        return HsmConst.T_SUCCESS;

    }

    /************************************************************************
     * <0xD107> 产生指定长度的随机工作密钥，并用LMK和指定ZMK加密后返回(ZMK保存在加密机中) * *
     ************************************************************************/
    public int HSMGenerateKey(int iKeyLen, int iKeyType, int iKekIndex,
                              byte[] bCipherKeyUndLMK, byte[] bCipherKeyUndKEK,
                              byte[] KeyCheckValue) {
        int iSndLen = 0, nResult = 0;
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        // Cmd code 0xD107
        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x07;
        iSndLen += 2;

        // key len
        if (iKeyLen != 1 && iKeyLen != 2 && iKeyLen != 3)
            return HsmConst.ERROR_KEY_LEN;
        bSecBufferIn[2] = (byte) (iKeyLen * 8);
        iSndLen += 1;

        // key type
        if ((iKeyType != HsmConst.PIK_TYPE) && (iKeyType != HsmConst.MAC_TYPE)
                && (iKeyType != HsmConst.DATA_TYPE)
                && (iKeyType != HsmConst.KEY_TYPE))
            return HsmConst.ERROR_KEY_TYPE;
        if (iKeyType == 1)
            bSecBufferIn[3] = (byte) iKeyType;
        else if (iKeyType == 2)
            bSecBufferIn[3] = (byte) 0x11;
        else if (iKeyType == 3)
            bSecBufferIn[3] = (byte) 0x12;
        else if (iKeyType == 4)
            bSecBufferIn[3] = (byte) 0x13;
        iSndLen++;

        // ZMK Index
        bSecBufferIn[4] = (byte) ((iKekIndex >> 8) & 0xff);
        bSecBufferIn[5] = (byte) (iKekIndex & 0xff);
        iSndLen += 2;

        // send & receive message
        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 2, bCipherKeyUndLMK, 0, iKeyLen * 8);
        System.arraycopy(bSecBufferOut, 2 + iKeyLen * 8, bCipherKeyUndKEK, 0,
                iKeyLen * 8);
        System.arraycopy(bSecBufferOut, 2 + iKeyLen * 8 + iKeyLen * 8,
                KeyCheckValue, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    /************************************************************************
     * <0xD10D> 导出区域主密钥,并用LMK加密 * *
     ************************************************************************/
    public int HSMGenerateLMK(int iKeyLen, int iKekIndex,
                              byte[] bCipherKeyUndLMK, byte[] KeyCheckValue) {
        int iSndLen = 0, nResult = 0;
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        // Cmd code 0xD10D
        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x0D;
        iSndLen += 2;
        // ZMK Index
        bSecBufferIn[2] = (byte) ((iKekIndex >> 8) & 0xff);
        bSecBufferIn[3] = (byte) (iKekIndex & 0xff);
        iSndLen += 2;

        // key len
        if (iKeyLen != 1 && iKeyLen != 2 && iKeyLen != 3)
            return HsmConst.ERROR_KEY_LEN;
        bSecBufferIn[4] = (byte) (iKeyLen * 8);
        iSndLen += 1;

        // send & receive message
        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 2, bCipherKeyUndLMK, 0, iKeyLen * 8);
        System.arraycopy(bSecBufferOut, 2 + iKeyLen * 8, KeyCheckValue, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    // 计算MAC 0410
    public int HSMEncryptByZMK(int iDataLen, int iKekIndex, byte[] bInData,
                               byte[] bOutData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, iLen;
        byte[] buf = new byte[8];
        byte[] mak = new byte[8];

        mak[0] = (byte) 0x00;
        mak[1] = (byte) 0x01;
        mak[2] = (byte) 0x02;
        mak[3] = (byte) 0x03;
        mak[4] = (byte) 0x04;
        mak[5] = (byte) 0x05;
        mak[6] = (byte) 0x06;
        mak[7] = (byte) 0x07;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0x04;
        bSecBufferIn[1] = (byte) 0x10;

        bSecBufferIn[2] = (byte) 0x01;
        bSecBufferIn[3] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[4] = (byte) (iKekIndex & 0xFF);
        bSecBufferIn[5] = (byte) 0x08;
        bSecBufferIn[6] = (byte) 0x01;
        System.arraycopy(mak, 0, bSecBufferIn, 7, 8);

        iLen = iDataLen;

        bSecBufferIn[23] = (byte) (iLen >> 8 & 0xFF);
        bSecBufferIn[24] = (byte) (iLen & 0xFF);
        System.arraycopy(bInData, 0, bSecBufferIn, 25, iDataLen);
        iSndLen = 25 + iLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 1, buf, 0, 8);
        Hex2Str(buf, bOutData, 8);

        return HsmConst.T_SUCCESS;
    }

    /* 分散密钥并计算过程密钥然后计算MAC */
    public int HSMGen_Mac_Pboc(int iKekIndex, int iDevNum, byte[] bDevData,
                               int iSessionFlag, byte[] bSessionData, byte[] bIv, int iDataLen,
                               byte[] bInData, byte[] bOutMac) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, iLen;

        if (iDevNum < 1 || iDevNum > 3)
            return HsmConst.ERROR_DEV_NUM;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        if ((iSessionFlag != 0) && (iSessionFlag != 2))
            return HsmConst.ERROR_ALGORITHM;

        bSecBufferIn[0] = (byte) 0xB0;
        bSecBufferIn[1] = (byte) 0x80;
        bSecBufferIn[2] = (byte) 0x00;
        bSecBufferIn[3] = (byte) 0x00;
        bSecBufferIn[4] = (byte) 0x00;
        bSecBufferIn[5] = (byte) 0x00;
        bSecBufferIn[6] = (byte) 0x00;
        bSecBufferIn[7] = (byte) 0x00;
        bSecBufferIn[8] = (byte) 0x00;
        bSecBufferIn[9] = (byte) 0x00;

        bSecBufferIn[10] = (byte) 0x00;
        bSecBufferIn[11] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[12] = (byte) (iKekIndex & 0xFF);
        bSecBufferIn[13] = (byte) iDevNum;
        System.arraycopy(bDevData, 0, bSecBufferIn, 14, 8 * iDevNum);

        iLen = 14 + (8 * iDevNum);

        bSecBufferIn[iLen] = (byte) iSessionFlag;
        iLen = iLen + 1;

        System.arraycopy(bSessionData, 0, bSecBufferIn, iLen, 8);
        iLen = iLen + 8;

        System.arraycopy(bIv, 0, bSecBufferIn, iLen, 8);
        iLen = iLen + 8;

        bSecBufferIn[iLen] = (byte) (iDataLen >> 8 & 0xFF);
        bSecBufferIn[iLen + 1] = (byte) (iDataLen & 0xFF);
        iLen = iLen + 2;

        System.arraycopy(bInData, 0, bSecBufferIn, iLen, iDataLen);
        iSndLen = iDataLen + iLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 9, bOutMac, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    /* 计算TAC */
    public int HSMGen_Tac_Pboc(int iKekIndex, int iDevNum, byte[] bDevData,
                               byte[] bIv, int iDataLen, byte[] bInData, byte[] bOutTac) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, iLen;

        if (iDevNum < 1 || iDevNum > 3)
            return HsmConst.ERROR_DEV_NUM;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0xB0;
        bSecBufferIn[1] = (byte) 0x83;
        bSecBufferIn[2] = (byte) 0x00;
        bSecBufferIn[3] = (byte) 0x00;
        bSecBufferIn[4] = (byte) 0x00;
        bSecBufferIn[5] = (byte) 0x00;
        bSecBufferIn[6] = (byte) 0x00;
        bSecBufferIn[7] = (byte) 0x00;
        bSecBufferIn[8] = (byte) 0x00;
        bSecBufferIn[9] = (byte) 0x00;

        bSecBufferIn[10] = (byte) 0x00;
        bSecBufferIn[11] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[12] = (byte) (iKekIndex & 0xFF);
        bSecBufferIn[13] = (byte) iDevNum;
        System.arraycopy(bDevData, 0, bSecBufferIn, 14, 8 * iDevNum);

        iLen = 14 + (8 * iDevNum);

        System.arraycopy(bIv, 0, bSecBufferIn, iLen, 8);
        iLen = iLen + 8;

        bSecBufferIn[iLen] = (byte) (iDataLen >> 8 & 0xFF);
        bSecBufferIn[iLen + 1] = (byte) (iDataLen & 0xFF);
        iLen = iLen + 2;

        System.arraycopy(bInData, 0, bSecBufferIn, iLen, iDataLen);
        iSndLen = iDataLen + iLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 9, bOutTac, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    /* 分散密钥加密数据 */
    public int HSMEncryptBySHSMK(int iKekIndex, int iDevNum, byte[] bDevData,
                                 int iDataLen, byte[] bInData, int[] iOutDataLen, byte[] bOutData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, iLen;

        if (iDevNum < 1 || iDevNum > 3)
            return HsmConst.ERROR_DEV_NUM;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0xB0;
        bSecBufferIn[1] = (byte) 0x91;
        bSecBufferIn[2] = (byte) 0x00;
        bSecBufferIn[3] = (byte) 0x00;
        bSecBufferIn[4] = (byte) 0x00;
        bSecBufferIn[5] = (byte) 0x00;
        bSecBufferIn[6] = (byte) 0x00;
        bSecBufferIn[7] = (byte) 0x00;
        bSecBufferIn[8] = (byte) 0x00;
        bSecBufferIn[9] = (byte) 0x00;

        bSecBufferIn[10] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[11] = (byte) (iKekIndex & 0xFF);
        bSecBufferIn[12] = (byte) iDevNum;
        System.arraycopy(bDevData, 0, bSecBufferIn, 13, 8 * iDevNum);

        iLen = 13 + (8 * iDevNum);

        bSecBufferIn[iLen] = (byte) (iDataLen >> 8 & 0xFF);
        bSecBufferIn[iLen + 1] = (byte) (iDataLen & 0xFF);
        iLen = iLen + 2;

        System.arraycopy(bInData, 0, bSecBufferIn, iLen, iDataLen);
        iSndLen = iDataLen + iLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        iOutDataLen[0] = bSecBufferOut[9] * 256 + bSecBufferOut[10];

        System.arraycopy(bSecBufferOut, 11, bOutData, 0, iOutDataLen[0]);

        return HsmConst.T_SUCCESS;
    }

    /* 不用过程密钥计算MAC */
    public int HSMGen_Mac1_Pboc(int iKekIndex, byte bMacFlag, int iDevNum,
                                byte[] bDevData, byte[] bIv, int iDataLen, byte[] bInData,
                                byte[] bOutMac) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, iLen;

        if (iDevNum < 1 || iDevNum > 3)
            return HsmConst.ERROR_DEV_NUM;

        if (iKekIndex < 0 || iKekIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0xB0;
        bSecBufferIn[1] = (byte) 0x93;
        bSecBufferIn[2] = (byte) 0x00;
        bSecBufferIn[3] = (byte) 0x00;
        bSecBufferIn[4] = (byte) 0x00;
        bSecBufferIn[5] = (byte) 0x00;
        bSecBufferIn[6] = (byte) 0x00;
        bSecBufferIn[7] = (byte) 0x00;
        bSecBufferIn[8] = (byte) 0x00;
        bSecBufferIn[9] = (byte) 0x00;

        bSecBufferIn[10] = (byte) 0x00;
        bSecBufferIn[11] = (byte) (iKekIndex >> 8 & 0xFF);
        bSecBufferIn[12] = (byte) (iKekIndex & 0xFF);
        bSecBufferIn[13] = bMacFlag;
        bSecBufferIn[14] = (byte) iDevNum;
        System.arraycopy(bDevData, 0, bSecBufferIn, 15, 8 * iDevNum);

        iLen = 15 + (8 * iDevNum);

        System.arraycopy(bIv, 0, bSecBufferIn, iLen, 8);
        iLen = iLen + 8;

        bSecBufferIn[iLen] = (byte) (iDataLen >> 8 & 0xFF);
        bSecBufferIn[iLen + 1] = (byte) (iDataLen & 0xFF);
        iLen = iLen + 2;

        System.arraycopy(bInData, 0, bSecBufferIn, iLen, iDataLen);
        iSndLen = iDataLen + iLen;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 9, bOutMac, 0, 8);

        return HsmConst.T_SUCCESS;
    }

    /* 用BMK解密PIK 0x41 */
    public int HSMDecryptPik(int iPikIndex, byte[] bInData, byte[] bOutData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult;

        if (iPikIndex < 0 || iPikIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0x41;
        System.arraycopy(bInData, 0, bSecBufferIn, 1, 8);

        bSecBufferIn[9] = (byte) (iPikIndex >> 8 & 0xFF);
        bSecBufferIn[10] = (byte) (iPikIndex & 0xFF);

        nResult = HSM_LINK(bSecBufferIn, 11, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        } else {
            System.arraycopy(bSecBufferOut, 1, bOutData, 0, 8);
        }

        return HsmConst.T_SUCCESS;
    }

    /*
     * 0xD108
     */
    public int HSMEncryptPik(int iKeyLen, int iKeyType, byte[] bInData,
                             byte[] bOutData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        // if((iKeyLen != HsmConst.S_Key_Len)&&(iKeyLen !=
        // HsmConst.D_Key_Len)&&(iKeyLen != HsmConst.T_Key_Len))
        // return HsmConst.ERROR_KEY_LEN;
        //
        // if((iKeyType != HsmConst.PIK_TYPE)&&(iKeyType !=
        // HsmConst.MAC_TYPE)&&(iKeyType != HsmConst.DATA_TYPE)&&(iKeyType !=
        // HsmConst.KEY_TYPE))
        // return HsmConst.ERROR_KEY_TYPE;

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x08;
        bSecBufferIn[2] = (byte) (iKeyLen * 8);
        if (iKeyType == 1)
            bSecBufferIn[3] = (byte) iKeyType;
        else if (iKeyType == 2)
            bSecBufferIn[3] = (byte) 0x11;
        else if (iKeyType == 3)
            bSecBufferIn[3] = (byte) 0x12;
        else if (iKeyType == 4)
            bSecBufferIn[3] = (byte) 0x13;
        System.arraycopy(bInData, 0, bSecBufferIn, 4, bInData.length);

        int nResult = HSM_LINK(bSecBufferIn, bInData.length + 10, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }

        System.arraycopy(bSecBufferOut, 2, bOutData, 0, bInData.length);

        return HsmConst.T_SUCCESS;
    }

    /**
     * 将银联文件中的终端主密钥转化为本地加密方式 参数：索引、密文
     *
     * @return
     */
    public String HSMEncryptBmkToLmk(int iKeyIndex, String key) {
        byte[] iPik = key.getBytes();
        byte[] pikNewIn1 = new byte[8];
        byte[] pikNewIn2 = new byte[8];

        byte[] oPik = new byte[iPik.length];
        boolean flag = Str2Hex(iPik, oPik, iPik.length);

        if (!flag) {
            // System.out.println("binary error1, return:" + flag);
            return "ERROR1";
        }

        System.arraycopy(oPik, 0, pikNewIn1, 0, 8);
        System.arraycopy(oPik, 8, pikNewIn2, 0, 8);

        byte[] pikNewOut1 = new byte[8];
        byte[] pikNewOut2 = new byte[8];

        // 调用0x41解密，获得明文
        int dResp1 = HSMDecryptPik(iKeyIndex, pikNewIn1, pikNewOut1);
        if (dResp1 != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp1);
            return "ERROR2";
        }
        // OutputDataHex("pik1: ", pikNewOut1, 8);

        int dResp2 = HSMDecryptPik(iKeyIndex, pikNewIn2, pikNewOut2);
        if (dResp2 != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp2);
            return "ERROR3";
        }
        // OutputDataHex("pik2: ", pikNewOut2, 8);

        // 调用D108加密明文
        byte[] pikEnc = new byte[16];
        byte[] result = new byte[16];
        System.arraycopy(pikNewOut1, 0, pikEnc, 0, 8);
        System.arraycopy(pikNewOut2, 0, pikEnc, 8, 8);

        int dResp3 = HSMEncryptPik(2, 1, pikEnc, result);
        if (dResp3 != HsmConst.T_SUCCESS) {
            // System.out.println("encrypt error, return:" + dResp3);
            return "ERROR4";
        }

        // OutputDataHex("result: ", result, 16);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < result.length; i++) {
            if (Integer.toHexString(result[i] & 0xff).length() == 1) {
                sb.append("0"
                        + Integer.toHexString(result[i] & 0xff).toUpperCase());
            } else {
                sb.append(Integer.toHexString(result[i] & 0xff).toUpperCase());
            }
        }

        return sb.toString();
    }

    /**
     * 调用D108对明文进行加密
     *
     * @return
     */
    public String HSMEncryotLmk(String key){
        byte[] iPik = key.getBytes();
        byte[] oPik = new byte[iPik.length];

        boolean flag = Str2Hex(iPik, oPik, iPik.length);
        if (!flag) {
            return "ERROR1";
        }
        byte[] pikNewOut1 = new byte[8];
        byte[] pikNewOut2 = new byte[8];

        System.arraycopy(oPik, 0, pikNewOut1, 0, 8);
        System.arraycopy(oPik, 8, pikNewOut2, 0, 8);

        // 调用D108加密明文
        byte[] pikEnc = new byte[16];
        byte[] result = new byte[16];
        System.arraycopy(pikNewOut1, 0, pikEnc, 0, 8);
        System.arraycopy(pikNewOut2, 0, pikEnc, 8, 8);

        int dResp3 = HSMEncryptPik(2, 1, pikEnc, result);
        if (dResp3 != HsmConst.T_SUCCESS) {
            return "ERROR4";
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < result.length; i++) {
            if (Integer.toHexString(result[i] & 0xff).length() == 1) {
                sb.append("0" + Integer.toHexString(result[i] & 0xff).toUpperCase());
            } else {
                sb.append(Integer.toHexString(result[i] & 0xff).toUpperCase());
            }
        }

        return sb.toString();
    }
    /**
     * 将银联文件中的终端主密钥转化为明文 参数：索引、密文
     *
     * @return
     */
    public String HSMEncryptBmk(int iKeyIndex, String key) {
        byte[] iPik = key.getBytes();
        byte[] pikNewIn1 = new byte[8];
        byte[] pikNewIn2 = new byte[8];

        byte[] oPik = new byte[iPik.length];
        boolean flag = Str2Hex(iPik, oPik, iPik.length);

        if (!flag) {
            // System.out.println("binary error1, return:" + flag);
            return "ERROR1";
        }

        System.arraycopy(oPik, 0, pikNewIn1, 0, 8);
        System.arraycopy(oPik, 8, pikNewIn2, 0, 8);

        byte[] pikNewOut1 = new byte[8];
        byte[] pikNewOut2 = new byte[8];

        // 调用0x41解密，获得明文
        int dResp1 = HSMDecryptPik(iKeyIndex, pikNewIn1, pikNewOut1);
        if (dResp1 != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp1);
            return "ERROR2";
        }
        // OutputDataHex("pik1: ", pikNewOut1, 8);

        int dResp2 = HSMDecryptPik(iKeyIndex, pikNewIn2, pikNewOut2);
        if (dResp2 != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp2);
            return "ERROR3";
        }
        // OutputDataHex("pik2: ", pikNewOut2, 8);

        // 调用D108加密明文
        byte[] pikEnc = new byte[16];
        byte[] result = new byte[16];
        System.arraycopy(pikNewOut1, 0, pikEnc, 0, 8);
        System.arraycopy(pikNewOut2, 0, pikEnc, 8, 8);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < pikEnc.length; i++) {
            if (Integer.toHexString(pikEnc[i] & 0xff).length() == 1) {
                sb.append("0"
                        + Integer.toHexString(pikEnc[i] & 0xff).toUpperCase());
            } else {
                sb.append(Integer.toHexString(pikEnc[i] & 0xff).toUpperCase());
            }
        }

        return sb.toString();
    }

    /*
     * 0xD112 用密钥加密数据
     */
    public String HSMEncryptData(String iData, String ikey) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];
        // 密钥必须是8位的
        if (ikey.length() != 8) {
            System.out.println("密钥长度错误！");
            return null;
        }
        byte[] iKeyByte = ikey.getBytes();
        byte[] iDataByte = iData.getBytes();
        byte[] oHexKey = new byte[iKeyByte.length];
        byte[] oHexData = new byte[iDataByte.length];
        boolean flag1 = Str2Hex(iKeyByte, oHexKey, iKeyByte.length);
        boolean flag2 = Str2Hex(iDataByte, oHexData, iDataByte.length);
        if (flag1 == false || flag2 == false) {
            System.out.println("数据转换为16进制错误！");
            return null;
        }

        // 调加密机命令
        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x12;
        // 调用方法 12――DES_ECB 21――DES_CBC 12――3DES_ECB(双长度)
        // 22――3DES_ECB（双长度）13――3DES_ECB（3长度） 23――3DES_ECB（3长度）
        bSecBufferIn[2] = (byte) 0x12;
        // 密钥长度 双精度一般是需要16位的密钥，而现在送入的肯定是8字节的
        for (int i = 0; i < 16; i++) {
            if (i < 8) {
                bSecBufferIn[i + 3] = (byte) oHexKey[i];
            } else {
                bSecBufferIn[i + 3] = (byte) 0x00;
            }
        }

        bSecBufferIn[19] = (byte) (((iDataByte.length) >> 8) & 0xff);
        bSecBufferIn[20] = (byte) ((iDataByte.length) & 0xff);

        System.arraycopy(oHexData, 0, bSecBufferIn, 21, oHexData.length);

        int nResult = HSM_LINK(bSecBufferIn, bSecBufferIn.length, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            System.out.println("发送加密机加密失败！");
            return null;
        }
        System.out.println("数据转换为16进制错误！");

        StringBuffer sb = new StringBuffer();
        // 第一位标志是否成功
        if ('A' != (char) bSecBufferOut[0]) {
            System.out.println("加密机加密失败！");
            return null;
        } else {
            System.out.println("加密机加密成功！");
        }
        int int1 = bSecBufferOut[1]&0xFF;
        int int2 = bSecBufferOut[2]&0xFF;
        int outLen = int1*256 + int2;

        for (int i = 3; i < outLen + 3; i++) {
            if (Integer.toHexString(bSecBufferOut[i] & 0xff).length() == 1) {
                sb.append("0"
                        + Integer.toHexString(bSecBufferOut[i] & 0xff)
                        .toUpperCase());
            } else {
                sb.append(Integer.toHexString(bSecBufferOut[i] & 0xff)
                        .toUpperCase());
            }
        }

        return sb.toString();
    }

    public String HSMEncryptBmkToLmkD102(int iKeyIndex, String key) {
        // 调用D102加密密文
        byte[] bCipherKeyUndKEK = key.getBytes();
        byte[] oPik = new byte[bCipherKeyUndKEK.length];
        boolean flag = Str2Hex(bCipherKeyUndKEK, oPik, bCipherKeyUndKEK.length);

        if (!flag) {
            // System.out.println("binary error1, return:" + flag);
            return "ERROR1";
        }
        byte[] bCipherKeyUndLMK = new byte[16];
        byte[] KeyCheckValue = new byte[8];
        int dResp = HSMTrnsKeyZMKtoLMK(2, iKeyIndex, 1, oPik, bCipherKeyUndLMK,
                KeyCheckValue);
        if (dResp != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp1);
            return "ERROR2";
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bCipherKeyUndLMK.length; i++) {
            if (Integer.toHexString(bCipherKeyUndLMK[i] & 0xff).length() == 1) {
                sb.append("0"
                        + Integer.toHexString(bCipherKeyUndLMK[i] & 0xff)
                        .toUpperCase());
            } else {
                sb.append(Integer.toHexString(bCipherKeyUndLMK[i] & 0xff)
                        .toUpperCase());
            }
        }

        return sb.toString();
    }

    public String HSMEncryptBmkToLmkD10D(int iKeyIndex) {
        // 调用D10D得到密钥
        byte[] bCipherKeyUndLMK = new byte[16];
        byte[] KeyCheckValue = new byte[8];
        int dResp = HSMGenerateLMK(2, iKeyIndex, bCipherKeyUndLMK,
                KeyCheckValue);
        if (dResp != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp1);
            return "ERROR2";
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bCipherKeyUndLMK.length; i++) {
            if (Integer.toHexString(bCipherKeyUndLMK[i] & 0xff).length() == 1) {
                sb.append("0"
                        + Integer.toHexString(bCipherKeyUndLMK[i] & 0xff)
                        .toUpperCase());
            } else {
                sb.append(Integer.toHexString(bCipherKeyUndLMK[i] & 0xff)
                        .toUpperCase());
            }
        }

        return sb.toString();
    }

    /***
     * 约定传入的报文是string格式的16进制的数据
     * iData：8d1bb0506f9fcd709a965ed1a54c28fa2128a3ba3d82e34d9864edd3e5d76ec5694b8d87d2054f571939a8fa46ce9f97ded790358d0f7e2e8973db96f4e6672c641c47ba306967ccd2adccbf66cbca7c1939a8fa46ce9f97076f9428df1741a0e9fe933473eb005bfd8b55b0f6994dde5368cfa477331eb68973db96f4e6672c8973db96f4e6672cfdefe4935be654e88973db96f4e6672c5320092b6f944e108973db96f4e6672c21aee3a54f8d371b57a3173998c14f00
     * iKey：f8f0079b6b529b4552434e00d3bbae66
     * 调用密钥机D114，用TDKey对报文进行解密
     */
//     public String TDKeyAnalysisData(String iData, String ikey) {
//        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
//        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];
//
//        //tdKey存储的都是32位的
//        if (ikey.length() != 32) {
//            System.out.println("密钥长度错误！");
//            return null;
//        }
//        if(iData.length()%2 != 0){
//            System.out.println("密钥长度错误！");
//            return null;
//        }
//
//        //直接将16进制的string转换成16进制的byte,2个字符代表一个byte
//        byte[] oHexKey = ASCIIToBCD.ASCII_To_BCD(ikey.getBytes(),ikey.length());
//        byte[] oHexData = ASCIIToBCD.ASCII_To_BCD(iData.getBytes(),iData.length());
//
//        // 调加密机命令
//        bSecBufferIn[0] = (byte) 0xD1;
//        bSecBufferIn[1] = (byte) 0x14;
//        // 调用方法 11――DES_ECB 21――DES_CBC 12――3DES_ECB(双长度)
//        // 22――3DES_ECB（双长度）13――3DES_ECB（3长度） 23――3DES_ECB（3长度）
//        bSecBufferIn[2] = (byte) 0x12;
//        // 密钥16位长度
//        System.arraycopy(oHexKey, 0, bSecBufferIn, 3, oHexKey.length);
//
//        bSecBufferIn[19] = (byte) (((oHexData.length) >> 8) & 0xff);
//        bSecBufferIn[20] = (byte) ((oHexData.length) & 0xff);
//
//        System.arraycopy(oHexData, 0, bSecBufferIn, 21, oHexData.length);
//
//        int nResult = HSM_LINK(bSecBufferIn, 21+oHexData.length, bSecBufferOut);
//        if (nResult != HsmConst.T_SUCCESS) {
//            System.out.println("发送加密机加密失败！");
//            return null;
//        }
//
//        StringBuffer sb = new StringBuffer();
//        // 第一位标志是否成功
//        if ('A' != (char) bSecBufferOut[0]) {
//            System.out.println("加密机解密失败！");
//            return null;
//        } else {
//            System.out.println("加密机解密成功！");
//        }
//        int int1 = bSecBufferOut[1]&0xFF;
//        int int2 = bSecBufferOut[2]&0xFF;
//        int outLen = int1*256 + int2;
//
//        System.out.println(outLen);
//        for (int i = 3; i < outLen + 3; i++) {
//            if (Integer.toHexString(bSecBufferOut[i] & 0xff).length() == 1) {
//                sb.append("0"
//                        + Integer.toHexString(bSecBufferOut[i] & 0xff)
//                                .toUpperCase());
//            } else {
//                sb.append(Integer.toHexString(bSecBufferOut[i] & 0xff)
//                        .toUpperCase());
//            }
//        }
//        return ASCIIToBCD.decode(sb.toString());
//     }

    // pos秘钥自动生成调用方法
    public int HSMAutoGenerateKey(byte[] bCipherKeyUndLMK, byte[] bCipherKeyUndKEK) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult, iSndLen, i;

        bSecBufferIn[0] = (byte) 0xD1;
        bSecBufferIn[1] = (byte) 0x07;
        bSecBufferIn[2] = (byte) 0x10;//长度为16位
        bSecBufferIn[3] = (byte) 0x01;//0x01通信主密钥TMK  0x11PIN加密密钥  0x12MAC计算密钥  0x13TDK数据加密密钥
        bSecBufferIn[4] = (byte) 0x00;
        bSecBufferIn[5] = (byte) 0x0B;//索引为十进制11

        iSndLen = 2 + 1 + 1 + 2;

        nResult = HSM_LINK(bSecBufferIn, iSndLen, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        }
        System.arraycopy(bSecBufferOut, 2, bCipherKeyUndLMK, 0, 2 * 8);

        System.arraycopy(bSecBufferOut, 2 + 2 * 8, bCipherKeyUndKEK, 0, 2 * 8);

        return HsmConst.T_SUCCESS;
    }
    /*用区域主密钥加密一个TMK密钥0x40 */
    public int HSMEncryptTmk (int iPikIndex, byte[] bInData, byte[] bOutData) {
        byte[] bSecBufferIn = new byte[HsmConst.SECBUF_MAX_SIZE];
        byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

        int nResult;

        if (iPikIndex < 0 || iPikIndex > HsmConst.MAX_ZMK_INDEX)
            return HsmConst.ERROR_KEY_INDEX;

        bSecBufferIn[0] = (byte) 0x40;
        System.arraycopy(bInData, 0, bSecBufferIn, 1, 8);

        bSecBufferIn[9] = (byte) (iPikIndex >> 8 & 0xFF);
        bSecBufferIn[10] = (byte) (iPikIndex & 0xFF);

        nResult = HSM_LINK(bSecBufferIn, 11, bSecBufferOut);
        if (nResult != HsmConst.T_SUCCESS) {
            return nResult;
        } else {
            System.arraycopy(bSecBufferOut, 1, bOutData, 0, 8);
        }

        return HsmConst.T_SUCCESS;
    }


    /**
     * 将银联文件中的终端主密钥转化为密文 参数：索引、密文
     *
     * @return
     */
    public String HSMEncryptZmk (int iKeyIndex, String key) {
        byte[] iPik = key.getBytes();
        byte[] pikNewIn1 = new byte[8];
        byte[] pikNewIn2 = new byte[8];

        byte[] oPik = new byte[iPik.length];
        //命令接收8位的密钥，因此将32位密钥压缩成16位，分两次发送加密机加密
        boolean flag = Str2Hex(iPik, oPik, iPik.length);

        if (!flag) {
            // System.out.println("binary error1, return:" + flag);
            return "ERROR1";
        }

        System.arraycopy(oPik, 0, pikNewIn1, 0, 8);
        System.arraycopy(oPik, 8, pikNewIn2, 0, 8);

        byte[] pikNewOut1 = new byte[8];
        byte[] pikNewOut2 = new byte[8];

        // 调用0x40加密，获得密文，密文长度为8位，需要扩展为16位
        int dResp1 = HSMEncryptTmk(iKeyIndex, pikNewIn1, pikNewOut1);
        if (dResp1 != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp1);
            return "ERROR2";
        }
        // OutputDataHex("pik1: ", pikNewOut1, 8);

        int dResp2 = HSMEncryptTmk(iKeyIndex, pikNewIn2, pikNewOut2);
        if (dResp2 != HsmConst.T_SUCCESS) {
            // System.out.println("decrypt error, return:" + dResp2);
            return "ERROR3";
        }
        // OutputDataHex("pik2: ", pikNewOut2, 8);

        // 调用D108加密明文
        byte[] pikEnc = new byte[16];
        byte[] result = new byte[16];
        System.arraycopy(pikNewOut1, 0, pikEnc, 0, 8);
        System.arraycopy(pikNewOut2, 0, pikEnc, 8, 8);

        StringBuffer sb = new StringBuffer();

        //扩展操作，将8位扩展成16位，得到32位的密文
        for (int i = 0; i < pikEnc.length; i++) {
            if (Integer.toHexString(pikEnc[i] & 0xff).length() == 1) {
                sb.append("0"
                        + Integer.toHexString(pikEnc[i] & 0xff).toUpperCase());
            } else {
                sb.append(Integer.toHexString(pikEnc[i] & 0xff).toUpperCase());
            }
        }

        return sb.toString();
    }
}
