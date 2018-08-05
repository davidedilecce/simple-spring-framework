package utils.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidevincenzodilecce on 05/08/18.
 */
public class Disjunction extends Filter {

    private List<Filter> filters = new ArrayList<>();

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
