package me.wonwoo.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.path;

@SpringBootApplication
public class EdgeServiceApplication {

	@Bean
	public DiscoveryClientRouteDefinitionLocator discoveryRoutes(DiscoveryClient discoveryClient) {
		return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
	}

	@Bean
	RouteLocator gatewayRoutes() {
		return Routes
				.locator()
				.route("spring")
				.predicate(path("/spring"))
				.uri("http://spring.io:80")
				.route("lb")
				.predicate(path("/lb"))
				.uri("lb://person-service/persons")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}
}
