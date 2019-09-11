package com.xcoding.springbootmongodb.properties.config;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.xcoding.springbootmongodb.properties.MongoDBProperties;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.beans.factory.BeanFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({MongoDBProperties.class})
public class MongoConfig {

    @Autowired
    MongoDBProperties properties;

    @Bean
    public MongoDbFactory mongoDbFactory(MongoDBProperties properties) {

        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();

        builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
        if (StringUtils.isNotBlank(properties.getReplicaSet())) {
            builder.requiredReplicaSetName(properties.getReplicaSet());
        }
        builder.threadsAllowedToBlockForConnectionMultiplier(
                properties.getThreadsAllowedToBlockForConnectionMultiplier());
        builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
        builder.maxWaitTime(properties.getMaxWaitTime());
        builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
        builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
        builder.connectTimeout(properties.getConnectTimeout());
        builder.socketTimeout(properties.getSocketTimeout());
//        builder.socketKeepAlive(properties.getSocketKeepAlive());
        builder.sslEnabled(properties.getSslEnabled());
        builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
        builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
        builder.heartbeatFrequency(properties.getHeartbeatFrequency());
        builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
        builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
        builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
        builder.localThreshold(properties.getLocalThreshold());

        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String address : properties.getAddress()) {
            String[] hostAndPort = address.split(":");
            String host = hostAndPort[0];
            Integer port = Integer.parseInt(hostAndPort[1]);
            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }
        System.out.println(" MongoDB地址列表serverAddresses:" + serverAddresses.toString());

        // 连接认证
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        if (StringUtils.isNotBlank(properties.getUsername())) {
            mongoCredentialList.add(MongoCredential.createScramSha1Credential(
                    properties.getUsername(),
                    StringUtils.isNotBlank(properties.getAuthenticationDatabase()) ? properties.getAuthenticationDatabase() : properties.getDatabase(),
                    properties.getPassword().toCharArray()));
        }
        System.out.println("连接认证mongoCredentialList:" + mongoCredentialList.toString());

        //创建客户端和Factory
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredentialList, mongoClientOptions);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());

        return mongoDbFactory;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }
        //不保存save _class
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

}
