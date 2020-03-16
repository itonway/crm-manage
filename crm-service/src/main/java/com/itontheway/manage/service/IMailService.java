package com.itontheway.manage.service;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/15 20:09
 */
public interface IMailService {
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:10
     * @Desc 简单的邮件发送
     * @Param [from, to, subject, content]
     * @Return void
     **/
    void sendSimpleMail(String from,String to, String subject, String content);
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:11
     * @Desc html邮件
     * @Param [from, to, subject, content]
     * @Return void
     **/
    void sendHtmlMail(String from,String to, String subject, String content);
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:11
     * @Desc 带附件的邮件
     * @Param [from, to, subject, content, filePath]
     * @Return void
     **/
    void sendAttachmentsMail(String from,String to, String subject, String content, String filePath);
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 20:11
     * @Desc 发送正文中有静态资源（图片）的邮件
     * @Param [from, to, subject, content, rscPath, rscId]
     * @Return void
     **/
    void sendInlineResourceMail(String from,String to, String subject, String content, String rscPath, String rscId);
}
