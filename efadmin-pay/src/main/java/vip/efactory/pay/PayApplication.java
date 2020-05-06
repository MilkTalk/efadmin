package vip.efactory.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
        log.info("支付模块启动成功");
    }

}
