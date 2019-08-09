package com.lettuce.air;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.lettuce.air.service.device.DeviceService;

/**
 * springboot框架主入口
 * 
 * @author Lando
 *
 */
@SpringBootApplication
public class Application implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger LOGGER = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		DeviceService deviceService = contextRefreshedEvent.getApplicationContext().getBean(DeviceService.class);
		try {
			deviceService.initDevice();
		} catch (Exception e) {
			LOGGER.error("初始化失败", e);
		}
	}

}
