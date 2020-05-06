package vip.efactory.pay.common.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付途径平台
 */
public enum PayWay {
    PC("PC,平板", (short) 1), MOBILE("手机", (short) 2);

    @Getter
    @Setter
    private Short code;
    @Getter
    @Setter
    private String name;

    PayWay(String name, Short code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(Short code, String name) {
        for (PayWay c : PayWay.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}
