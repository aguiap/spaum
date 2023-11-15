package com.catolica.sc.spaum.services.impl;

import com.catolica.sc.spaum.model.User;
import com.catolica.sc.spaum.repositories.UserRepository;
import com.catolica.sc.spaum.services.UsersServices;
import com.catolica.sc.spaum.utils.CryptographyUtils;
import com.catolica.sc.spaum.vo.UserVo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersServicesImpl implements UsersServices {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void changePassword(UserVo userVo) {
       User user = userRepository.findByUserName(userVo.getUsername());
       if(user != null){
           user.setPassword(CryptographyUtils.encodeWithPBKDF2(userVo.getPassword()));
           userRepository.save(user);
       }
    }


}
