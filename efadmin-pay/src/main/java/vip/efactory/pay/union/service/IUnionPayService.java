package vip.efactory.pay.union.service;

import vip.efactory.pay.common.model.Product;

import java.util.Map;

public interface IUnionPayService {
    /**
     * 银联支付
     *
     * @param product
     * @return String
     */
    String unionPay(Product product);

    /**
     * 前台回调验证
     *
     * @param valideData
     * @param encoding
     * @return String
     */
    String validate(Map<String, String> valideData, String encoding);

    /**
     * 对账单下载
     */
    void fileTransfer();
}
