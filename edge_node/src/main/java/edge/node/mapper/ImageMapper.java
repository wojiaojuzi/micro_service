package edge.node.mapper;

import edge.node.model.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert("Insert into image(nodeName,imageId,imageShortId,imageRepository,imageTag,imageStatus,serviceName)"+
            " values(#{nodeName},#{imageId},#{imageShortId},#{imageRepository},#{imageTag},#{imageStatus},#{serviceName})")
    public void createImage(@Param("nodeName")String nodeName, @Param("imageId")String imageId,
                            @Param("imageShortId")String imageShortId, @Param("imageRepository")String imageRepository,
                            @Param("imageTag")String imageTag, @Param("imageStatus")boolean imageStatus,
                            @Param("serviceName") String serviceName);

    @Select("Select * from image where nodeName=#{nodeName}")
    public List<Image> getAllImage(@Param("nodeName") String nodeName);

    @Select("Select imageTag from image where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public String getTagByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName);

    @Select("Select imageRepository from image where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public String getRepositoryByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName);

    @Select("Select * from image where nodeName=#{nodeName} and serviceName=#{serviceName}")
    public Image getByNodeNameAndServiceName(@Param("nodeName")String nodeName, @Param("serviceName")String serviceName);

    @Update("Update image set imageId=#{imageId} where nodeName=#{nodeName} and imageRepository=#{imageRepository} and imageTag=#{imageTag}")
    public void updataImageIdByNodeNameAndImageRepositoryAndImageTag
            (@Param("nodeName") String nodeName, @Param("imageRepository") String imageRepository,
             @Param("imageTag") String imageTag, @Param("imageId")String imageId);

    @Update("Update image set imageShortId=#{imageShortId} where nodeName=#{nodeName} and imageRepository=#{imageRepository} and imageTag=#{imageTag}")
    public void updataImageShortIdByNodeNameAndImageRepositoryAndImageTag
            (@Param("nodeName") String nodeName, @Param("imageRepository") String imageRepository,
             @Param("imageTag") String imageTag, @Param("imageShortId")String imageShortId);

    @Update("Update image set imageStatus=#{imageStatus} where nodeName=#{nodeName} and imageRepository=#{imageRepository} and imageTag=#{imageTag}")
    public void updataImageStatusByNodeNameAndImageRepositoryAndImageTag
            (@Param("nodeName") String nodeName, @Param("imageRepository") String imageRepository, @Param("imageTag") String imageTag, @Param("imageStatus")boolean imageStatus);

}
