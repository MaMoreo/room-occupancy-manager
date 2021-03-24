package com.smarthost.roommanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smarthost.roommanager.model.RoomManager;

@Configuration
public class BeanConfig {

	@Bean
	RoomManager csvFileReaderCreator() {
		return new RoomManager();
	}
}
