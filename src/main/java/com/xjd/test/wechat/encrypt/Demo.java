package com.xjd.test.wechat.encrypt;

/**
 * @author elvis.xu
 * @since 2018-04-03 11:29
 */
public class Demo {
	static String token = "qwertyuiopasdfghjklzxcvbnm123456";
	static String aesKey = "XXXXXXXXXXXXXXXXYYYYYZZZZZXXXXXYYYYYZZZZZAB";
	static String appId = "wxd304baf747638c40";
	static WechatCrypt pc;

	static {
			pc = new WechatCrypt(appId, token, aesKey);
	}


	public static void main(String[] args) {

		String emsg = "cXJdVfLt1r5s2iNgxkZ4cpeH7xzYmHLiDPXUc3mGr26RnftdrJ0lMzWks25Td43tPKFOl" +
				"+jUSRduCZPVrGYU0Dl8z3RrTlWkai60dFPbAdlZVTHhrXzztP8O1wB9UINx+33bo5InD9fb9w9s7xFX8EVTXWJ21a5" +
				"+sx4pbK40L0bNQqkNDRq1Nn3/OH" +
				"/l3jjaIb3yIIoz3SfAQXjxwmGopy07xEBgmHNC8dmqOlbxoDElqLejkh042z0ikn8tyNSNCixMkbgXo9GzXK4W0U7h6w6JRb4DbjV90f860ZMo2NZ+xO3msu8CJnxioN2bKWAJ8GWtFGCsLc2M0Tz4BVKiAnioMnAc6fCbgGojlG5m7LaaTIWR8SYJbqhv94qOpEelhoSAZe8+PtOh7MpqCxOqhElDdjBsJEdgiTSZdskO3drbO1/Nj3WDJZGqB30wcWlwxIHEfOi4YV4dY2Wr46eAgQ==";
		String timestamp = "1522724657";
		String nonce = "1533761462";
		String signature = "3288204b337b18630a70d45fca832ba084be3c88";
		decrypt(emsg, timestamp, nonce, signature);

	}


	public static void decrypt(String source, String timestamp, String nonce, String signature) {
		try {
			String rt = pc.decrypt(source);
			System.out.println("=====解密后=====");
			System.out.println(rt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
