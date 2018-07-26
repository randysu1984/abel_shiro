package com.abel.shiro.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.AbstractHash;

public class CustomCredentialsMatcher extends HashedCredentialsMatcher{
	
	@Override  
    public boolean doCredentialsMatch(AuthenticationToken token,  
            AuthenticationInfo info) {  
        Object tokenCredentials = encrypt(token.getCredentials());  
        Object accountCredentials = getCredentials(info);  
        return equals(tokenCredentials, accountCredentials);  
    }  
  
    private Object encrypt(Object credentials) {  
        try {  
        	credentials = string2MD5(credentials);
        	byte[] storedBytes = toBytes(credentials);
            
  
            
            AbstractHash hash = newHashInstance();
            hash.setBytes(storedBytes);
            return hash;
  
        } catch (Exception e) {  
  
            System.err.println("DES算法，加密数据出错!");  
  
            e.printStackTrace();  
  
        }  
        return null;  
    }
    
    public static String string2MD5(Object obj){  
    	String inStr = obj.toString();
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  

}
