package base;

public class Filter {

    public static final int EQ = 0;
    public static final int GT = 1;
    public static final int GTE = 2;
    public static final int LT = 3;
    public static final int LTE = 4;
    private int type;

    private String field;
    private Object value;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
