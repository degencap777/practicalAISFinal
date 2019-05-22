package es.codeurjc.ais.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
public class WebApp implements WebSocketConfigurer {

	private static ConfigurableApplicationContext app;
	
	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(messageHandler(), "/tictactoe");
	}

	@Bean
	public TicTacToeHandler messageHandler() {
		return new TicTacToeHandler();
	}
	    
    public static void start() {
    	start(new String[] {});
    }

	private static void start(String[] args) {
		if(app == null) {
    		app = SpringApplication.run(WebApp.class, args);
    	} 
	}    
	
	public static void stop() {
		if(app != null) {
			app.close();
		}
	}
}