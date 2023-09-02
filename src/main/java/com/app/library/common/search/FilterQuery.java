package com.app.library.common.search;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FilterQuery {

    public static Sort sort(Map<String, String> fields) {
        List<Sort.Order> orders = new ArrayList<>();
        if (fields != null) {
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                String fieldName = entry.getKey();
                String sortType = entry.getValue();
                if (OrderType.ASC.name().equalsIgnoreCase(sortType)) {
                    orders.add(Sort.Order.asc(fieldName));
                } else if (OrderType.DESC.name().equalsIgnoreCase(sortType)) {
                    orders.add(Sort.Order.desc(fieldName));
                }
            }
        }
        return Sort.by(orders);
    }

    public static ExampleMatcher defaultMatcher() {
        return ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public static <E> Example<E> example(E model) {
        Example<E> result = null;
        if (model != null) {
            result = Example.of(model, FilterQuery.defaultMatcher());
        }
        return result;
    }

    public static <E> Example<E> example(SearchModel<E> search) {
        Example<E> result = null;
        if ((search != null) && (search.getExample() != null)) {
            result = Example.of(search.getExample(), FilterQuery.defaultMatcher());
        }
        return result;
    }

    public static <E> PageRequest pageRequest(SearchModel<E> search) {
        PageRequest result = null;
        if (search != null) {
            result = PageRequest.of(search.getIndex(), search.getSize(), FilterQuery.sort(search.getSorts()));
        }
        return result;
    }
}
