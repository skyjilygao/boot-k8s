package cn.skyjilygao.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;

@Slf4j
@SpringBootApplication
@RestController
public class BootK8sApplication {
    @Value("${server.port}")
    private Integer port;

    public static void main(String[] args) {
        SpringApplication.run(BootK8sApplication.class, args);
    }

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        //获取本机ip
        String ip=addr.getHostAddress();
        //获取本机计算机名称
        String hostName=addr.getHostName();
        log.info("本机IP："+ip+"\n本机名称:"+hostName);
        return String.format("hello %s" +
                        "\nserver port: %s" +
                        "\n本机IP：%s" +
                        "\n本机名称：%s" +
                        "\n时间：%s",
                name, port, ip, hostName, ZonedDateTime.now());
    }
}
