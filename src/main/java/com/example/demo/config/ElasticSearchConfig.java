package com.example.demo.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class ElasticSearchConfig {

	@Value("${elasticsearch.host:localhost}")
	public String host;
	@Value("${elasticsearch.port:9300}")
	public int port;

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	@Bean
	public Client client() {
		TransportClient client = null;
		try {
			Settings settingsBuilder = Settings.builder()
		            .put("client.transport.sniff", false).build();
			client = new PreBuiltTransportClient(settingsBuilder)
					.addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
			System.out.println("------>abe" + client.listedNodes());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
}
