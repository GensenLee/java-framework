package org.devops.mjar.message.core;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.message.constant.MjarMessageConstant;
import org.devops.mjar.message.email.MailInfo;
import org.devops.mjar.message.email.SimpleMail;
import org.devops.mjar.message.model.MessageEmail;
import org.devops.mjar.message.model.MessageEmailTemplate;
import org.devops.mjar.message.repository.MessageEmailRepository;
import org.devops.mjar.message.repository.MessageEmailTemplateRepository;
import org.devops.mjar.message.util.MessagePassowrdUtil;
import org.devops.mjar.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class MessageEmailCore {

    @Autowired
    private MessageEmailRepository messageEmailRepository;

    @Autowired
    private MessageEmailTemplateRepository messageEmailTemplateRepository;

    @SuppressWarnings("serial")
    public void send(long partner, String templateNo, final String toAddress, final MessageVO messageVO) {
        send(partner, templateNo, new ArrayList<String>() {{
            add(toAddress);
        }}, new ArrayList<MessageVO>() {{
            add(messageVO);
        }});
    }

    public void send(long partner, String templateNo, List<String> toList, List<MessageVO> param) {

        if (CollectionUtils.isEmpty(toList)) {
            throw new CommonRuntimeException("请选择收件人");
        }

        if (!CollectionUtils.isEmpty(param) && toList.size() != param.size()) {
            throw new CommonRuntimeException("参数数量不对");
        }

        MessageEmail messageEmail = messageEmailRepository
                .where(MessageEmail.PARTNER_ID, partner)
                .where(MessageEmail.ACTIVE, CommonConstant.ACTIVE)
                .get();
        if (messageEmail == null) {
            throw new CommonRuntimeException("请先配置邮件服务信息");
        }

        MessageEmailTemplate messageEmailTemplate = messageEmailTemplateRepository
                .where(MessageEmailTemplate.PARTNER_ID, partner)
                .where(MessageEmailTemplate.ACTIVE, CommonConstant.ACTIVE)
                .where(MessageEmailTemplate.NO, templateNo)
                .get();

        if (messageEmailTemplate == null) {
            throw new CommonRuntimeException("找不到no:" + templateNo + "对应的邮件模板");
        }


        messageEmail.setPasswd(MessagePassowrdUtil.decode(MjarMessageConstant.getToken(), messageEmail.getPasswd()));

        MailInfo mailInfo = new MailInfo();

        for (int i = 0; i < toList.size(); i++) {
            MessageVO templateParam = param.get(i);
            Object subject = templateParam.getSubject();

            if (StringUtil.isEmpty(subject)) {
                subject = messageEmailTemplate.getSubject();
            }
            mailInfo.setValidate(true);

            StringWriter stringWriter = new StringWriter();
            String content = "";
            try {

                Properties properties = new Properties();
                //设置文件编码
                properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
                properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
                Velocity.init(properties);
                // velocity引擎
                VelocityEngine velocityEngine = new VelocityEngine();

                // 填充模板内容
                VelocityContext velocityContext = new VelocityContext();
                velocityContext.put("data", templateParam.getData());
                velocityEngine.evaluate(velocityContext, stringWriter, "", messageEmailTemplate.getContent());
                content = stringWriter.toString();
            } catch (Exception e) {
                throw new CommonRuntimeException("邮件模板渲染出错");
            } finally {
                try {
                    stringWriter.close();
                } catch (IOException e) {
                }
            }

            mailInfo.setContent(content);
            mailInfo.setToAddress(toList.get(i));
            mailInfo.setSubject(subject.toString());

            mailInfo.setNickName(messageEmail.getNickname());
            mailInfo.setFromAddress(messageEmail.getUsername());
            mailInfo.setUsername(messageEmail.getUsername());
            mailInfo.setMailServerHost(messageEmail.getHost());
            mailInfo.setMailServerPort(messageEmail.getPort() + "");
            mailInfo.setPassword(messageEmail.getPasswd());

            SimpleMail.sendHtmlMail(mailInfo);
        }
    }

    public void send(String templateNo, final String toAddress, final MessageVO messageVO) {
        send(-1, templateNo, toAddress, messageVO);
    }

    public void send(String templateNo, List<String> toList, List<MessageVO> param) {
        send(-1, templateNo, toList, param);
    }

    public static void main(String[] args) {
        System.out.println(MessagePassowrdUtil.encode("", "xxxx"));
    }
}
