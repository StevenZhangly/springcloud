package com.demo.order.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Properties;

/**
 * @ClassName SSHConnection
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/8
 **/
@Component
@Configuration
public class SSHConnection {

    private Session sesion;

    /**
     * 创建SSH连接
     */
    public void createSSH() throws JSchException {

        JSch jsch = new JSch();
        // 需要用到了开启
        sesion = jsch.getSession("admin", "10.37.20.204", 22);
        sesion.setPassword("admin1234");

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);
        // 去连接
        sesion.connect();
        //  设置转发
        sesion.setPortForwardingL(13306, "rm-wz9ol46kro5h2gbjqzo.mysql.rds.aliyuncs.com", 3306);

    }

    /**
     * 关闭SSH连接
     */
    public void closeSSH() {
        sesion.disconnect();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:13306/testdb", "zly", "zly_1988");
    }

    public static void main(String[] args) throws JSchException, SQLException, ClassNotFoundException {
        SSHConnection sshConnection = new SSHConnection();
        sshConnection.createSSH();
        Connection connection = sshConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM T_ORDER");
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(rs);
        while (rs.next()) {
            //获取id列数据
            String id = rs.getString("id");
            //获取goodsName列数据
            String goodsName = rs.getString("name");
            //输出结果
            System.out.println(id + "\t" + goodsName);
        }
        rs.close();
        connection.close();
        sshConnection.closeSSH();
    }
}
