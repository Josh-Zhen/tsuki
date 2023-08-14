package com.moonlit.elasticsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;


/**
 * ES配置类
 *
 * @author Joshua
 * @version 1.0
 * @date 23/3/2023 1:20
 * @email by.Moonlit@hotmail.com
 */
@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final ElasticSearchProperties properties;

    @Override
    public ClientConfiguration clientConfiguration() {

        // 使用构建器来提供集群地址
        return ClientConfiguration.builder()
                // 设置连接地址
                .connectedTo(properties.getUris())
                // 启用ssl并配置CA指纹
                .usingSsl(properties.getCaFingerprint())
                // 设置用户名密码
                .withBasicAuth(properties.getUsername(), properties.getPassword())
                // 创建连接信息
                .build();
    }
}
