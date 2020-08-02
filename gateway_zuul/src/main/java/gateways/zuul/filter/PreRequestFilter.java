package gateways.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import gateways.zuul.filter.constant.CommonUtil;
import gateways.zuul.filter.constant.ResponseEnum;
import gateways.zuul.mapper.ZuulMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


public class PreRequestFilter extends ZuulFilter {
    private List<String> uri;

    @Autowired(required = false)
    private ZuulMapper zuulMapper;

    @Autowired
    public PreRequestFilter(List list) {
        this.uri = list;
        this.uri.add("/manager-service/admins/login");
        this.uri.add("/loger/log/addLog");
        this.uri.add("/loger/log/getLogByDate");

    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("000");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if(uri.contains(request.getRequestURI()))
            return false;
        else
            return true;
        //return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("111");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //System.out.println(request.getParameter("account"));
        //String token = request.getParameter("Authentication-Token");
        String token = request.getHeader("Authentication");//token
        System.out.println(token);
        try {
            DecodedJWT jwt = CommonUtil.phraseJWT(token, "EdgeComputingService", ResponseEnum.INVALID_USER_TOKEN.getMessage());
            String account = JSONObject.parseObject(jwt.getSubject()).getString("account");
            System.out.println("解析后的用户名："+account+"时间:"+jwt.getExpiresAt().getTime());
            System.out.println("111");
            String token1 = zuulMapper.getTokenByAccount(account);
            System.out.println(token1);
            if(token.equals(token1))
                System.out.println("验证通过");
            Date now = new Date();
            HttpServletResponse response = ctx.getResponse();
            //if (jwt.getExpiresAt().getTime() < now.getTime()){
                //zuulMapper.updateToken(token);
                //response.setHeader("code","10001");
                //RequestContext.getCurrentContext().setSendZuulResponse(false);
            //}else{
                response.setHeader("made","made");
            //}

        } catch (TokenExpiredException e) {
            e.printStackTrace();
            RequestContext.getCurrentContext().setSendZuulResponse(false);
            HttpServletResponse response = ctx.getResponse();
            zuulMapper.updateToken(token);
            response.setHeader("code","10001");
            RequestContext.getCurrentContext().setSendZuulResponse(false);
        }
        return null;
    }
}
