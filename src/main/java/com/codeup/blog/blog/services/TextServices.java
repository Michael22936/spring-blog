package com.codeup.blog.blog.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextServices {
    // Find your Account Sid and Token at twilio.com/user/account
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.authorize.token}")
    private String AUTH_TOKEN;


    public void sendText(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+18302377771"),
                new PhoneNumber("+19388887261"),
                "Welcome").create();

        System.out.println(message.getSid());
    }


}