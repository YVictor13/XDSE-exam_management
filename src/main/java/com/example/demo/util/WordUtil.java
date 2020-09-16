package com.example.demo.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @Desc SpringFramemarker
 * @author GQing
 */
public class WordUtil {

    private static Configuration configuration = null;

    private WordUtil() {
        throw new AssertionError();
    }

    /**
     * 根据模板生成相应的文件
     *
     * @param root     保存数据的map
     * @param template 模板文件的地址
     * @return
     */
    public static synchronized ByteArrayOutputStream process(Map<?, ?> root, String ftlName) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23); // 这里Configurantion对象不能有两个，否则多线程访问会报错
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassicCompatible(true);
        }
        configuration.setClassForTemplateLoading(WordUtil.class, "/fileTemplate/");

        Template t = null;
        try {
            t = configuration.getTemplate(ftlName);
            Writer w = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            t.process(root, w); // 这里w是一个输出地址，可以输出到任何位置，如控制台，网页等
            w.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return outputStream;
    }
}

