package edge.node.model.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="loger",url="localhost:2999/loger/log")
public interface LogFeign {
    @RequestMapping("/addLog")
    public void addLog(@RequestParam("account") String account, @RequestParam("detail") String detail);
}
