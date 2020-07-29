package gateways.zuul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="manager-service")
public interface Feign {
    @RequestMapping("/admins/logout")
    public void adminLogout(@RequestParam("token") String token);
}
