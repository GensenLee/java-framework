package org.devops.mjar.live.transform.resolve.mapper;

import lombok.Data;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/28 16:34
 * @description：转化字段
 */
@Data
public class TransformConvertField {

    public final static String ROOT_KEY = "root";

    public TransformConvertField(String from, String to) {
        this.from = from;
        this.to = to;
        this.required = false;
    }

    /**
     * 字段类型
     */
    protected FieldType fieldType = FieldType.single;

    /**
     * 源字段名
     */
    private String from;

    /**
     * 输出字段名
     */
    private String to;

    private boolean required;

    /**
     * 字段值转化器列表
     * 默认原值返回
     */
    private FiledMapper filedMapper = data -> data;

    /**
     * 值映射，优先级大于Mapper
     */
    private Map<String, String> valueMappings = new HashMap<>();

    /**
     * 父节点指针
     */
    private TransformConvertField parent;

    public void addChild(TransformConvertField child){
        if (child == null) {
            return;
        }
        child.setParent(this);
    }

    public void addChildren(List<TransformConvertField> children){
        if (ListUtil.isNull(children)) {
            return;
        }
        children.forEach(c -> c.setParent(this));
    }

    public String concatKey(){
        if (this.parent == null || this.parent.getFrom().equals(ROOT_KEY)) {
            return this.from;
        }
        return this.parent.concatKey() + CommonConstant.POINT_MARK + this.from;
    }


    public int depth(){
        if (this.parent == null){
            return 0;
        }
        return this.parent.depth() + 1;
    }

    /**
     * @param value
     * @return
     */
    public Object convertTo(Object value, Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect){
        if (this.required && (value == null || StringUtil.isEmpty(value))) {
            throw new IllegalArgumentException(this.from + " required");
        }
        return this.fieldType.convert(value, this, parentGroupCollect);
    }

}
