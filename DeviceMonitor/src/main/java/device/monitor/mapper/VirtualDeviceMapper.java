package device.monitor.mapper;


import device.monitor.model.VirtualDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VirtualDeviceMapper {
    @Select("Select * from device")
    public List<VirtualDevice> getAllDevice();
}
