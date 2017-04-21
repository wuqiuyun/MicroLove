package com.mmt.microlove.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @Description 加密工具类
 * @Author wuqiuyun
 * @Time 2017/4/20
 */
public class EncryptionUtil {

    private static final String encoding = "UTF-8";
    private static final String KEY = "7;9Ku7;:84VG*B78";
    private static final String IV = "sHjrydLq";//初始化向量参数，AES 为16bytes. DES 为8bytes.
    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";//DES是加密方式 CBC是工作模式 PKCS5Padding是填充模式

    /**
     * @param plaintext 明文
     * @return 密文
     * @Description MD5加密
     */
    public static String md5(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Description SHA-1加密字符串
     */
    public static String sha1(String val) {
        MessageDigest sha1 = null;
        byte[] m = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
            sha1.update(val.getBytes());
            m = sha1.digest();
            return getString(m);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    /**
     * @Description 加密字符串
     */
    public static String desEnStr(String str) {
        String result = str;
        if (str != null && str.length() > 0) {
            try {
                byte[] encodeByte = symmetricEncrypto(str.getBytes(encoding));
                result = new String(Base64.encode(encodeByte));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @Description 解密字符串
     */

    public static String desDeStr(String str) {
        String result = str;
        if (str != null && str.length() > 0) {
            try {
                byte[] encodeByte = Base64.decode(str);

                byte[] decoder = symmetricDecrypto(encodeByte);
                result = new String(decoder, encoding);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @Description 加密byte[]
     */
    public static byte[] desEnByte(byte[] str) {
        byte[] result = null;
        if (str != null && str.length > 0) {
            try {
                byte[] encodeByte = symmetricEncrypto(str);
                result = Base64.encode(encodeByte);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @Description 解密byte[]
     */
    public static byte[] desDeByte(byte[] str) {
        byte[] result = null;
        if (str != null && str.length > 0) {
            try {
                byte[] encodeByte = Base64.decode(new String(str, encoding));
                byte[] decoder = symmetricDecrypto(encodeByte);
                result = new String(decoder).getBytes(encoding);
                result = new String(decoder).getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param byteSource 需要加密的数据
     * @return 经过加密的数据
     * @throws Exception
     * @Description 对称加密字节数组并返回
     */
    public static byte[] symmetricEncrypto(byte[] byteSource) throws Exception {
        try {
            int mode = Cipher.ENCRYPT_MODE;
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            byte[] keyData = KEY.getBytes();
            DESKeySpec keySpec = new DESKeySpec(keyData);
            Key key = keyFactory.generateSecret(keySpec);
            IvParameterSpec spec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode, key, spec);

            byte[] result = cipher.doFinal(byteSource);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    /**
     * @param byteSource 需要解密的数据
     * @return 经过解密的数据
     * @throws Exception
     * @Description 对称解密字节数组并返回
     */
    public static byte[] symmetricDecrypto(byte[] byteSource) throws Exception {
        try {
            int mode = Cipher.DECRYPT_MODE;
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            byte[] keyData = KEY.getBytes();
            DESKeySpec keySpec = new DESKeySpec(keyData);
            Key key = keyFactory.generateSecret(keySpec);
            IvParameterSpec spec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode, key, spec);
            byte[] result = cipher.doFinal(byteSource);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    /**
     * @param byteSource 需要散列计算的数据
     * @return 经过散列计算的数据
     * @throws Exception
     * @Description 散列算法
     */
    public static byte[] hashMethod(byte[] byteSource) throws Exception {
        try {
            MessageDigest currentAlgorithm = MessageDigest.getInstance("SHA-1");
            currentAlgorithm.reset();
            currentAlgorithm.update(byteSource);
            return currentAlgorithm.digest();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param srcFile  明文文件
     * @param distFile 加密后的文件
     * @throws Exception
     * @Description 对文件srcFile进行加密输出到文件distFile
     */
    public static void enFile(String srcFile, String distFile) throws Exception {

        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            int mode = Cipher.ENCRYPT_MODE;
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            byte[] keyData = KEY.getBytes();
            DESKeySpec keySpec = new DESKeySpec(keyData);
            Key key = keyFactory.generateSecret(keySpec);
            IvParameterSpec spec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode, key, spec);
            is = new FileInputStream(srcFile);
            out = new FileOutputStream(distFile);
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cis.close();
            is.close();
            out.close();
        }
    }

    /**
     * @param srcFile  密文文件
     * @param distFile 解密后的文件
     * @throws Exception
     * @Description 解密文件srcFile到目标文件distFile
     */
    public static void deFile(String srcFile, String distFile) throws Exception {

        InputStream is = null;
        OutputStream out = null;
        CipherOutputStream cos = null;
        try {
            int mode = Cipher.DECRYPT_MODE;
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            byte[] keyData = KEY.getBytes();
            DESKeySpec keySpec = new DESKeySpec(keyData);
            Key key = keyFactory.generateSecret(keySpec);
            IvParameterSpec spec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode, key, spec);
            byte[] buffer = new byte[1024];
            is = new FileInputStream(srcFile);
            out = new FileOutputStream(distFile);
            cos = new CipherOutputStream(out, cipher);

            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cos.close();
            is.close();
            out.close();
        }
    }

    /**
     * @param srcFile  源文件
     * @param distFile 目标文件
     * @Description 对文件进行加密64位编码
     */
    public static void base64EnFile(String srcFile, String distFile) {
        InputStream inputStream = null;
        OutputStream out = null;
        try {
            inputStream = new FileInputStream(srcFile);

            out = new FileOutputStream(distFile);
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) > 0) {
                out.write(desEnByte(buffer));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                out.close();
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @param srcFile  源文件
     * @param distFile 目标文件
     * @Description 对文件进行解密64位解码
     */
    public static void base64DeFile(String srcFile, String distFile) {
        InputStream inputStream = null;
        OutputStream out = null;
        try {
            inputStream = new FileInputStream(srcFile);

            out = new FileOutputStream(distFile);
            byte[] buffer = new byte[1412];

            while (inputStream.read(buffer) > 0) {
                out.write(desDeByte(buffer));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                out.close();
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
