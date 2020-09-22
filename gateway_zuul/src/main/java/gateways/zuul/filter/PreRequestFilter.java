package gateways.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import gateways.zuul.config.MQSender;
import gateways.zuul.filter.constant.CommonUtil;
import gateways.zuul.filter.constant.ResponseEnum;
import gateways.zuul.mapper.ZuulMapper;
import gateways.zuul.model.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PreRequestFilter extends ZuulFilter {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private List<String> uri;

    @Autowired(required = false)
    private MQSender mqSender;

    @Autowired(required = false)
    private ZuulMapper zuulMapper;

    @Autowired
    public PreRequestFilter(List list) {
        this.uri = list;
        this.uri.add("/manager-service/admins/login");
        this.uri.add("/loger/log/addLog");
        this.uri.add("/loger/log/getLogByDate");
        this.uri.add("/edge-node/service/pullImage");
        //this.mqSender = mqSender;
        //this.zuulMapper = zuulMapper;
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
        Date createTime = new Date();
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getHeader("Authentication");//token
        System.out.println(token);
        try {
            DecodedJWT jwt = CommonUtil.phraseJWT(token, "EdgeComputingService", ResponseEnum.INVALID_USER_TOKEN.getMessage());
            String account = JSONObject.parseObject(jwt.getSubject()).getString("account");
            System.out.println("解析后的用户名："+account+"时间:"+jwt.getExpiresAt().getTime());
            String token1 = zuulMapper.getTokenByAccount(account);
            System.out.println(token1);
            if(token.equals(token1))
                System.out.println("验证通过");
            Date now = new Date();
            String detail = request.getRequestURL()+" "+request.getMethod()+" Remote:"+request.getRemoteHost();
            MsgInfo msgInfo = new MsgInfo("httpLog", mysqlSdf.format(createTime),
                    account,detail);
            mqSender.send(msgInfo);

            HttpServletResponse response = ctx.getResponse();
            //if (jwt.getExpiresAt().getTime() < now.getTime()){
                //zuulMapper.updateToken(token);
                //response.setHeader("code","10001");
                //RequestContext.getCurrentContext().setSendZuulResponse(false);
            //}else{
                response.setHeader("made","made");
            //}

        } catch (TokenExpiredException e) {
            System.out.println("超时");
            e.printStackTrace();
            RequestContext.getCurrentContext().setSendZuulResponse(false);
            HttpServletResponse response = ctx.getResponse();
            zuulMapper.updateToken(token);
            response.setHeader("code","10001");
            //RequestContext.getCurrentContext().setSendZuulResponse(false);
        }
        return null;
    }
}
