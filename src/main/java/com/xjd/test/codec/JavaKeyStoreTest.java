package com.xjd.test.codec;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


/**
 * Java证书，公私钥，加解密，签名验签，证书验证
 * @author elvis.xu
 * @since 2017-08-11 11:30
 */
public class JavaKeyStoreTest {
	public static void main(String[] args) throws Exception {
		/*
		1. 现有java keystore文件"wo.jks"和密码"123456"。该文件可以从其它证书转换而来，如pkcs12，请参照证书转换
		2. 通过命令查看jks中所含有的证书: $JAVA_HOME/bin/keytool -list -v -keystore wo.jks -storepass 123456
		   查看结果有"别名: 1", 得密钥库别名为: 1
		3. 导出该别名的证书(证书包含公钥): $JAVA_HOME/bin/keytool -exportcert -keystore wo.jks -storepass 123456 -alias 1 -file wo.crt
		   得到证书文件"wo.crt"
		通过以上步骤我们得到: keystore文件(wo.jks, 密码123456); 密钥库别名"1"; 密钥库证书文件(wo.crt,包含公钥)
		*/

		String keyStorePath = "/Users/XJD/Downloads/cer2/wo.jks";
		String keyStorePass = "123456";
		String alias = "1";
		String aliasPass = "123456";
		String certificatePath = "/Users/XJD/Downloads/cer2/wo.crt";

		String data = "我是绝密内容，保护我！！！";

		System.out.println("=====公钥加密私钥解密=====");
		{
			System.out.println("原文: [" + data + "]");

			byte[] encryptedData1 = CertUtils.encryptByPublicKey(data.getBytes(Charset.forName("utf8")), CertUtils.getPublicKey(CertUtils.getCertificate(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias)));
			System.out.println("公钥加密-通过keystore-1: [" + Base64.encodeBase64String(encryptedData1) + "]");

			byte[] encryptedData11 = CertUtils.encryptByPublicKey(data.getBytes(Charset.forName("utf8")), CertUtils.getPublicKey(CertUtils.getCertificate(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias)));
			System.out.println("公钥加密-通过keystore-2: [" + Base64.encodeBase64String(encryptedData11) + "]");

			byte[] encryptedData2 = CertUtils.encryptByPublicKey(data.getBytes(Charset.forName("utf8")), CertUtils.getPublicKey(CertUtils.getCertificate(certificatePath)));
			System.out.println("公钥加密-通过certificate-1: [" + Base64.encodeBase64String(encryptedData2) + "]");

			byte[] encryptedData22 = CertUtils.encryptByPublicKey(data.getBytes(Charset.forName("utf8")), CertUtils.getPublicKey(CertUtils.getCertificate(certificatePath)));
			System.out.println("公钥加密-通过certificate-2: [" + Base64.encodeBase64String(encryptedData22) + "]");

			System.out.printf("两次加密相等: %b\r\n", Arrays.equals(encryptedData1, encryptedData2));

			byte[] decryptedData1 = CertUtils.decryptByPrivateKey(encryptedData1, CertUtils.getPrivateKey(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias, aliasPass));
			System.out.println("私钥解密: [" + new String(decryptedData1, Charset.forName("utf8")) + "]");

			byte[] decryptedData2 = CertUtils.decryptByPrivateKey(encryptedData2, CertUtils.getPrivateKey(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias, aliasPass));
			System.out.println("私钥解密: [" + new String(decryptedData2, Charset.forName("utf8")) + "]");

			System.out.printf("两次解密相等: %b\r\n", Arrays.equals(decryptedData1, decryptedData2));

			System.out.println("对同一内容公钥多次加密的结果均不相同(因为加官时有随机向量,也可以自己指定), 解密结果均相同!");
		}

		System.out.println("=====私钥加密(签名)公钥解密(验签)=====");
		{
			System.out.println("原文: [" + data + "]");

			byte[] encryptedData1 = CertUtils.encryptByPrivateKey(data.getBytes(Charset.forName("utf8")), CertUtils.getPrivateKey(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias, aliasPass));
			System.out.println("私钥加密-1: [" + Base64.encodeBase64String(encryptedData1) + "]");

			byte[] encryptedData2 = CertUtils.encryptByPrivateKey(data.getBytes(Charset.forName("utf8")), CertUtils.getPrivateKey(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias, aliasPass));
			System.out.println("私钥加密-2: [" + Base64.encodeBase64String(encryptedData2) + "]");

			System.out.printf("两次加密相等: %b\r\n", Arrays.equals(encryptedData1, encryptedData2));

			byte[] decryptedData1 = CertUtils.decryptByPublicKey(encryptedData1, CertUtils.getPublicKey(CertUtils.getCertificate(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias)));
			System.out.println("公钥解密-1: [" + new String(decryptedData1, Charset.forName("utf8")) + "]");

			byte[] decryptedData2 = CertUtils.decryptByPublicKey(encryptedData1, CertUtils.getPublicKey(CertUtils.getCertificate(certificatePath)));
			System.out.println("公钥解密-2: [" + new String(decryptedData2, Charset.forName("utf8")) + "]");

			System.out.printf("两次解密相等: %b\r\n", Arrays.equals(decryptedData1, decryptedData2));

			System.out.println("对同一内容私钥多次加密的结果均相同, 解密结果均相同!");
		}

		// 证书验证
		System.out.println("=====证书验证=====");
		{
			System.out.println("原文: [" + data + "]");

			byte[] sign1 = CertUtils.sign(data.getBytes(Charset.forName("utf8")), CertUtils.getPrivateKey(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias, aliasPass), CertUtils.getCertificate(certificatePath));
			System.out.printf("签名-1: [%s]\r\n", Base64.encodeBase64String(sign1));

			byte[] sign2 = CertUtils.sign(data.getBytes(Charset.forName("utf8")), CertUtils.getPrivateKey(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias, aliasPass), CertUtils.getCertificate(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias));
			System.out.printf("签名-2: [%s]\r\n", Base64.encodeBase64String(sign2));

			System.out.printf("两次签名相等: %b\r\n", Arrays.equals(sign1, sign2));

			boolean verify1 = CertUtils.verify(data.getBytes(Charset.forName("utf8")), sign1, CertUtils.getCertificate(CertUtils.getKeyStore(keyStorePath, keyStorePass), alias));
			System.out.printf("验签-1: [%b]\r\n", verify1);

			boolean verify2 = CertUtils.verify(data.getBytes(Charset.forName("utf8")), sign1, CertUtils.getCertificate(certificatePath));
			System.out.printf("验签-2: [%b]\r\n", verify2);
		}
	}

