package org.devops.core.model.interfaces;

import java.util.HashMap;

import org.devops.core.model.core.Model;
import org.devops.core.model.dto.TableField;

public interface ModelAware {

    /**
     * 表初始化的时候调用
     */
    <T extends Model<?, ?>> void onCreated(T model, String tableSuffix);

    /**
     * 表数据初始化完成
     */
    <T extends Model<?, ?>> void onDataInitialized(T model, String tableSuffix);

    /**
     * 表初始化完成
     */
    <T extends Model<?, ?>> void onInitialized(T model, String tableSuffix);

    /**
     * 新增的列
     */
    <T extends Model<?, ?>> void onColumnAdded(T model, String tableSuffix, HashMap<String, TableField> mapTableField);
}

