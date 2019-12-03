package tk.mybatis.simple;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = "tk.mybatis.simple.*")
@EnableSwagger2
public class BaseController {

    public static void main(String[] args) {
        SpringApplication.run(BaseController.class,args);
    }

}
