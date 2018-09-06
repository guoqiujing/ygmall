/**
 * 
 */
package cn.myzqu.ygmall.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {

	public   static  String encrypt(String source,String salt) {
		//散列次数
		int hashIterations = 2;
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		String password_md5 =  md5Hash.toString();
		return password_md5;
	}


}
