package com.yuansb.demo.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 自定义的健康指标
 *
 * 可以通过实现 HealthIndicator 接口来自定义一个健康指标
 * 或者继承 AbstractHealthIndicator 类 （本次用例选择）
 *
 *  {"status":"UP","details":{"custom":{"status":"UP","details":{"app":"Alive and Kicking","error":"Nothing! I'm good."}},"diskSpace":{"status":"UP","details":{"total":167772155904,"free":54026833920,"threshold":10485760}}}}
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // Use the builder to build the health status details that should be reported.
        // If you throw an exception, the status will be DOWN with the exception message.

        builder.up()
                .withDetail("app", "Alive and Kicking")
                .withDetail("error", "Nothing! I'm good.");
    }

}
