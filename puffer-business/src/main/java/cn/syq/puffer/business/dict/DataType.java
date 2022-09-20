package cn.syq.puffer.business.dict;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/19 16:26
 */
public interface DataType {

    String TYPE_LIST = List.class.getName();

    String TYPE_COLLECTION = Collection.class.getName();

    String TYPE_COMPARABLE = Comparable.class.getSimpleName();

    String TYPE_STRING = String.class.getSimpleName();

    String TYPE_NUMERIC = Number.class.getSimpleName();

    String TYPE_BIGDECIMAL = BigDecimal.class.getSimpleName();

    String TYPE_DOUBLE = Double.class.getSimpleName();

    String TYPE_FLOAT = Float.class.getSimpleName();

    String TYPE_INTEGER = Integer.class.getSimpleName();

    String TYPE_LONG = Long.class.getSimpleName();

    String TYPE_SHORT = Short.class.getSimpleName();

    String TYPE_BOOLEAN = Boolean.class.getSimpleName();

    String TYPE_DATE = Date.class.getName();

    String TYPE_OBJECT = Object.class.getSimpleName();

    String TYPE_THIS = "this";

    String TYPE_VOID = "void";

    Function<String, String> FILE_CLASS_TYPE_2_INNER = (outer) -> {
        switch (outer) {
            case "1":
                return TYPE_STRING;
            case "2":
                return TYPE_BOOLEAN;
            case "3":
                return TYPE_DATE;
            case "4":
                return TYPE_DOUBLE;
            case "5":
                return TYPE_INTEGER;
            case "6":
                return TYPE_LONG;
            case "7":
                return TYPE_BIGDECIMAL;
            default:
                return "";
        }
    };

    /*Function<String, String> FILE_CLASS_TYPE_2_OUTER = (inner) -> {
        switch (inner) {
            case DataType.TYPE_STRING:
                return "1";
            case DataType.TYPE_BOOLEAN:
                return "2";
            case DataType.TYPE_DATE:
                return "3";
            case DataType.TYPE_DOUBLE:
                return "4";
            case DataType.TYPE_INTEGER:
                return "5";
            case DataType.TYPE_LONG:
                return "6";
            case DataType.TYPE_BIGDECIMAL:
                return "7";
            default:
                return "";
        }
    };*/
}
