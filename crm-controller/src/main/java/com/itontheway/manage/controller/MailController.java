package com.itontheway.manage.controller;

import com.itontheway.manage.service.IMailService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 公众号 itontheway
 * @description: 发送邮件
 * @date 2020/3/15 20:04
 */
@Api(tags = "邮件管理")
@Slf4j
@RestController
@RequestMapping(value = "mail")
public class MailController extends BaseController{
    @Autowired
    IMailService mailService;
    @Value("${spring.mail.username}")
    String from;
    private String sendTo = "xieglvip@163.com";
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:26
     * @Desc 简单邮件
     * @Param []
     * @Return void
     **/
    @GetMapping(value = "sendSimpleMail")
    public void sendSimpleMail(){
        mailService.sendSimpleMail(from,sendTo,"简单邮件主题","这是一封简单邮件");
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:27
     * @Desc HTML邮件
     * @Param []
     * @Return void
     **/
    @GetMapping(value = "sendHtmlMail")
    public void sendHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>这是一封HTML邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(from,sendTo,"HTML邮件主题",content);
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:27
     * @Desc 带附件的邮件
     * @Param []
     * @Return void
     **/
    @GetMapping(value = "sendAttachmentsMail")
    public void sendAttachmentsMail() {
        String filePath="E:\\xgl\\workproject\\wechat\\pic\\a.png";
        mailService.sendAttachmentsMail(from,sendTo, "带附件的邮件", "这是带附件的邮件", filePath);
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:27
     * @Desc 带附件的邮件
     * @Param []
     * @Return void
     **/
    @GetMapping(value = "sendInlineResourceMail")
    public void sendInlineResourceMail() {
        String rscId = "itontheway";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\xgl\\workproject\\wechat\\pic\\b.png";
        mailService.sendInlineResourceMail(from,sendTo,"主题：这是有图片的邮件", content, imgPath, rscId);
    }
}
