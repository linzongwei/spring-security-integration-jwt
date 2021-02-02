package com.linzongwei.authentication.security.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description: jwt配置信息
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
@Data
public class JwtAuthenticationConfig {

    @Value("${jwt.url:/login}")
    private String url;

    @Value("${jwt.header:Authorization}")
    private String header;

    @Value("${jwt.prefix:Bearer}")
    private String prefix;

    /**
     * 默认1小时超时
     */
    @Value("${jwt.expiration:#{60*60*24}}")
    private int expiration;

    @Value("${jwt.secret:123456}")
    private String secret;

}
