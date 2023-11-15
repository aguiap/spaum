package com.catolica.sc.spaum.services;

import com.catolica.sc.spaum.vo.EmailDataVo;

public interface EmailServices {
    String sendEmail(EmailDataVo emailDataVo, String origin) throws Exception;
}
