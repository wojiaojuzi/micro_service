package gateways.zuul.mapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ZuulMapper {

    @Select("select LoginToken from admin where account=#{account}")
    public String getTokenByAccount(@Param("account")String account);

}
