package edge.node.mapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ContainerMapper {
    @Insert("Insert into container(nodeName,serviceName,serviceStatus,containerName,containerId,containerShortId,containerStatus)"+
            " values(#{nodeName},#{serviceName},#{serviceStatus},#{containerName},#{containerId},#{containerShortId},#{containerStatus})")
    public void createContainer(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName,
                                @Param("serviceStatus")String serviceStatus, @Param("containerName")String containerName,
                                @Param("containerId")String containerId, @Param("containerShortId")String containerShortId,
                                @Param("containerStatus")String containerStatus);

    @Delete("Delete from container where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void offContainer(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName);
}
