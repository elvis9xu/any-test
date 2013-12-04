package com.xjd.test.crypt;

import static junit.framework.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.util.StreamUtils;

import com.xjd.util.crypt.CoderUtil;
import com.xjd.util.crypt.CryptUtil;


public class Base64Test {

	/**
	 * <pre>
	 * Base64 encode之后的byte[]通过new String(byte[], String charset)生成字符串即可,一般使用UTF-8
	 * Base64 encode之后的byte[]数组可以在指定长度处(一行为76字符)包括换行符(\r\n), 在decode时将被忽略.
	 * </pre>
	 * @param args
	 * @throws IOException
	 * @author elvis.xu
	 * @since 2013-12-4
	 */
	public static void main(String[] args) throws IOException {
		byte[] orgData = readFrom("D:/test/org.jpg");
		
		byte[] edata1 = CryptUtil.encode(CoderUtil.BASE64, orgData);
		String edataStr1 = new String(edata1);
		sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		String edataStr2 = enc.encode(orgData);
		System.out.println(edataStr1);
		System.out.println(edataStr2);
		System.out.println(StringUtils.equals(edataStr1, edataStr2));
		
//		String pngBase64Data = "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";
//		String pngBase64Data = "/9j/4AAQSkZJRgABAQAAAQABAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZyBJSkcgSlBFRyB2NjIpLCBxdWFsaXR5ID0gOTAK/9sAQwADAgIDAgIDAwMDBAMDBAUIBQUEBAUKBwcGCAwKDAwLCgsLDQ4SEA0OEQ4LCxAWEBETFBUVFQwPFxgWFBgSFBUU/9sAQwEDBAQFBAUJBQUJFA0LDRQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQU/8AAEQgAKAAoAwEiAAIRAQMRAf/EAB8AAAEFAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQAAIBAwMCBAMFBQQEAAABfQECAwAEEQUSITFBBhNRYQcicRQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYnKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6g4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh4uPk5ebn6Onq8fLz9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYHCAkKC//EALURAAIBAgQEAwQHBQQEAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRCkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm5+jp6vLz9PX29/j5+v/aAAwDAQACEQMRAD8A+xP2lPG39lfs169creywXM/7iNkcQsfm5GSVJGM8DJOehr80PD3xM8ReFL9X8MXdxZaxMCrXEaqZY/mydrEEgnHLDBwSOhNfRH7Znj5Nc8N+FvDVtcNJcx3E9zNC4YMA2FQ4xjHyv714b8P9AuNIu7++ngSBcRKhmViMFdzdASO1cU1GVa76HpUlP2XKtnv/AF8inBZ/EKy1N9fW81Aag7+e98t2xmL/AN4vnJPvX3T+xX+1ZrfxA1g+B/GUpudUWEmxvpBiSUoMsknq20Fgepwc9q8Vi1ezh0eGYskbNhSk7BEB75Yj+lbnw48JN4Y/aL+G+uWFsHt9UBeb7O/mKrhWVzkDgYZT+Nae3Ubupou/oFbCRhHmpts/RcjcMHBFFUxfOelvIQe4x/jRXGszwr2b/wDAZf5HBySPyD8SeG9f8a/GUafN5kF1NeLb28F5uRdpPy8nK4Yfz5r6Q+MPwpj+D3iGxKeWLDUbVWjiRDiJ440R15Jzzg8ete0Xnwx0b4h61Dr0Vle2StPEYp08tWhVQB6MevOM9Sc4xWb+2B4r0fVPAmm6aLizl8QreJLDFE4lkjj2HeSR90E4XB64z2rp9k03UluehDENOMILTqfNPg/WrO6vI7d/Jl+diOckknsP8PSvsj4QfD53l8N63JBEILSG4APRt0m0Yx6fL19q+OfCKvDNDcy2Eaxkht6y/fwemMZr9C/AOrQy+CNFkBjWV7WNvKj4AJHTHbvWF4c157LU7MTVkqXuddDqBbx/3F/KiorW6Eww3DelFdFF0K0FOmlY8J3W5+eOtfG3xFq+mPpsV6+n6c2Q9vaMV3Z/vHOTn8vauWi02bUfLuEieQoeWQjA/A0UV+h5/gMPSwinTjZxfTzPh+GcwxFXHOnUldSWt/LaxqW1k9oDsbLcsyYxjnnPpXfeBfiHqfhm8jlN5KjFGTZnKIArbQQeDySfyooryuGsBh8VKpUrRu0ra7a7nrcYY6vhadKjRlZSd3302PavAv7RNtqsqWWuRCymyFF3F909sle31H5UUUV2Y3hTLJVeeEXG/ROy+4+Tw3EePpw5W1Lza1P/2Q==";
		String pngBase64Data = "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";
		
//		String pngBase64Data = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAA=ABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAABIAAAASABGyWs+AAACeklEQVQ4y5WSP0xTURjF=f6+v9h8UCwgYGkkwhmCtg0KUReKqDkYgnR2MDGiwq4loMNHJMGgHEhbRxCisSIxxkAGNppCIU=VNijJHyR6AF2r6+vvv+ODx5ReKgZ7k3+e53zvm+eyR24MXX+xa7IHSNkiiiaipCL4FlcvHkPW=m77lzG525b7a0xZJcX2bUH0xQIs4TQVYShoOkKQiiU9AIra/P0HL0hAbi3CfLKBqZl4rJMlq7=c4W+QgQDQDCT7+qy2REJyCCwkDEOQjT8gOjiI7PcDoCsKlhCYuo6paej5PJZhoC4vkwRLAojE=hi3+AUPnfBw/eBCRy+EOBPgxNoY7Ehu2rvd2c6DeVkylDQqqgaKaFNUi67kim4UKNpUF4hOzv=L4KLo8HbWsLZwc1VR46owEAOqPw7kuOJ1MWy2sgdJOSsYBsVQEwl83SArgD9nsXQEGVSa8qjt=UTrUGGLlfTcaQCAE0NUTRsRW9tLf6GBrRMpuwgva5x87EG5OmI+OnqcFOz10//+RAYBZ4nF0E=POQJVkQhbqVTZwb6gwUi8npF4PS1hmbtPs0x9tB31d4VprA6imBsOgbuyEqNQKBMUhccpdkYD=XLsQYvRlnsxmEYDY6TBF9c+PqmhuLhPMzq/QM5hi4GGagmoQrgvQfcrPo1f2nGfag2iGBoBpG=HZufp8ugMaaSsYHWqgNwq3RFcfJ+JtFR/FwowxANplkaXKStenpMsFiJg/ApbP7eTufti367I=bUggqAIuzQbszMsDQxgchmaUskJCkSG7Zqmo5xqCFIwOfmw/cs0SYPasnL+9Qq4TofHjd8/pb=GW/rJp2e90s5d/FeUdzcD/ALveheyqUSIiwAAAABJRU5ErkJggg=3D=3D";
//		String pngBase64Data = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAA=ACXBIWXMAAAsTAAALEwEAmpwYAAAAN0lEQVQoFWP8//8/AzZgXHoRmzADE1ZRPIKjGvAEDlyK=Ec5CY1y+dAlNBMIdDVaswYImSHIoAQA2KAcMeIF1hwAAAABJRU5ErkJggg=3D=3D";
		
		
		
		byte[] originalData = pngBase64Data.getBytes(Charset.forName("UTF-8"));
		writeTo(originalData, "D:/test/org.png");
		
		byte[] decodedData = CryptUtil.decode(CoderUtil.BASE64, originalData);
		writeTo(decodedData, "D:/test/dec.png");
		
		FileOutputStream fout = new FileOutputStream("D:/test/dec2.png");
		new Base64Encoder().decode(pngBase64Data, fout);
		fout.close();
		
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		byte[] ddata = dec.decodeBuffer(pngBase64Data);
//		for (int i = 0; i < ddata.length; ++i) {
//			if (ddata[i] < 0) {//调整异常数据
//				ddata[i] += 256;
//			}
//		}
		writeTo(ddata, "D:/test/dec3.png");
	}

	public static void writeTo(byte[] data, String file) throws IOException {
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
	}
	
	public static byte[] readFrom(String file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream(in.available());
		StreamUtils.copy(in, out);
		return out.toByteArray();
	}
}
