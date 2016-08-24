package com.rp;
import java.security.NoSuchAlgorithmException;

import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class AESEncryption {
    public static void main(String[] args)
    {
        try
        { 
            // Provider 추가
            Security.addProvider(new BouncyCastleProvider());
             
            // Random 키 생성
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
             
            // Print key string
           // String encodedKey = new BASE64Encoder().encode(secretKey.getEncoded()).substring(0, 16);  //16자리만 사용
            String encodedKey = "RockpalceMidware";
             
            System.out.println("secretKey:" + encodedKey);
             
            String originalText = "rplinux ";
              
            String encryptedStr = Encrypt( originalText, encodedKey);
            String decryptedStr = Decrypt( encryptedStr, encodedKey);
             
            System.out.println( "Original Text is " + originalText);
            System.out.println( "Encrypted Text is " + encryptedStr );
            System.out.println( "Decrypted Text is " + decryptedStr );
        }
        catch (Exception ex)
        {
               ex.printStackTrace();
        }
              
    }
   
    public static String Decrypt(String text, String encodedKey) throws Exception
    {
        String DecString = "";
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
             
            // key 가 16자리 이상일 경우 16자리만 복사함.
            /*
            byte[] keyBytes= new byte[16];
            byte[] b= key.getBytes("UTF-8");
            int len= b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            */
            SecretKeySpec keySpec = new SecretKeySpec(encodedKey.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(encodedKey.getBytes());
            cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
            BASE64Decoder decoder = new BASE64Decoder();
            byte [] results = cipher.doFinal(decoder.decodeBuffer(text));
            return new String(results,"UTF-8");
        } catch (NoSuchAlgorithmException noSuchAlgo)   {
            System.out.println(" No Such Algorithm exists " + noSuchAlgo);
        }
        return DecString;
    }
    public static String Encrypt(String text, String encodedKey) throws Exception
    {
        String EncString = "";
        try {
             
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");    // PKCS7PADDING은 BouncyCastleProvider를 추가해야 작동함.
          /*
          byte[] keyBytes= new byte[16];
          byte[] b= key.getBytes("UTF-8");
          int len= b.length;
          if (len > keyBytes.length) len = keyBytes.length;
          System.arraycopy(b, 0, keyBytes, 0, len);
            */
          SecretKeySpec keySpec = new SecretKeySpec(encodedKey.getBytes(), "AES");
          IvParameterSpec ivSpec = new IvParameterSpec(encodedKey.getBytes());
         
          cipher.init(Cipher.ENCRYPT_MODE,keySpec, ivSpec);
           
          byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
          BASE64Encoder encoder = new BASE64Encoder();
          EncString = encoder.encode(results);
               
        } catch (NoSuchAlgorithmException noSuchAlgo)   {
            System.out.println(" No Such Algorithm exists " + noSuchAlgo);
        }
         
        return EncString;
    }
     
}