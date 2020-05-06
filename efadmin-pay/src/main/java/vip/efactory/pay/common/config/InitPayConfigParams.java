package vip.efactory.pay.common.config;

import com.alipay.demo.trade.config.Configs;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vip.efactory.pay.union.util.SDKConfig;
import vip.efactory.pay.wx.util.ConfigUtil;

/**
 * 启动加载支付宝、微信以及银联相关参数配置
 */
@Component
public class InitPayConfigParams implements ApplicationRunner {

    @Override 
    public void run(ApplicationArguments var) {
        //初始化 支付宝-微信-银联相关参数,涉及机密,此文件不会提交,请自行配置相关参数并加载
        Configs.init("zfbinfo.properties");     //支付宝
        ConfigUtil.init("wxinfo.properties");   //微信
        SDKConfig.getConfig().loadPropertiesFromSrc();    //银联
    }
}
