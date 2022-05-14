package org.devops.mjar.live.transform.resolve.xml.define;

import org.devops.mjar.live.transform.resolve.xml.define.parser.PolyvTransformerDefineParser;
import org.devops.mjar.live.transform.resolve.xml.define.parser.PolyvTransformerJClassParser;
import org.devops.mjar.live.transform.resolve.xml.define.parser.PolyvTransformerParsingParser;

/**
 * @author GENSEN
 * @date 2021/6/25 16:20
 * @description：transformer定义类型
 */
public enum PolyvTransformerDefineType {
    /**
     * java实现类方式
     * 指定com.sinmn.polyv.transform.transformer.Transformer实现类的全限定名
     * 如：com.sinmn.polyv.transform.transformer.DemoTransformer
     */
    JClass() {
        private final PolyvTransformerDefineParser parser = new PolyvTransformerJClassParser();

        @Override
        public PolyvTransformerDefineParser getParser() {
            return this.parser;
        }
    },

    /**
     * 在配置文件中定义字段映射
     */
    Parsing() {
        private final PolyvTransformerDefineParser parser = new PolyvTransformerParsingParser();

        @Override
        public PolyvTransformerDefineParser getParser() {
            return this.parser;
        }
    };


    PolyvTransformerDefineType() {
    }

    public abstract PolyvTransformerDefineParser getParser();


    public static PolyvTransformerDefineType get(String name) {
        for (PolyvTransformerDefineType value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("unsupported type " + name);
    }


}
