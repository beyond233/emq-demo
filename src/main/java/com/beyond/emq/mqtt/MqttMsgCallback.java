package com.beyond.emq.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * description: mqtt消息回调
 *
 * @author beyond233
 * @since 2021/3/7 22:29
 */
@Slf4j
@Component
public class MqttMsgCallback implements MqttCallback {

    /**
     * 连接丢失
     * @since 2021/3/7 22:29
     */
    @Override
    public void connectionLost(Throwable throwable) {
        // 资源清理、重连
        log.info("【mqtt】丢失了对服务端的连接");
    }

    /**
     * 接收到订阅消息后的回调处理
     * 注：该方法由mqttClient客户端同步调用，在此方法未正确返回前不会发送ACK确认消息到emq broker,一旦该方法向外抛出异
     * 常将导致客户端异常关闭，当再次连接时，所有Qos1,Qos2且客户端未进行ACK确认的消息都将由emq broker再次发送到客户端。
     *
     * @param s           主题
     * @param mqttMessage 消息
     * @since 2021/3/7 22:31
     */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("【mqtt】消息接收! topic={}, messageId={}, Qos={}, payload={}", s, mqttMessage.getId(),
                mqttMessage.getQos(), new String(mqttMessage.getPayload()));
    }

    /**
     * 消息发布完成且收到ACK确认后的回调处理
     * 不同QoS下消息确认发布完成的时机不同:
     * 1. QoS0: 消息被网络发出后触发一次
     * 2. QoS1: 当收到broker的PUBACK消息后触发
     * 3. QoS2: 当收到broker的PUBCOMP消息后触发
     *
     * @param iMqttDeliveryToken 消息发布完成token
     * @since 2021/3/7 22:42
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        int messageId = iMqttDeliveryToken.getMessageId();
        String[] topics = iMqttDeliveryToken.getTopics();
        log.info("【mqtt】消息发布完成 ,messageId={}, topics={}", messageId, topics);
    }
}
