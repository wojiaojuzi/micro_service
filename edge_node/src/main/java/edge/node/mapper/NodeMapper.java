package edge.node.mapper;

import edge.node.model.Node;
import org.apache.ibatis.annotations.*;
import org.bouncycastle.util.Times;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface NodeMapper {
    @Insert("INSERT INTO node(node_name,location,nodeStatus,nodeCreateAt,runAt,endLastAt,cpu,memory)" +
            " VALUES(#{node_name},#{location},#{node_status},#{nodeCreateAt},#{runAt},#{endLastAt},#{cpu},#{memory})")
      public void create_node(@Param("node_name")String node_name, @Param("location")String location,
                            @Param("node_status")boolean node_status,
                            @Param("nodeCreateAt")String nodeCreateAt, @Param("runAt")String runAt,
                            @Param("endLastAt")String endLastAt, @Param("cpu")String cpu,
                              @Param("memory")String memory);


    /*-------select--------------*/
    @Select("SELECT * FROM node WHERE node_name=#{node_name}")
    public Node getNodeByNodeName(@Param("node_name") String node_name);

    @Select("SELECT * FROM node")
    public List<Node> get_all();

    @Select("SELECT nodeStatus FROM node WHERE node_name=#{node_name}")
    public boolean geNodeStatusByNodeName(@Param("node_name")String node_name);

    @Select("SELECT location FROM node")
    public List<String> get_all_location();

    @Select("SELECT location FROM node WHERE nodeStatus=true")
    public List<String> get_on_location();

    @Select("SELECT location FROM node WHERE nodeStatus=false")
    public List<String> get_off_location();

    @Select("SELECT * FROM node WHERE nodeStatus=true")
    public List<Node> get_on_num();

    @Select("SELECT id FROM node WHERE nodeStatus=true")
    public List<Integer> get_node_id();

    @Select("SELECT ip FROM node")
    public List<String> get_node_ip();

    /*-------update------------*/
    @Update("UPDATE node SET nodeStatus=#{node_status} WHERE node_name=#{node_name}")
    public void updateNodeStatusByNodeName(@Param("node_status")boolean node_status, @Param("node_name") String node_name);

    @Update("UPDATE node SET runAt=#{run_at} WHERE node_name=#{node_name}")
    public void updateRunAtByNodeName(@Param("run_at")String run_at,@Param("node_name")String node_name);

    @Update("UPDATE node SET endLastAt=#{end_last_at} WHERE node_name=#{node_name}")
    public void updateEndLastAtByNodeName(@Param("end_last_at")String run_at,@Param("node_name")String node_name);

    /*-------delete-----------*/
    @Delete("DELETE FROM node WHERE node_name=#{node_name}")
    public void deleteNodeByNodeName(@Param("node_name")String node_name);
}
