package com.demo.product.util;

import org.graylog2.syslog4j.Syslog;
import org.graylog2.syslog4j.SyslogConstants;
import org.graylog2.syslog4j.SyslogIF;
import org.graylog2.syslog4j.util.SyslogUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * @ClassName SyslogUtil
 * @Description: TODO
 * @Author zly
 * @Date 2020/10/27
 **/
public class SyslogUtil {

    private static final Logger logger = LoggerFactory.getLogger(SyslogUtil.class);

    public static void sendMsg(String ip, int port, String msg) throws Exception {
        SyslogIF syslog = Syslog.getInstance(SyslogConstants.UDP);
        syslog.getConfig().setHost(ip);
        syslog.getConfig().setPort(port);
        //syslog.getConfig().setLocalName("test1");
        //syslog.getConfig().setFacility("USER");
        logger.info(InetAddress.getLocalHost().getHostName());
        int level = SyslogUtility.getLevel("EMERGENCY");
        syslog.log(level, msg);
        //syslog.shutdown();
    }

    public static void main(String[] args) throws Exception {
        String msg = "我自横刀向天笑，去留肝胆两昆仑";
        //SyslogUtil.sendMsg("172.16.5.188", 514, msg);
        logger.info("syslog test");
    }
}
