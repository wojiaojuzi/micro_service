package edge.node.mapper;

import edge.node.model.DeviceTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceTestMapper {
    @Select("Select * from device")
    public List<DeviceTest> getAllDevice();
}
