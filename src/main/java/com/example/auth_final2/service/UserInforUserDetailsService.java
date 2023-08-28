package com.example.auth_final2.service;

import com.example.auth_final2.entity.UserInfo;
import com.example.auth_final2.entity.UserInfoUserDetails;
import com.example.auth_final2.repositoy.UserInforRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;


@Component
public class UserInforUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInforRepository userInforRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> userInfo =  userInforRepository.findByName(username);
// this user needs to be converted into a userDetails Object
    return     userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found "));
    }
}
