package org.devops.iweb.operationlog.service;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.interfaces.BaseUserInfo;
import org.devops.core.utils.util.BooleanUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.operationlog.model.OperationLog;
import org.devops.iweb.operationlog.repository.OperationLogRepository;
import org.devops.iweb.operationlog.repository.SubTableControl;
import org.devops.iweb.operationlog.web.vo.OperationLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author GENSEN
 * @date 2021/11/19 16:27
 * @description：操作日志
 */
@Service
public class OperationLogService {

    @Autowired
    private OperationLogRepository operationLogRepository;

    public PageResult<OperationLog> list(OperationLogSearchVO vo, BaseUserInfo userInfo) {
        return list(vo, userInfo.companyId());
    }

    public PageResult<OperationLog> list(OperationLogSearchVO vo) {
        return list(vo, 0L);
    }

    /**
     * @param vo
     * @param companyId
     * @return
     */
    public PageResult<OperationLog> list(OperationLogSearchVO vo, Long companyId) {
        SubTableControl.setSubKey(vo.getScope());
        if (!SubTableControl.checkExist()) {
            return PageResult.get();
        }
        ModelWhere modelWhere = new ModelWhere();
        if (LongUtil.toLong(companyId) > 0L) {
            modelWhere.add(OperationLog.COMPANY_ID, companyId);
        }

        ModelWhere conditionWhere = new ModelWhere();

        if (StringUtil.isNotEmpty(vo.getResult())) {
            conditionWhere.add(OperationLog.RESULT, vo.getResult());
        }

        //<editor-fold desc="record_time 过滤">
        if (LongUtil.toLong(vo.getStartRecordTime()) > 0) {
            conditionWhere.add(OperationLog.RECORD_TIME, new Date(vo.getStartRecordTime()), ModelOperator.EGT);
        }
        if (LongUtil.toLong(vo.getEndRecordTime()) > 0) {
            conditionWhere.add(OperationLog.RECORD_TIME, new Date(vo.getEndRecordTime()), ModelOperator.ELT);
        }
        //</editor-fold>

        //<editor-fold desc="request 过滤">
        if (StringUtil.isNotEmpty(vo.getRequestMethod())) {
            conditionWhere.add(OperationLog.REQUEST_METHOD, vo.getRequestMethod().toUpperCase());
        }
        if (StringUtil.isNotEmpty(vo.getRequestId())) {
            conditionWhere.add(OperationLog.REQUEST_ID, vo.getRequestId().toUpperCase());
        }
        if (StringUtil.isNotEmpty(vo.getRequestIp())) {
            conditionWhere.add(OperationLog.REQUEST_IP, vo.getRequestIp());
        }
        if (StringUtil.isNotEmpty(vo.getRequestUri())) {
            conditionWhere.add(OperationLog.REQUEST_URI, vo.getRequestUri(), ModelOperator.LIKE);
        }
        if (StringUtil.isNotEmpty(vo.getRequestHost())) {
            conditionWhere.add(OperationLog.REQUEST_HOST, vo.getRequestHost(), ModelOperator.LIKE);
        }
        //</editor-fold>

        //<editor-fold desc="地区过滤">
        if (StringUtil.isNotEmpty(vo.getCountry())) {
            conditionWhere.add(OperationLog.COUNTRY, vo.getCountry());
        }
        if (StringUtil.isNotEmpty(vo.getProvince())) {
            conditionWhere.add(OperationLog.PROVINCE, vo.getProvince());
        }
        if (StringUtil.isNotEmpty(vo.getCity())) {
            conditionWhere.add(OperationLog.CITY, vo.getCity());
        }

        //</editor-fold>

        //<editor-fold desc="自定义参数">
        if (StringUtil.isNotEmpty(vo.getOperationDescription())) {
            conditionWhere.add(OperationLog.OPERATION_DESCRIPTION, vo.getOperationDescription(), ModelOperator.LIKE);
        }
        if (StringUtil.isNotEmpty(vo.getParam1())) {
            ModelOperator operator = ModelOperator.LIKE;
            if (BooleanUtil.toBoolean(vo.getExactParam1())) {
                operator = ModelOperator.EQ;
            }
            conditionWhere.add(OperationLog.PARAM1, vo.getParam1(), operator);
        }
        if (StringUtil.isNotEmpty(vo.getParam2())) {
            ModelOperator operator = ModelOperator.LIKE;
            if (BooleanUtil.toBoolean(vo.getExactParam2())) {
                operator = ModelOperator.EQ;
            }
            conditionWhere.add(OperationLog.PARAM2, vo.getParam2(), operator);
        }
        if (StringUtil.isNotEmpty(vo.getParam3())) {
            ModelOperator operator = ModelOperator.LIKE;
            if (BooleanUtil.toBoolean(vo.getExactParam3())) {
                operator = ModelOperator.EQ;
            }
            conditionWhere.add(OperationLog.PARAM3, vo.getParam3(), operator);
        }
        if (StringUtil.isNotEmpty(vo.getParam4())) {
            ModelOperator operator = ModelOperator.LIKE;
            if (BooleanUtil.toBoolean(vo.getExactParam4())) {
                operator = ModelOperator.EQ;
            }
            conditionWhere.add(OperationLog.PARAM4, vo.getParam4(), operator);
        }
        //</editor-fold>

        //<editor-fold desc="quickSearch">
        if (StringUtil.isNotEmpty(vo.getQuickSearch())) {
            ModelWhere quickSearchWhere = new ModelWhere();
            for (String key : OperationLog.QUICK_SEARCH_SUPPORT) {
                quickSearchWhere.or(key, vo.getQuickSearch(), ModelOperator.LIKE);
            }
            conditionWhere.add(quickSearchWhere);
        }
        //</editor-fold>

        //<editor-fold desc="用户过滤">
        if (StringUtil.isNotEmpty(vo.getUserId())) {
            conditionWhere.add(OperationLog.USER_ID, vo.getUserId());
        }
        if (StringUtil.isNotEmpty(vo.getUserName())) {
            conditionWhere.add(OperationLog.USER_NAME, vo.getUserName());
        }
        if (StringUtil.isNotEmpty(vo.getUserTypeCode())) {
            conditionWhere.add(OperationLog.USER_TYPE_CODE, vo.getUserTypeCode());
        }
        //</editor-fold>

        //<editor-fold desc="功能模块过滤">
        if (StringUtil.isNotEmpty(vo.getModule())) {
            conditionWhere.add(OperationLog.MODULE, vo.getModule());
        }
        if (StringUtil.isNotEmpty(vo.getStack())) {
            conditionWhere.add(OperationLog.STACK, (CommonConstant.COMMA_MARK + vo.getStack() + CommonConstant.COMMA_MARK), ModelOperator.LIKE);
        }
        if (StringUtil.isNotEmpty(vo.getType())) {
            conditionWhere.add(OperationLog.TYPE, vo.getType());
        }
        //</editor-fold>

        if (conditionWhere.hasWhere()) {
            modelWhere.add(conditionWhere);
        }

        List<OperationLog> list = operationLogRepository
                .exclude(OperationLog.COMPANY_ID, OperationLog.INPUT, OperationLog.OUTPUT)
                .where(modelWhere)
                .limit(vo.getStart() ,vo.getSize())
                .orderBy(OperationLog.RECORD_TIME, "DESC")
                .list();

        PageResult<OperationLog> pageResult = PageResult.get();
        pageResult.setList(list);
        if (ListUtil.isNotNull(list)) {
            pageResult.setCount(operationLogRepository.where(modelWhere).count());
        }
        SubTableControl.clean();
        return pageResult;
    }

    public OperationLog get(Long id, String scope, BaseUserInfo baseUserInfo) {
        return get(id, scope, baseUserInfo.companyId());
    }

        /**
         * @param id
         * @return
         */
    public OperationLog get(Long id, String scope) {
        return get(id, scope,0L);
    }

        /**
         * 获取详情
         * @param id
         * @param companyId
         * @return
         */
    public OperationLog get(Long id, String scope, Long companyId){
        SubTableControl.setSubKey(scope);
        if (!SubTableControl.checkExist()) {
            throw new CommonRuntimeException(ApiResultCode.RECORD_NOT_FOUND);
        }
        if (LongUtil.toLong(companyId) > 0L) {
            operationLogRepository.where(OperationLog.COMPANY_ID, companyId);
        }
        OperationLog operationLog = operationLogRepository
                .where(OperationLog.ID, id)
                .get();
        SubTableControl.clean();
        return operationLog;
    }

}
