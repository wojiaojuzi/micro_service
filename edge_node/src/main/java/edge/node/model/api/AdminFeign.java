package edge.node.model.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="manager-service",url="localhost:2999/manager-service/admins")
public interface AdminFeign {
    @RequestMapping("/getAdminNameByAccount")
    public String getAdminNameByAccount(@RequestParam("account")String account);
}