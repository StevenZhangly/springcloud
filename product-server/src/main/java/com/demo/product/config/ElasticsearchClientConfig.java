package com.demo.product.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ElasticsearchClientConfig
 * @Description: TODO
 * @Author zly
 * @Date 2021/6/1
 **/
@Configuration
public class ElasticsearchClientConfig {

    @Bean
    public RestClientBuilder restClientBuilder(){
        return RestClient.builder(new HttpHost("localhost", 9200, "http"));
    }

    @Bean
    public RestHighLevelClient highLevelClient(RestClientBuilder restClientBuilder){
        restClientBuilder.setMaxRetryTimeoutMillis(60000);
        return new RestHighLevelClient(restClientBuilder);
    }
}
