package vip.efactory.pay.enums;

import lombok.Setter;

/**
 * 阿里支持的支付方式枚举
 */
public enum AliPayTypeEnum {
    QR(1, "A端显示二维码B端扫码支付"),
    APP(2, "调用本机上的支付宝App支付"),
    ;
    @Setter
    private Integer order; // 支付方式序号
    @Setter
    private String desc; // 支付方式描述

    AliPayTypeEnum(Integer order, String desc) {
        this.order = order;
        this.desc = desc;
    }
}
