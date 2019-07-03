package com.zhuoyuan.wxshop.utils;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Map;

public class RestTemplateUtils<T> {

    private static RestTemplate restTemplate = new RestTemplate();

    static {

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
    }

    public static <T> T getForObject(String url, Class<T> clazz, Map<String, ?> map) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        map.forEach(builder::queryParam);
        String newUrl = builder.build().encode().toString();
        return restTemplate.getForObject(newUrl, clazz);
    }

    public static <T> T postForObject(String url, Class<T> clazz, Map<String, ?> map) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        return restTemplate.postForObject(url, map, clazz);
    }
}
