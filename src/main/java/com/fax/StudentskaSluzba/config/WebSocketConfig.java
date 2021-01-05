package com.fax.StudentskaSluzba.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //prefix brokera tj na frontu
        registry.enableSimpleBroker("/topic");
        //prefix na serveru
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //end point na serveru koji podleze autentikaciji mi smo ga zaobisli u security configu
        registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:4200");
        registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:4200").withSockJS();
    }
}
