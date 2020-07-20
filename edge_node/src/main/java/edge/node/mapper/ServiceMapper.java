package edge.node.mapper;

import edge.node.model.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ServiceMapper {
    @Insert("Insert into image(nodeName,imageId,imageShortId,imageTag,imageStatus)"+
            " values(#{nodeName},#{imageId},#{imageShortId},#{imageTag},#{imageStatus})")
    public void createImage(@Param("nodeName")String nodeName, @Param("imageId")String imageId,
                            @Param("imageShortId")String imageShortId, @Param("imageTag")String imageTag,
                            @Param("imageStatus")boolean imageStatus);

    @Select("Select * from image where nodeName=#{nodeName}")
    public List<Image> getAllImage(@Param("nodeName") String nodeName);

    @Update("Update image set imageId=#{imageId} where nodeName=#{nodeName} and imageTag=#{imageTag}")
    public void updataImageIdByNodeNameAndImageTag(@Param("nodeName") String nodeName,
                                                   @Param("imageTag") String imageTag, @Param("imageId")String imageId);
    @Update("Update image set imageShortId=#{imageShortId} where nodeName=#{nodeName} and imageTag=#{imageTag}")
    public void updataImageShortIdByNodeNameAndImageTag(@Param("nodeName") String nodeName,
                                                   @Param("imageTag") String imageTag, @Param("imageShortId")String imageShortId);
    @Update("Update image set imageStatus=#{imageStatus} where nodeName=#{nodeName} and imageTag=#{imageTag}")
    public void updataImageStatusByNodeNameAndImageTag(@Param("nodeName") String nodeName,
                                                   @Param("imageTag") String imageTag, @Param("imageStatus")boolean imageStatus);

}
