package com.beyond.emq.mqtt;

import com.beyond.emq.config.MqttProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * mqtt消息监听
 *
 * @author beyond233
 * @date 2021/3/8 17:08
 */
@Slf4j
@Component
public class MqttMsgListen {

    @Autowired
    private MqttProperty mqttProperty;

    @Autowired
    private EmqClient emqClient;

    @EventListener({ContextRefreshedEvent.class})
    public void initListen() {
        emqClient.connect(mqttProperty.getUsername(), mqttProperty.getPassword());
        emqClient.subscribe("testtopic/#", MqttQos.ONLY_ONCE);
        emqClient.publish("testtopic/demo", "hello mqtt!", MqttQos.ONLY_ONCE, true);
    }
}
