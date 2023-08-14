package com.moonlit.rabbitmq;

/**
 * 消息监听器
 *
 * @author Joshua
 * @version 1.0
 * @date 14/6/2023 14:29
 * @email by.Moonlit@hotmail.com
 */
public abstract class RabbitListener<T> {

    /**
     * 接受消息时调用
     *
     * @param messages 消息内容
     * @return 结果
     */
    public abstract Boolean onReceive(T messages);

    /**
     * 消费异常时调用
     *
     * @param messages 消息内容
     * @param e        异常信息
     */
    public abstract void onError(T messages, Exception e);

}
