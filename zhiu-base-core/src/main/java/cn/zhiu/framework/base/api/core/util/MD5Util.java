package cn.zhiu.framework.base.api.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	// 全局数组
	private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

	public static String GetMD5Code(String profix, String sourceString) {
		String resultString = encoderByMd5(encoderByMd5(sourceString) + profix );
		return resultString;
	}

	public static String encoderByMd5(String buf) {
		String resultString ="";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteToString(md.digest(buf.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException("md5加密错误", ex);
		}
		return resultString;

	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	public static void main(String[] args) {
		System.out.println( MD5Util.GetMD5Code(  "HXWcjvQWVG1wI4FQBLZpQ3pEdsaDFaas" , "222222" ) );
	}



}
