package com.itcodes.myhub.resthttp.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName HttpClientConfig  http连接池配置
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/15
 */
@Configuration
@EnableScheduling
@Slf4j
public class HttpClientConfig {

    //确定连接建立之前的超时时间（以毫秒为单位）
    private final static int CONNECT_TIMEOUT = 30000;
    //向连接管理器请求连接 超时时间
    private final static int REQUEST_TIMEOUT = 30000;
    //等待数据超时
    private final static int SOCKET_TIMEOUT = 60000;

    //最大总连接数
    private static final int MAX_TOTAL_CONNECTIONS = 50;
    //默认保持心跳时间
    private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 20 * 1000;
    //关闭空闲连接的等待时间
    private static final int CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS = 30;

    /**
     * 连接池管理器
     * 通过在池中租用连接而不是创建全新的连接来请求 管理器内有持久化连接
     *
     * @return
     */
    @Bean
    public PoolingHttpClientConnectionManager poolingConnectionManager() {
        SSLContextBuilder builder = new SSLContextBuilder();

        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            log.error("连接池管理器初始化失败:" + e.getMessage(), e);
        }

        SSLConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(builder.build());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("连接池管理器初始化失败: " + e.getMessage(), e);
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();

        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        return poolingConnectionManager;
    }

    /**
     * 连接保持心跳策略
     *
     * @return
     */
    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator
                        (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();

                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return DEFAULT_KEEP_ALIVE_TIME_MILLIS;
            }
        };
    }

    /**
     * restTemplater模板 将使用此httpClient发送请求
     *
     * @return
     */
    @Bean
    public CloseableHttpClient httpClient() {
        //请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                //连接请求超时时间
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                //连接超时时间
                .setConnectTimeout(CONNECT_TIMEOUT)
                //读取数据时阻塞链路的超时时间
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        return HttpClients.custom()
                //请求配置
                .setDefaultRequestConfig(requestConfig)
                //连接池管理器
                .setConnectionManager(poolingConnectionManager())
                //连接保持心跳策略
                .setKeepAliveStrategy(connectionKeepAliveStrategy())
                .build();
    }

    /**
     * 空闲连接监视器
     * 该线程定期检查所有未使用的连接,并释放未使用及过期的连接
     *
     * @param connectionManager
     * @return
     */
    @Bean
    public Runnable idleConnectionMonitor(final PoolingHttpClientConnectionManager connectionManager) {
        return new Runnable() {
            @Override
            //定时每10s检测
            @Scheduled(fixedDelay = 10000)
            public void run() {
                try {
                    if (connectionManager != null) {
                        log.trace("空闲连接监控器: - 关闭过期和空闲的连接...");
                        connectionManager.closeExpiredConnections();
                        connectionManager.closeIdleConnections(CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS, TimeUnit.SECONDS);
                    } else {
                        log.trace("空闲连接监控器: - Http客户端连接管理器未初始化");
                    }
                } catch (Exception e) {
                    log.error("空闲连接监控器: - 发生异常. msg=[{}], e=[{}]", e.getMessage(), e);
                }
            }
        };
    }

}
