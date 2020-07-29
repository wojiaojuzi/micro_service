package gateways.zuul.filter.constant;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @Author: jojo
 * @Description: 公共工具类
 * @Date: Created on 2019/4/4 14:49
 */
public class CommonUtil {

    //加密
    public static <T> String createJWT(T content, String secret, Date createTime, Date expireTime) throws Exception {
        //HMAC
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(createTime)
                .withExpiresAt(expireTime)
                .withSubject(JSON.toJSONString(content))
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT phraseJWT(String token, String secret, String message) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //无效签名或者无效token
            throw new TokenExpiredException("token time out");
        }
        return jwt;
    }

}
