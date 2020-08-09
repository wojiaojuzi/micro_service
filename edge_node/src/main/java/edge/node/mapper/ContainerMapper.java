package edge.node.mapper;
import edge.node.model.Container;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContainerMapper {
    @Insert("Insert into container(nodeName,serviceName,serviceStatus,containerName,containerId,containerShortId,containerStatus,isCreated)"+
            " values(#{nodeName},#{serviceName},#{serviceStatus},#{containerName},#{containerId},#{containerShortId},#{containerStatus},#{isCreated})")
    public void createContainer(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName,
                                @Param("serviceStatus")String serviceStatus, @Param("containerName")String containerName,
                                @Param("containerId")String containerId, @Param("containerShortId")String containerShortId,
                                @Param("containerStatus")String containerStatus, @Param("isCreated") boolean isCreated);

    @Update("Update container set containerStatus=#{containerStatus} where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void updateContainerStatusByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName,
                                                              @Param("containerStatus") String containerStatus);
    @Update("Update container set serviceStatus=#{serviceStatus} where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void updateServiceStatusByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName,
                                                              @Param("serviceStatus") String serviceStatus);
    @Update("Update container set containerId=#{containerId} where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void updateContainerIdByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName,
                                                            @Param("containerId") String containerId);
    @Update("Update container set containerShortId=#{containerShortId} where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void updateContainerShortIdByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName,
                                                               @Param("containerShortId") String containerShortId);
    @Update("Update container set containerName=#{containerName} where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void updateContainerNameByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName,
                                                               @Param("containerName") String containerName);
    @Update("Update container set isCreated=#{isCreated} where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void updateIsCreatedByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName,
                                                            @Param("isCreated") boolean isCreated);


    @Select("Select * from container where nodeName=#{nodeName}")
    public List<Container> getContainerByNodeName(@Param("nodeName")String nodeName);


    /*@Delete("Delete from container where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public void offContainer(@Param("nodeName") String nodeName, @Param("serviceName")String serviceName);*/
    @Delete("Delete from container where nodeName=#{nodeName}")
    public void deleteContainerByNodeName(@Param("nodeName") String nodeName);
}
