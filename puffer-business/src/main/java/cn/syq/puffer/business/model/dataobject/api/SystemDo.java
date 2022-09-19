package cn.syq.puffer.business.model.dataobject.api;

import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Map;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/19 10:19
 */
public class SystemDo {

    public static final String SYSTEM_CURR_DATE_KEY = "CURR_DATE";

    public static final String SYSTEM_CURR_TIME_KEY = "CURR_TIME";

    public static final String SYSTEM_CURR_PII_KEY = "CURR_PII";

    public static final String SYSTEM_CURR_RANDOM01_KEY = "RANDOM01";


    public static final String SYSTEM_CURR_DATE_LABEL = "当前日期";

    public static final String SYSTEM_CURR_TIME_LABEL = "当前时间";

    public static final String SYSTEM_CURR_PII_LABEL = "当前流程编号";

    public static final String SYSTEM_CURR_RANDOM01_LABEL = "随机数01";


    public static final String SYSTEM_CURR_DATE_DESC = "yyyyMMdd";

    public static final String SYSTEM_CURR_TIME_DESC = "HHmmss";

    public static final String SYSTEM_CURR_PII_DESC = "当前流程编号";

    public static final String SYSTEM_CURR_RANDOM01_DESC = "随机数01";

    public static final Map<Long, String> FIELD_CLASS_TYPE = Maps.newHashMap();

    public static final Map<Long, String> FIELD_NAME = Maps.newHashMap();

    public static final Map<Long, String> FIELD_LABEL = Maps.newHashMap();

    public static final Map<Long, String> FIELD_DESC = Maps.newHashMap();

    static {
        FIELD_CLASS_TYPE.put(-1L, "String");
        FIELD_CLASS_TYPE.put(-2L, "String");
        FIELD_CLASS_TYPE.put(-3L, "Long");
        FIELD_CLASS_TYPE.put(-4L, BigDecimal.class.getName());

        FIELD_NAME.put(-1L, SYSTEM_CURR_DATE_KEY);
        FIELD_NAME.put(-2L, SYSTEM_CURR_TIME_KEY);
        FIELD_NAME.put(-3L, SYSTEM_CURR_PII_KEY);
        FIELD_NAME.put(-4L, SYSTEM_CURR_RANDOM01_KEY);

        FIELD_LABEL.put(-1L, SYSTEM_CURR_DATE_LABEL);
        FIELD_LABEL.put(-2L, SYSTEM_CURR_TIME_LABEL);
        FIELD_LABEL.put(-3L, SYSTEM_CURR_PII_LABEL);
        FIELD_LABEL.put(-4L, SYSTEM_CURR_RANDOM01_LABEL);

        FIELD_DESC.put(-1L, SYSTEM_CURR_DATE_DESC);
        FIELD_DESC.put(-2L, SYSTEM_CURR_TIME_DESC);
        FIELD_DESC.put(-3L, SYSTEM_CURR_PII_DESC);
        FIELD_DESC.put(-4L, SYSTEM_CURR_RANDOM01_DESC);
    }
}
