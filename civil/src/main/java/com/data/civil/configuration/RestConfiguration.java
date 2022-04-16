package com.data.civil.configuration;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class RestConfiguration {

    @Value("${rest.client.sslbypass}")
    private Boolean sslBypass;

    @Value("${rest.client.readTimeout}")
    private Integer readTimeout;

    @Value("${rest.client.maxConnectionTotal}")
    private Integer maxConnectionTotal;

    @Value("${rest.client.defaultMaxRoute}")
    private Integer defaultMaxRoute;

    @Bean(name = "restClientBasic")
    public RestTemplate restClientBasic() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
        HostnameVerifier hostnameVerifier = (s, sslSession) -> this.sslBypass;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", csf)
                .register("http", new PlainConnectionSocketFactory()).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(maxConnectionTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxRoute);
        CloseableHttpClient httpClient = HttpClients.custom()
                .disableRedirectHandling()
                .setSSLSocketFactory(csf)
                .setConnectionManager(connectionManager)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(this.readTimeout);
        requestFactory.setReadTimeout(this.readTimeout);
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

}
