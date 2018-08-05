package utils.query;

/**
 * Created by davidevincenzodilecce on 05/08/18.
 */
public class Filter {

    public static final int LIKE_ANYWHERE = 1;
    public static final int LIKE_START = 2;
    public static final int LIKE_END = 3;
    public static final int EQUALS = 4;

    public static final int DISJUNCTION = 5;
    public static final int CONJUNCTION = 6;

    private String property;
    private Object value;
    private Integer type;


    public Filter() {
    }

    public Filter(String property, Object value, Integer type) {
        this.property = property;
        this.value = value;
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
