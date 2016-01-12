{
    "ifSuc": 1,
    "code": "200",
    "msg": "查询成功",
    /*数据总条数*/"totalCount": 110,
    /*总页数*/"totalPageNum": 3,
    /*当前页*/"pageNum": 1,
    /*每页条数*/"pageSize": 50,
    "data": {
        "orderInfo": [
            {
                /*订单号*/"orderNumber": 1251810207,
                /*订单总价格 */"totalAmount": 3760,
                /*购买数量 */"buyAmount": 2,
                /*付款时间 */"payTime": "2015-05-15 14:20:30",
                /*支付类型 1：在线支付2：货到付款*/"payType": "货到付款",
                /*创建时间 */"gmtCreated": "2015-05-15 14:20:30",
                /*收获者信息 */"buyerResult": {
                    /*收获者地址 */"address": "上海省 上海市 长宁区 娄山关路",
                    /*收获地址省份 */"province": "上海",
                    /*收获地址市 */"city": "上海",
                    /*收获地址区 */"country": "长宁区",
                    /*收获者移动电话*/"mobilePhone": "18612345678",
                    /*收获者姓名 */"username": "陈xx",
                    /*快递费用 */"expressPrice": 8,
                    /*优惠金额 */"discountAmount": 8
                },
                "orderDetail": [
                    /*药品清单*/{
                        /*每种药购买数量*/"buyAmount": 2,
                        /*制药公司*/"factoryName": "中美天津史克制药有限公司",
                        /*审批号*/"licence": "国药准字H10900089",
                        /*药品名称*/"name": "布洛芬缓释胶囊",
                        /*药品价格(单位分)*/"price": 1480,
                        /*好药师药品id，主商品编码*/"productCode": "12062101",
                        /*每种药规格*/"standard": "300mg×20粒/盒，铝塑泡罩"
                    }
                ],
                /*是否需要发票，0：无需发票，1：需要发票*/"invoiceInfos": 1,
                /*发票信息*/"InvoiceDO": {
                    "invoiceSignal": "开票人标识:公司-个人",
                    "invoiceTypeId": "发票类型",
                    "invoiceContentId": "发票内容",
                    "invoiceTitle": "开票人标识:公司-个人"
                }
            }
        ]
    }
}