package vip.efactory.pay.common.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付类型
 */
public enum PayType {
    /**
     * 支付类型
     */
    ALI("支付宝", (short) 1), WECHAT("微信", (short) 2), UNION("银联", (short) 3);

    @Setter
    @Getter
    private Short code;
    @Setter
    @Getter
    private String name;

    PayType(String name, Short code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(Short code, String name) {
        for (PayType c : PayType.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
}
