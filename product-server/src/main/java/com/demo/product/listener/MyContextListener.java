package com.demo.product.listener;

import com.demo.product.config.SSHConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName MyContextListener
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/8
 **/
@Component
@WebListener
public class MyContextListener implements ServletContextListener {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SSHConnection conexionssh;

    /**
     * * Notification that the web application initialization process is starting.
     * All ServletContextListeners are notified of context initialization before
     * any filter or servlet in the web application is initialized.
     * The default implementation is a NO-OP.
     *
     * @param sce Information about the ServletContext that was initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 建立连接
        try {
            conexionssh.createSSH();
            log.info("成功建立SSH连接！");
        } catch (Throwable e) {
            log.info("SSH连接失败！");
            e.printStackTrace();
        }
    }

    /**
     * * Notification that the servlet context is about to be shut down. All
     * servlets and filters have been destroy()ed before any
     * ServletContextListeners are notified of context destruction.
     * The default implementation is a NO-OP.
     *
     * @param sce Information about the ServletContext that was destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 断开连接
        try {
            conexionssh.closeSSH();
            log.info("成功断开SSH连接!");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("断开SSH连接出错！");
        }
    }
}
