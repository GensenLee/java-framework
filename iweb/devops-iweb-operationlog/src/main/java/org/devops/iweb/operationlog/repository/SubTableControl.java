package org.devops.iweb.operationlog.repository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.devops.core.model.core.Model;
import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.DateUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.operationlog.enums.SubTableMode;
import org.mybatis.spring.SqlSessionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author GENSEN
 * @date 2021/11/30 16:59
 * @description：分表模式
 */
@Slf4j
@Setter
public class SubTableControl {

    private static SubTableMode mode;

    public static void setMode(SubTableMode mode) {
        SubTableControl.mode = mode;
    }

    private static final ThreadLocal<String> SUB_KEY_HANDLER = new ThreadLocal<>();

    public static void setSubKey(String subKey) {
        if (mode == SubTableMode.NONE) {
            return;
        }
        SUB_KEY_HANDLER.set(subKey);
    }

    public static void setByTime(Date time) {
        switch (mode) {
            case MONTH:
                SUB_KEY_HANDLER.set(DateUtil.getDateFormat(time, "yyyyMM"));
                break;
            case YEAR:
                SUB_KEY_HANDLER.set(DateUtil.getDateFormat(time, "yyyy"));
                break;
            default:
        }
    }

    public static void clean() {
        SUB_KEY_HANDLER.remove();
    }

    public static String getTableSuffix() {
        return getTableSuffix(SUB_KEY_HANDLER.get());
    }

    /**
     * @param suKey
     * @return
     */
    public static String getTableSuffix(String suKey) {
        switch (mode) {
            case YEAR:
                if (StringUtil.isEmpty(suKey)) {
                    return DateUtil.getDateFormat(new Date(), "yyyy");
                } else if (!StringUtil.isNumber(suKey) || suKey.length() != 4) {
                    throw new CommonRuntimeException(ApiResultCode.PARAMETER_FORMAT_ERROR, "当前开启按年分表模式，请传入'yyyy'格式四位长度的年份参数");
                } else {
                    String currentYear = DateUtil.getDateFormat(new Date(), "yyyy");
                    if (LongUtil.toLong(suKey) > LongUtil.toLong(currentYear) || LongUtil.toLong(suKey) < 2020L) {
                        throw new CommonRuntimeException(ApiResultCode.PARAMETER_FORMAT_ERROR, "年份参数超出限制");
                    }
                    return suKey;
                }
            case MONTH:
                if (StringUtil.isEmpty(suKey)) {
                    return DateUtil.getDateFormat(new Date(), "yyyyMM");
                } else if (!StringUtil.isNumber(suKey) || suKey.length() != 6) {
                    throw new CommonRuntimeException(ApiResultCode.PARAMETER_FORMAT_ERROR, "当前开启按月分表模式，请传入'yyyyMM'格式六位长度的年月参数");
                } else {
                    String currentYearMonth = DateUtil.getDateFormat(new Date(), "yyyyMM");
                    if (LongUtil.toLong(suKey) > LongUtil.toLong(currentYearMonth) || LongUtil.toLong(suKey) < 202001L) {
                        throw new CommonRuntimeException(ApiResultCode.PARAMETER_FORMAT_ERROR, "年月参数超出限制");
                    }
                    return suKey;
                }
            default:
                return  "";
        }
    }

    public static boolean checkExist() {
        return checkExist(SUB_KEY_HANDLER.get());
    }

    /**
     * 检查表是否存在
     *
     * @param subKey
     * @return
     */
    public static boolean checkExist(String subKey) {
        if (StringUtil.isEmpty(subKey)) {
            return true;
        }
        String tableName = "operation_log_" + subKey;
        OperationLogRepository repository = SpringContextUtil.getBean(OperationLogRepository.class);
        Model model = (Model) repository.getModel();
        model.init();
        Field field = BeanUtil.getField(Model.class, "sqlSessionFactory");
        SqlSessionFactory factory = null;
        try {
            field.setAccessible(true);
            factory = (SqlSessionFactory) field.get(model);
        } catch (IllegalAccessException e) {
            log.error("table check error", e);
            return false;
        } finally {
            field.setAccessible(false);
        }

        SqlSession sqlSession = SqlSessionUtils.getSqlSession(factory);
        Connection connection = sqlSession.getConnection();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            statement.execute("show tables like 'operation_log%'");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String table = resultSet.getString(1);
                if (table.equalsIgnoreCase(tableName)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            log.error("table check error", e);
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
            SqlSessionUtils.closeSqlSession(sqlSession, factory);
        }
        return false;
    }

}
