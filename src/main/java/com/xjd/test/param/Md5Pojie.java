package com.xjd.test.param;

public class Md5Pojie {

	public static void main(String[] args) {
		String paramString = "app=a-ajk&cid=11&commid=3345&cv=8.5&from=mobile&i=865199023674173&m=Android-HM+NOTE+1LTEW&macid=8345b17a70e11dd189d0587ce07dcbb8&o=dior-user+4.4.2+KVT49L+KHICNBH21.0+release-keys&pm=b95&qtime=20150402110802&rsl=65535&uuid=48d88f01-a33d-4fed-858f-932015040210&v=4.4.2";

		String prefix = "/4.0/community/info";
		String is = "?";
		String suffix = "5d41a9e970273bca";

		String s = prefix  + paramString + suffix ;

		String os = "0f534bfc13792f7a3fba3bde77edb411";

		for (int i = 0; i <= (prefix + is).length(); i++) {
			String t = s.substring(i);
			String m = MD5Utils.md5(t);
			System.out.println(t);
			System.out.println(m);
			if (os.equals(m)) {
				System.out.println("======OK========");
				break;
			}
		}
		System.out.println(Md5Util.Md5(paramString));
		System.out.println(MD5Utils.md5(paramString));
	}

}
