package com.xjd.test;

public class Test {
	/**
     * ## App 首页中间部分的数据. /appindex/body
     * 
     * 服务器会根据 userId 去查找相应的状态, 并生成相应的页面内容返回给客户端.
     * 
     * **NotImplemented**
     * 
     * **endpoint**
     * 
     * `/appindex/body`
     * 
     * ### Input
     * 
     * `Request<EmtpyBody>`
     * 
     * req 的 body 可以不存在
     * 
     * **sample**
     * 
     * ```json
     * {"header": {"token":"asdf"} }
     * ```
     * 
     * ### Output
     * 
     * `Response<Page>`
     * 
     * Page 里面有一些通用结构可能也会在其他页面出现
     * 结构可以参考 {@link kmmedical.domain.page.appindex.Page}
     * 
     * * 用户状态见 {@link UserState}
     * * 血糖的结构见 {@link BloodSugarFragment}
     * * 服务包见 {@link kmmedical.domain.page.ServicePack}
     * * 跳转见 {@link kmmedical.domain.page.Redirect}
     * 
     * 
     * **sample**
     * 
     * 返回 json 例子如下
     * 
     * ```javascript
     * {
     *   "header" : {
     *     "code" : 2000,
     *     "message" : "OK"
     *   },
     *   "body" : {
     *     "signinToday" : true,
     *     "state" : "HIGH_RISK",
     *     "header" : {
     *       "iconURL" : "http://dummpy.com/dummpy.png",
     *       "text" : "header 1",
     *       "redirect" : {
     *         "type" : "NATIVE",
     *         "url" : "page-diagnostic-procedure-person"
     *       }
     *     },
     *     "footer" : {
     *       "text" : "footer 1",
     *       "avatarURLs" : [ "http://dummpy.com/dummpy.png" ],
     *       "redirect" : {
     *         "type" : "NATIVE",
     *         "url" : "page-medical-active-user"
     *       }
     *     },
     *     "bloodSugarInfo" : {
     *       "start" : 1,
     *       "bloodSugars" : [ {
     *         "weekday" : "MONDAY",
     *         "monitorPoint" : "MIDNIGHT",
     *         "value" : 5.8,
     *         "text" : "凌晨"
     *       }, {
     *         "weekday" : "TUESDAY",
     *         "monitorPoint" : "PRE_BREAKFAST",
     *         "value" : null,
     *         "text" : "早餐前"
     *       }, {
     *         "weekday" : "THURSDAY",
     *         "monitorPoint" : "POST_LAUNCH",
     *         "value" : 5.6,
     *         "text" : "午餐后"
     *       } ]
     *     },
     *     "servicePacks" : [ {
     *       "title" : "title1",
     *       "subTitle" : "subtitle1",
     *       "iconURL" : "http://dummy.com/icon.png",
     *       "redirect" : {
     *         "type" : "WEB_PAGE",
     *         "url" : "http://dummy.com"
     *       }
     *     } ]
     *   }
     * }
     * ```
     *
     * @param req 空请求
     * @return 返回一个 Page 的 json 对象
     */
	public static void main(String[] args) {
		String a = "hello";
		System.out.println(a);
		System.out.println(a);
	}
}
