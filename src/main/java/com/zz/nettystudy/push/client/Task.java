package com.zz.nettystudy.push.client;

import com.zz.nettystudy.push.common.Constants;
import com.zz.nettystudy.push.common.entity.ClientMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task {

    private Logger logger = LoggerFactory.getLogger(Task.class);
    private static ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    public void heartbeat(String deviceId) {
        pool.scheduleAtFixedRate(() -> {
            logger.info("{} start heartbeat:{}", deviceId, LocalDateTime.now());

            ClientMessage heartMsg = new ClientMessage();
            heartMsg.setType(Constants.MESSAGE_TYPE_PING);
            heartMsg.setDeviceId(deviceId);
            AppClient.channel.writeAndFlush(heartMsg);
        }, 5, 30, TimeUnit.SECONDS);
    }

    public static void close() {
        pool.shutdown();
    }
}
