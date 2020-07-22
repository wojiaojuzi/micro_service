package edge.node.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;
import lombok.Data;
@Data
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "serviceport")
public class serviceport {
    public Map<String,String> portmap;
}