	public static class CertUtils {
		private static final String KEY_STORE = "JKS";
		private static final String X509 = "X.509";

		// ===== keystore ===== //
		public static KeyStore getKeyStore(String keyStorePath, String keyStorePass) throws Exception {
			FileInputStream in = new FileInputStream(keyStorePath);
			KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
			keyStore.load(in, keyStorePass.toCharArray());
			in.close();
			return keyStore;
		}

		// ===== certificate ===== //
		public static Certificate getCertificate(KeyStore keyStore, String alias) throws KeyStoreException {
			return keyStore.getCertificate(alias);
		}

		public static Certificate getCertificate(String certificatePath) throws Exception {
			CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
			FileInputStream in = new FileInputStream(certificatePath);
			Certificate certificate = certificateFactory.generateCertificate(in);
			in.close();
			return certificate;
		}

		// ===== key ===== //
		public static PrivateKey getPrivateKey(KeyStore keyStore, String alias, String aliasPass) throws Exception {
			return (PrivateKey) keyStore.getKey(alias, aliasPass.toCharArray());
		}

		public static PublicKey getPublicKey(Certificate certificate) {
			return certificate.getPublicKey();
		}


		// ===== encrypt & decrypt ===== //
		private static byte[] docrypt(byte[] data, Key key, int mode) throws Exception {
			Cipher cipher = Cipher.getInstance(key.getAlgorithm());
			cipher.init(mode, key);
			return cipher.doFinal(data);
		}

		public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
			return docrypt(data, privateKey, Cipher.ENCRYPT_MODE);
		}

		public static byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
			return docrypt(data, privateKey, Cipher.DECRYPT_MODE);
		}

		public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
			return docrypt(data, publicKey, Cipher.ENCRYPT_MODE);
		}

		public static byte[] decryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
			return docrypt(data, publicKey, Cipher.DECRYPT_MODE);
		}

		// ===== verify certificate ===== //
		public static boolean verifyCertificate(Date date, Certificate certificate) {
			try {
				((X509Certificate) certificate).checkValidity(date);
				return true;
			} catch (CertificateExpiredException e) {
			} catch (CertificateNotYetValidException e) {
			}
			return false;
		}

		// ===== sign & verify ===== //
		public static byte[] sign(byte[] data, PrivateKey privateKey, Certificate certificate) throws Exception {
			Signature signature = Signature.getInstance(((X509Certificate) certificate).getSigAlgName());
			signature.initSign(privateKey);
			signature.update(data);
			return signature.sign();
		}

		public static boolean verify(byte[] data, byte[] sign, Certificate certificate) throws Exception {
			Signature signature = Signature.getInstance(((X509Certificate) certificate).getSigAlgName());
			signature.initVerify(certificate.getPublicKey());
			signature.update(data);
			return signature.verify(sign);
		}
	}
}
