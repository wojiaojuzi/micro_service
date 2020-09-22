package com.manager.mapper;

import com.manager.model.Admin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public interface AdminMapper {
    @Select("Select * from admin")
    List<Admin> getAll();

    @Select("SELECT * FROM admin WHERE account=#{account} AND password=#{password};")//账号+密码获取用户所有信息
    Admin getByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Update("UPDATE admin SET tokenCreateAt=#{tokenCreateTime} WHERE account =#{account};")//更新TokenCreateTime
    void updateTokenCreateTimeByAccount(@Param("tokenCreateTime") String tokenCreateTime, @Param("account") String account);

    @Update("UPDATE admin SET LoginToken=#{token} WHERE account =#{account};")//更新Token
    void updateTokenByAccount(@Param("token") String token, @Param("account") String account);

    @Select("SELECT tokenCreateAt FROM admin WHERE account =#{account};")//获取TokenCreateTime
    String getTokenCreateTime(@Param("account") String account);

    @Select("SELECT * FROM admin WHERE account =#{account};")//用账号获取所有用户所有信息
    Admin getByAccount(@Param("account") String account);

    @Select("SELECT * FROM admin WHERE LoginToken =#{token};")//用账号获取所有用户所有信息
    Admin getByToken(@Param("token") String token);

    @Select("SELECT account FROM admin WHERE LoginToken =#{LoginToken};")
    String getAccountFromToken(@Param("LoginToken") String LoginToken);


    @Select("SELECT password FROM admin WHERE LoginToken =#{token};")
    String getPassWordByToken(@Param("token") String token);

    @Select("SELECT password FROM admin WHERE account =#{account};")//账号获取密码
    String getPassWordByAccount(@Param("account")String account);

    @Insert("INSERT INTO admin(id,account,password,LoginToken,tokenCreateAt,createAt)" +
            "VALUES(#{id},#{account},#{password},#{LoginToken},#{tokenCreateAt},#{createAt});" )
    void creatAdmin(@Param("id")String id,@Param("account")String account,
                    @Param("password")String password, @Param("LoginToken")String LoginToken,
                    @Param("tokenCreateAt")String tokenCreateAt,
                    @Param("createAt")String createAt);
    @Update("UPDATE admin SET password=#{newpassword} WHERE account=#{account};")
    void updatePassWordByAccount(@Param("account")String account,@Param("newpassword")String newpassword);
}
