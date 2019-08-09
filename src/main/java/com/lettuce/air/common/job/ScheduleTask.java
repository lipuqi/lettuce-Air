package com.lettuce.air.common.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lettuce.air.cache.MapCache;
import com.lettuce.air.pojo.basic.Cache;
import com.lettuce.air.pojo.device.BasicDevice;
import com.lettuce.air.pojo.device.DeviceConstant;
import com.lettuce.air.pojo.device.OperationPi;
import com.lettuce.air.profile.ServiceConstant;
import com.lettuce.air.service.device.DeviceService;

@Component
@Configuration      
@EnableScheduling   
public class ScheduleTask {
	
	private static final Logger LOGGER = Logger.getLogger(ScheduleTask.class);
	
	@Autowired
	private MapCache<String, Cache> mapCache;
	
	@Autowired
	private DeviceService deviceService;
	
    @Scheduled(cron = "0 0/1 * * * ?") //1分钟执行一次
    private void cleanMapTasks() {
		try {
			mapCache.cleanMap();
		} catch (Exception e) {
			LOGGER.error("定时清理任务失败", e);
		}
    }

    @Scheduled(cron = "0 0/30 * * * ?") //30分钟执行一次
    private void queryStatusTasks() {
		try {
			deviceService.sendCommand(ServiceConstant.QUERY_STATUS, 1);
		} catch (Exception e) {
			LOGGER.error("定时查询灯状态的指令失败", e);
		}
    }
    
    @Scheduled(cron = "0 0 0/1 * * ?") //1小时执行一次
    private void healthCheckTasks() {
		try {
			BasicDevice device = (OperationPi) mapCache.get(DeviceConstant.OperationPi);
			if(device != null){
				if((System.currentTimeMillis() - device.getUpdateTime()) > 60 * 60 * 1000){
					device.setStatus(0);
					device.setUpdateTime();
					mapCache.put(device.getDeviceKey(), device);
					LOGGER.warn("----------------------设备已下线----------------------");
				}
			}
		} catch (Exception e) {
			LOGGER.error("健康检测任务失败", e);
		}
    }
    
}
