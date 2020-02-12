package zxs.up.newlife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"zxs.up.newlife.mapper"})
public class NewlifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewlifeApplication.class, args);
    }

}
