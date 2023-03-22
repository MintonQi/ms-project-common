package com.minton.common.util;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAUtil {

    /**
     * 类型
     */
    public static final String ENCRYPT_TYPE = "RSA";

    /**
     * 从文件中读取公钥
     * @param filename 公钥保存路径
     * @return 公钥字符串
     * @throws Exception
     */
    public static String getPublicKey(String filename) throws Exception {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader(filename);
        String result = fileReader.readString();
        return result;
    }

    /**
     * 从文件中读取密钥
     * @param filename 私钥保存路径
     * @return 私钥字符串
     * @throws Exception
     */
    public static String getPrivateKey(String filename) throws Exception {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader(filename);
        String result = fileReader.readString();
        return result;
    }

    /**
     * 公钥加密
     * @param content   要加密的内容
     * @param publicKey 公钥
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密
     * @param content   要加密的内容
     * @param publicKey 公钥
     */
    public static String encrypt(String content, String publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 私钥解密
     * @param content    要解密的内容
     * @param privateKey 私钥
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     * @param content    要解密的内容
     * @param privateKey 私钥
     */
    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取公私钥-请获取一次后保存公私钥使用
     * @param publicKeyFilename 公钥生成的路径
     * @param privateKeyFilename 私钥生成的路径
     */
    public static void generateKeyPair(String publicKeyFilename, String privateKeyFilename) {
        try {
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();

            // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
            String pubEncBase64 = Base64.getEncoder().encodeToString(pubEncBytes);
            String priEncBase64 = Base64.getEncoder().encodeToString(priEncBytes);

            FileWriter pub = new FileWriter(publicKeyFilename);
            FileWriter pri = new FileWriter(privateKeyFilename);
            pub.write(pubEncBase64);
            pri.write(priEncBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

