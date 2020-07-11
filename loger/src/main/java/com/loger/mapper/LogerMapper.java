package com.loger.mapper;


import com.loger.model.Loger;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogerMapper {
    @Insert("INSERT INTO log(time,account,detail) " +
            "VALUES(#{time},#{account},#{detail})")
    public void create_log(@Param("time")String time, @Param("account")String account, @Param("detail")String detail);

    @Select("SELECT * FROM log")
    public List<Loger> get_all();

    @Select("SELECT * FROM log WHERE time BETWEEN #{start} AND #{end}")
    public List<Loger> get_by_date(@Param("start") String start, @Param("end") String end);


    @Delete("DELETE FROM log WHERE time=#{time}")
    public void delete_log(@Param("time")String time);

    @Delete("DELETE FROM log")
    public void clear_log();
}
