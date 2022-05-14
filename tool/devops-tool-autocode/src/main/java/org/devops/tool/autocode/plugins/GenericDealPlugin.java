package org.devops.tool.autocode.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @author er_dong_chen
 * @date 2018/12/13
 *
 * 需要配置 mapperRootInterface
 */
public class GenericDealPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateMapperRootInterface(interfaze);
        return true;
    }

    /* 令所有 Mapper 继承 BaseMapper */
    private void generateMapperRootInterface(Interface i) {
        String rootInterface = properties.getProperty("mapperRootInterface");
        if (rootInterface == null)
            return;
        i.addImportedType(new FullyQualifiedJavaType(rootInterface));
        i.addSuperInterface(new FullyQualifiedJavaType(_getGenericString(i, rootInterface.substring(rootInterface.lastIndexOf(".") + 1))));
    }

    /* 获取泛型格式的类名 */
    private String _getGenericString(Interface i, String className) {
        String mapper = i.getType().getShortName();
        className = className + "<" + mapper.substring(0, mapper.indexOf("Mapper")) + ">";
        return className;
    }
}
