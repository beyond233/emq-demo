package com.beyond.emq.mqtt;

import com.beyond.emq.config.MqttProperty;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description: emq客户端（对原有mqttClient进行包装简化，方便开发）
 *
 * @author beyond233
 * @since 2021/3/7 21:29
 */
@Slf4j
@Component
public class EmqClient {

    private IMqttClient mqttClient;

    @Autowired
    private MqttProperty mqttProperty;

    @Autowired
    private MqttCallback mqttCallback;

    /**
     * 初始化mqtt客户端
     *
     * @since 2021/3/7 21:57
     */
    @PostConstruct
    public void init() {
        MqttClientPersistence persistence = new MemoryPersistence();
        try {
            mqttClient = new MqttClient(mqttProperty.getServerAddr(), mqttProperty.getClientId(), null);
        } catch (MqttException e) {
            log.error("【mqtt】init mqtt client failed! serverAddr={},clientId={}", mqttProperty.getServerAddr(), mqttProperty.getClientId());
            e.printStackTrace();
        }
    }

    /**
     * 连接emq server
     *
     * @param username 用户名
     * @param password 密码
     * @since 2021/3/7 21:57
     */
    public void connect(String username, String password) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(mqttProperty.isCleanSession());
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(mqttProperty.getTimeout());

        mqttClient.setCallback(mqttCallback);
        try {
            mqttClient.connect(options);
        } catch (MqttException e) {
            log.error("【mqtt】connect emq server failed!");
            e.printStackTrace();
        }
    }

    /**
     * 断开连接
     *
     * @since 2021/3/7 21:59
     */
    public void disConnect() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            log.error("disconnect emq server failed!");
            e.printStackTrace();
        }
    }

    /**
     * 重新连接
     *
     * @since 2021/3/7 22:00
     */
    public void reconnect() {
        try {
            mqttClient.reconnect();
        } catch (MqttException e) {
            log.info("reconnect emq server failed!");
            e.printStackTrace();
        }
    }

    /**
     * 发布消息
     *
     * @param topic  消息主题
     * @param msg    消息内容
     * @param qos    消息发布质量
     * @param retain 是否保存
     * @since 2021/3/7 22:18
     */
    public void publish(String topic, String msg, MqttQos qos, boolean retain) {
        MqttMessage mqttMsg = new MqttMessage();
        mqttMsg.setPayload(msg.getBytes());
        mqttMsg.setQos(qos.getValue());
        mqttMsg.setRetained(retain);
        try {
            mqttClient.publish(topic, mqttMsg);
        } catch (MqttException e) {
            log.error("mqtt发布消息失败! topic={},msg={},qos={},retain={}", topic, msg, qos.getValue(), retain);
            e.printStackTrace();
        }
    }

    /**
     * 订阅消息
     *
     * @param topicFilter 主题过滤器
     * @param qos         消息发布质量
     * @since 2021/3/7 22:24
     */
    public void subscribe(String topicFilter, MqttQos qos) {
        try {
            mqttClient.subscribe(topicFilter, qos.getValue());
        } catch (MqttException e) {
            log.error("mqtt消息订阅失败！ topic={},qos={}", topicFilter, qos.getValue());
            e.printStackTrace();
        }
    }

    /**
     * 取消订阅
     *
     * @param topicFilter 主题过滤器
     * @since 2021/3/7 22:28
     */
    public void unSubscribe(String topicFilter) {
        try {
            mqttClient.unsubscribe(topicFilter);
        } catch (MqttException e) {
            log.error("mqtt消息取消订阅失败! topic={}", topicFilter);
            e.printStackTrace();
        }
    }


}
