package com.xjd.test.valid;

import java.util.regex.Pattern;

/**
 * @author elvis.xu
 * @since 2017-10-12 13:22
 */
public class EmailTest {
	public static void main(String[] args) {
		String mails = "39243918@qq.com;panyf8775@126.com;8308398@qq.com;373410452@qq.com;374816293@qq.com;85957007@qq.com;112405840@qq.com;123965394@qq.com;651945785@qq.com;532109286@qq.com;119346456@qq.com;snark001@163.com;277922921@qq.com;124082481@qq.com;24998617@qq.com;33265956@qq.com;1520818445@qq.com;425201378@qq.com;378849501@qq.com;1614797717@qq.com;304774864@qq.com;1095561198@qq.com;lushuming520@126.com;66628874@qq.com;764605990@qq.com;527950227@qq.com;379607364@qq.com;626388483@QQ.com;www.zhangjianfeng8218@163.com;492898924@qq.com;1052138207@qq.com;2465940143@qq.com;13369961957@qq.com;1360752432@qq.com;xly7688@163.com;252657790@qq.com;119335989@qq.com;139992530@qq.com;377627028@qq.com;281806673@qq.com;443234498@qq.com;1323564966@qq.com;1258155672@qq.com;1534668783@.qq.com;420257208@.qq.com;540723559@qq.com;324600514@qq.com;2315270477@qq.com;2584793953@qq.com;465669350@qq.com;2281128142@qq.com;191632376@qq.com;125459840@qq.com;964154024@qq.com;498073770@qq.com;164064400@qq.com;1525938200@qq.com;75893500@qq.com;94239608@qq.com;742813252@qq.com;56692331@qq.com;wwc0312@126.com;8632790@qq.com;574808681@qq.com;420528467@qq.com;185815640@qq.com;50824405@qq.com;920351823@qq.com;534918351@qq.com;653324457@qq.com;1037277677@qq.com;624370672@qq.com;26147494@qq.com;316327166@qq.com;1187800169@qq.com;42125924@qq.com;13815753@qq.com;csw_577577@163.com;35870156@qq.com;331274845@qq.com;306432818@qq.com;23992683@qq.com;fei405266251@qq.com;30379611@qq.com;543178802.@qq.com;455503759@qq.com;80163638@qq.com;oyjh75922@126.com;1498983610@qq.com;yfstu@126.com;liwen0301@126.com;911607016@qq.com;caokh@qq.com;2521680618@qq.com;43772108@qq.com;1965633749@qq.com;hkspd@163.com;dawei.qin@techcreate.net;89488922@qq.com;609788147@QQ.com;575896855@qq.com;293521870@.qq.com;28026343@qq.com;1523244330@qq.COM;20941071@qq.com;2590809019@qq.com;xlj5812@qq.com;15894650543@163.com;1692831678@qq.com;529669378@qq.com;43949369@qq.com;46524204@QQ.com;646870205@QQ.com;270507249@qq.com;1430402503@qq.com;49018484@qq.com;171613952@qq.com;814101104@qq.com;897622098@qq.com;345016013@qq.com;447276469@qq.com;317705628@qq.com;1261249738@qq.com;250168478@qq.com;764608841@qq.com;shenyuhu@dingtalk.com;309440012@qq.comm;1520929995@qq.com;379823329@qq.com;542474044@qq.com;1175117863@QQ.com;303379460@qq.com;www.1723014149@qq.com;285171040@qq.com;123892938@qq.com;752058890@qq.com;1174006658@qq.com;372485019@qq.com;602144316@qq.com;1499617195@qq.com;124220406@qq.com;442151917@qq.com;792812852@qq.com;25180545@qq.com;1511360867@qq.com;541939964@qq.com;373062203@qq.com;348795502@qq.com;364449607@qq.com;1445206515@qq.com;1518587789@qq.com;470229877@qq.com;328720733@qq.com;2542713244@qq.com;304329116@qq.com;869835881@qq.com;1120402210@qq.com;www.2535501816@qq.com;272681789@qq.com;1838017350@qq.com;313309307@qq.com;yuer517@126.com;QQ11918538@qq.com;921233988@qq.com;a0022@qq.com;QQ1193918538@qq.com;1191800479@qq.com;xjkelwb@sina.com;2921310235@qq.com;2597678758@qq.com;362137593@qq.com;debao1102@sina.com;740861189@qq.com;273001010@qq.com;523157399@qq.com;306460261@qq.com;158414547@qq.com;173072648@qq.com;2283296568@qq.com;1078352662@qq.com;35854881@qq.com;553552887@qq.com;1753032332@qq.com;106627480@qq.com;502165725@qq.com;18909964823@qq.com;1s2u3n@sina.com;angelxf@126.com;365051246@qq.com;1518789336@QQ.com;419313935@qq.com;306696818@qq.com;anni951212@sina.com;978148366@QQ.com;734710582@qq.com;382245539@qq.com;59921982@QQ.com;flank1986@sina.com;1115298291@QQ.com;genghy@PetroChina.com.cn;1461091433@qq.com;617243709@qq.com;87428079@qq.com;1113793394@qq.com;1113792394@qq.com;735895323@qq.com;872490157@qq.com;fc_0308@qq.com;1318315630@qq.com;631312885@qq.com;whyhaven@163.com;hengxue_1982@163.com;1441271291@qq.com;3255877500@qq.com;869237146@qq.com;495848028@qq.com;521203578@qq.com;1367445731@qq.com;416808152@qq.com;401055673@qq.com;1151425835@qq.com;yjdg846@163.com;zhhsph@163.com;430115719@qq.com;649516729@qq.com;1403382845@QQ.com";

		String[] splits = mails.split(";");

		Pattern mailPattern = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");

		for (String split : splits) {
			if (!mailPattern.matcher(split).matches()) {
				System.out.println(split);
			}
		}
	}
}
