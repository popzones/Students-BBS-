package com.saybiu.bbs.security.provide;

import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.security.token.UserPasswordAuthenticationToken;
import com.saybiu.bbs.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @Author wangwei
 * @Date 2021/5/27 19:37
 * @Version 1.0
 */
public class UserPasswordAuthenticationProvide implements AuthenticationProvider {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 UserPasswordAuthenticationToken 的子类或子接口
        return UserPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPasswordAuthenticationToken authenticationToken = (UserPasswordAuthenticationToken) authentication;
        //取出用户名以及密码
        Map<String,Object> paramMap = (Map<String, Object>) authenticationToken.getPrincipal();
        String username = paramMap.get("username").toString();
        String password=paramMap.get("password").toString();
        User user = queryUser(username);
        checkPassword(user,password);
        //判断是否被封禁
        checkMuted(user);
        //user取权限列表
        List<GrantedAuthority> grantedAuthorities = getUserAuthorities("ROLE_admin");

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        UserPasswordAuthenticationToken authenticationResult = new UserPasswordAuthenticationToken(user,grantedAuthorities);
        return authenticationResult;
    }

    public List<GrantedAuthority> getUserAuthorities(String role)
    {
        List<GrantedAuthority> authorityGranterList= AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_WOCAONIMA,ROLE_fwqfwqf");
        return authorityGranterList;
    }

    /**
     * 通过电话号查询用户信息
     * @param username 电话号
     * @return
     */
    public User queryUser(String username)
    {
        return userService.getUserInfo(username);
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


    private void checkPassword(User user,String password) {
        if (user.getUserPassword()==null){
            throw  new UsernameNotFoundException("尚未设置密码，请通过手机号登录");
        }
        if (!password.equals(user.getUserPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }
    }

}
