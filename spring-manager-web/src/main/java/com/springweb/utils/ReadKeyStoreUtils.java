package com.springweb.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 从证书获取 公钥和私钥
 * @author admin
 * @date 2022/1/7
 */
public class ReadKeyStoreUtils {

	/**
	 * Java密钥库(Java Key Store，JKS)KEY_STORE
	 */
	public static final String KEY_STORE = "JKS";

	public static final String X509 = "X.509";

	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {

		return(new BASE64Decoder()).decodeBuffer(key);

	}

	/**
	 * BASE64加密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {

		return(new BASE64Encoder()).encodeBuffer(key).replace("\r","").replace("\n","");

	}

	/**
	 * 获得KeyStore
	 *
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password)

			throws Exception {

		FileInputStream is = new FileInputStream(keyStorePath);

		KeyStore ks = KeyStore.getInstance(KEY_STORE);

		ks.load(is, password.toCharArray());

		is.close();

		return ks;

	}

	/**
	 * 由KeyStore获得私钥
	 *
	 * @param keyStorePath
	 * @param alias
	 * @param storePass
	 * @return
	 */
	private static PrivateKey getPrivateKey(String keyStorePath, String alias, String storePass, String keyPass) throws Exception {

		KeyStore ks = getKeyStore(keyStorePath, storePass);

		PrivateKey key = (PrivateKey) ks.getKey(alias, keyPass.toCharArray());

		return key;

	}

	/**
	 * 由Certificate获得公钥
	 *
	 * @param keyStorePath KeyStore路径
	 * @param alias        别名
	 * @param storePass    KeyStore访问密码
	 */
	private static PublicKey getPublicKey(String keyStorePath, String alias, String storePass) throws Exception {

		KeyStore ks = getKeyStore(keyStorePath, storePass);

		PublicKey key = ks.getCertificate(alias).getPublicKey();

		return key;

	}

	/**
	 * 从KeyStore中获取公钥，并经BASE64编码
	 *
	 * @param keyStorePath
	 * @param alias
	 * @param storePass
	 */
	public static String getStrPublicKey(String keyStorePath, String alias, String storePass) throws Exception {

		PublicKey key = getPublicKey(keyStorePath, alias, storePass);

		String strKey = encryptBASE64(key.getEncoded());

		return strKey;

	}

	/**
	 * 获取经BASE64编码后的私钥
	 *
	 * @param keyStorePath
	 * @param alias
	 * @param storePass
	 * @param keyPass
	 * @return
	 * @throws Exception
	 * @author 奔跑的蜗牛
	 */

	public static String getStrPrivateKey(String keyStorePath, String alias, String storePass, String keyPass) throws Exception {

		PrivateKey key = getPrivateKey(keyStorePath, alias, storePass, keyPass);

		String strKey = encryptBASE64(key.getEncoded());

		return strKey;

	}



	public static void main(String[] args) throws Exception {

		String keyStorePath = "D:\\sumscope\\MyCode\\cer\\fd-alias.keystore";

		String strPublicKey = getStrPublicKey(keyStorePath, "fd-alias", "123456");
		System.out.println("公钥："+strPublicKey);

		String strPrivateKey = getStrPrivateKey(keyStorePath, "fd-alias", "123456", "123456");
		System.out.println("私钥："+strPrivateKey);

	}

}