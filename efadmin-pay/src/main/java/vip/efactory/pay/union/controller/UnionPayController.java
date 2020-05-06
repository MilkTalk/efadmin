package vip.efactory.pay.union.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vip.efactory.pay.common.constants.PayWay;
import vip.efactory.pay.common.model.Product;
import vip.efactory.pay.union.service.IUnionPayService;
import vip.efactory.pay.union.util.AcpService;
import vip.efactory.pay.union.util.SDKConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 银联支付
 */
@Api(tags = "银联支付")
@Controller
@RequestMapping(value = "unionpay")
@Slf4j
@AllArgsConstructor
public class UnionPayController {
    private IUnionPayService unionPayService;

    @ApiOperation(value = "电脑支付")
    @RequestMapping(value = "pcPay", method = RequestMethod.POST)
    public String pcPay(Product product, ModelMap map) {
        log.info("电脑支付");
        product.setPayWay(PayWay.PC.getCode());
        String form = unionPayService.unionPay(product);
        map.addAttribute("form", form);
        return "unionpay/pay";
    }

    @ApiOperation(value = "手机H5支付")
    @RequestMapping(value = "mobilePay", method = RequestMethod.POST)
    public String mobilePay(Product product, ModelMap map) {
        log.info("手机H5支付");
        product.setPayWay(PayWay.MOBILE.getCode());
        String form = unionPayService.unionPay(product);
        map.addAttribute("form", form);
        return "unionpay/pay";
    }

    /**
     * 然后这个方法的基本作用就是 银联支付回调 通知我们支付是否成功。
     */
    @ApiOperation(value = "银联回调通知")
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public void union_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("银联接收后台通知开始");
        String encoding = request.getParameter(SDKConstants.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(request);
        //打印参数
        log.info(reqParam.toString());
        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                value = new String(value.getBytes(encoding), encoding);
                valideData.put(key, value);
            }
        }
        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            log.info("银联验证签名结果[失败].");
        } else {
            log.info("银联验证签名结果[成功].");
            String outtradeno = valideData.get("orderId");//订单号
            String reqReserved = valideData.get("reqReserved");//辅助信息(字段穿透)
            log.info("处理相关业务逻辑{},{}", outtradeno, reqReserved);
            response.getWriter().print("ok");//返回给银联服务器http 200  状态码
        }
    }

    /**
     * 获取请求参数中所有的信息
     *
     * @param request
     * @return Map<String, String>
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
