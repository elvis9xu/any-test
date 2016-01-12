package com.xjd.test;

public class Test {
	/**
     * ## App ��ҳ�м䲿�ֵ�����. /appindex/body
     * 
     * ����������� userId ȥ������Ӧ��״̬, ��������Ӧ��ҳ�����ݷ��ظ��ͻ���.
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
     * req �� body ���Բ�����
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
     * Page ������һЩͨ�ýṹ����Ҳ��������ҳ�����
     * �ṹ���Բο� {@link kmmedical.domain.page.appindex.Page}
     * 
     * * �û�״̬�� {@link UserState}
     * * Ѫ�ǵĽṹ�� {@link BloodSugarFragment}
     * * ������� {@link kmmedical.domain.page.ServicePack}
     * * ��ת�� {@link kmmedical.domain.page.Redirect}
     * 
     * 
     * **sample**
     * 
     * ���� json ��������
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
     *         "text" : "�賿"
     *       }, {
     *         "weekday" : "TUESDAY",
     *         "monitorPoint" : "PRE_BREAKFAST",
     *         "value" : null,
     *         "text" : "���ǰ"
     *       }, {
     *         "weekday" : "THURSDAY",
     *         "monitorPoint" : "POST_LAUNCH",
     *         "value" : 5.6,
     *         "text" : "��ͺ�"
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
     * @param req ������
     * @return ����һ�� Page �� json ����
     */
	public static void main(String[] args) {
		String a = "hello";
		System.out.println(a);
		System.out.println(a);
	}
}
