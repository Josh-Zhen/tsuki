package com.moonlit.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * es基本信息类
 *
 * @author Joshua
 * @version 1.0
 * @date 2/8/2023 18:31
 * @email by.Moonlit@hotmail.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticSearchProperties {

    /**
     * 地址
     */
    private String[] uris;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * CA证书指纹(sha256)
     * 在证书路径（docker：usr/share/elasticSearch/config/carts）下
     * 使用指令：openssl x509 -fingerprint -sha256 -in http_ca.crt
     */
    private String caFingerprint;

}
