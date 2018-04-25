package com.sugar.com.util;

import com.sugar.com.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendStudentMailUtil {

    static final String MAIL_GROUP = "xxx@110.com";
    static final String HTTP_URL = "http：// ";


    public void sendResultMail(List<Student> students) {
        String a = createMailHtml(students);
        String subject = "班级学生表";
        try {
            MailUtil.sendHtmlMail(MAIL_GROUP, subject, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createMailHtml(List<Student> students) {
        String htmlText = "";
        try {
            //创建一个合适的Configration对象
            Configuration configuration = new Configuration();
            // 1. 通过类加载器: 文件位于resource下
            configuration.setClassForTemplateLoading(this.getClass(), "/template");
            // 2. 通过绝对路径
            configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\sugar\\Desktop\\spring-send-mail\\src\\main\\webapp\\WEB-INF\\template)"));
            // 3. 通过上下文 : 位于webapp
            //configuration.setServletContextForTemplateLoading(context,  "/template");
            // 4. ftp：
            //创建一个获取外部url的
            RemoteTemplateLoader remoteTemplateLoader = new RemoteTemplateLoader(HTTP_URL);
            configuration.setTemplateLoader(remoteTemplateLoader);


            configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码
            //获取或创建一个模版。
            Template template = configuration.getTemplate("model.ftl");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("students", students);
            paramMap.put("date", LocalDateTime.now().toLocalDate());
            Writer writer = new OutputStreamWriter(new FileOutputStream("success.ftl"), "UTF-8");
            template.process(paramMap, writer);
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, paramMap);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlText;
    }
}
