package org.devops.mjar.live.transform.resolve.mapper;

import org.devops.mjar.live.transform.resolve.mapper.converter.BodyBodyConverter;
import org.devops.mjar.live.transform.resolve.mapper.converter.BodyQueryConverter;
import org.devops.mjar.live.transform.resolve.mapper.converter.QueryBodyConverter;
import org.devops.mjar.live.transform.resolve.mapper.converter.QueryQueryConverter;

/**
 * @author GENSEN
 * @date 2021/6/29 9:58
 * @description：参数转化方式 body 仅支持 json 转 json
 */
public enum ParamsConvertType {
    /**
     * body json 转 json
     */
    BODY_BODY(ParamsType.BODY, ParamsType.BODY) {
        @Override
        public ParamsConverter converter() {
            return new BodyBodyConverter();
        }
    },
    BODY_QUERY(ParamsType.BODY, ParamsType.QUERY) {
        /**
         * @return
         */
        @Override
        public ParamsConverter converter() {
            return new BodyQueryConverter();
        }
    },
    QUERY_BODY(ParamsType.QUERY, ParamsType.BODY) {
        @Override
        public ParamsConverter converter() {
            return new QueryBodyConverter();
        }
    },
    QUERY_QUERY(ParamsType.QUERY, ParamsType.QUERY) {
        @Override
        public ParamsConverter converter() {
            return new QueryQueryConverter();
        }
    };

    ParamsConvertType(ParamsType from, ParamsType to) {
        this.from = from;
        this.to = to;
    }

    /**
     * converter
     *
     * @return
     */
    public abstract ParamsConverter converter();

    private ParamsType from;
    private ParamsType to;

    public static ParamsConvertType find(String fromType, String toType){
        for (ParamsConvertType value : values()) {
            if (value.from.name().equalsIgnoreCase(fromType) && value.to.name().equalsIgnoreCase(toType)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find a convert of type from " + fromType + " to " + toType);
    }

}
