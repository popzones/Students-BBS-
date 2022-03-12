package com.saybiu.bbs.security.provide;

import com.alibaba.fastjson.JSON;
import com.saybiu.bbs.entity.SmsCode;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.security.token.SmsCodeAuthenticationToken;
import com.saybiu.bbs.security.exception.SmsCodeError;
import com.saybiu.bbs.security.exception.SmsCodeNotFound;
import com.saybiu.bbs.service.UserService;
import com.saybiu.bbs.utils.JedisUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;
import java.util.Map;

/**
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 * @author jitwxs
 * @since 2019/1/9 13:59
 */

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;
    private ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        //取出手机号码以及验证码
        WebAuthenticationDetails details = (WebAuthenticationDetails)authenticationToken.getDetails();
        String remoteAddress = details.getRemoteAddress();
        Map<String,Object> paramMap = (Map<String, Object>) authenticationToken.getPrincipal();
        String phone = paramMap.get("phone").toString();
        String smsCode=paramMap.get("smsCode").toString();
        //System.out.println("手机号码"+phone);
        //验证逻辑
        checkSmsCode(phone,smsCode);
        //数据库中查询用户信息
        User user = queryUser(phone,remoteAddress);
        //判断是否被封禁
        checkMuted(user);
        //user取权限列表
        List<GrantedAuthority> grantedAuthorities = getUserAuthorities("ROLE_admin");
        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user,grantedAuthorities);
        return authenticationResult;
    }

    public List<GrantedAuthority> getUserAuthorities(String role)
    {
        List<GrantedAuthority> authorityGranterList= AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin");
        return authorityGranterList;
    }

    /**
     * 通过电话号查询用户信息
     * @param phone
     * @return
     */
    public User queryUser(String phone,String ip)
    {
        User user=userService.getUserInfo(phone);
        if(user==null)
        {
            user=new User();
            user.setUserPhone(phone);
            user  = userService.commonRegister(user,ip);
            user.setUserMuted(false);
            System.out.println(user);
        }
        return user;
    }

    /**
     * 判断账号是否被封禁
     * @param user
     */
    private void checkMuted(User user)
    {
        if(user.getUserMuted()==true)
        {
            throw new LockedException("账户已被封禁");
        }
    }

    /**
     * 判断验证码是否正确
     * @param phone
     * @param smsCode
     */
    private void checkSmsCode(String phone,String smsCode) {


        SmsCode sms = JSON.parseObject(JedisUtils.getJedis().get(phone),SmsCode.class);

        if(sms==null) {
            throw new SmsCodeNotFound("该手机号码没有申请验证码");
        }

        String cacheSmsCode =  sms.getDynamicCode();

        if(!smsCode.equals(cacheSmsCode))
        {
           throw new SmsCodeError("验证码错误");
        }
    }




}