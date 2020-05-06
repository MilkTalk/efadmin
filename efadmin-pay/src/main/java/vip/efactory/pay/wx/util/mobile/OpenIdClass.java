package vip.efactory.pay.wx.util.mobile;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信用户信息
 */
@Getter
@Setter
public class OpenIdClass {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    private String errcode;
    private String errmsg;
}
