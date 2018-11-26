package utils.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidevincenzodilecce on 05/08/18.
 */
public class QueryBuilder {


    private List<Filter> filters = new ArrayList<>();


    public QueryBuilder likeAnywhere(String property, Object value) {
        this.filters.add(new Filter(property, value, Filter.LIKE_ANYWHERE));
        return this;
    }

    public QueryBuilder equal(String property, Object value) {
        this.filters.add(new Filter(property, value, Filter.EQUALS));
        return this;
    }

    public QueryBuilder addDisjunction(Disjunction disjunction) {
        this.getFilters().add(new Filter("disjunction", disjunction, Filter.DISJUNCTION));
        return this;
    }

    public QueryBuilder addConjunction(Conjunction conjunction) {
        this.getFilters().add(new Filter("conjunction", conjunction, Filter.CONJUNCTION));
        return this;
    }


    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
