package com.app.library.common.search;

import java.util.Map;

public class SearchModel<M> {

    private static final int DEFAULT_SIZE_VALUE = 10;
    private static final int DEFAULT_INDEX_VALUE = 0;
    private int index;
    private int size;
    private Map<String, String> sorts;
    private M example;

    public int getIndex() {
        if (index < 0)
            setIndex(DEFAULT_INDEX_VALUE);
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        if (size <= 0)
            setSize(DEFAULT_SIZE_VALUE);
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, String> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, String> sorts) {
        this.sorts = sorts;
    }

    public M getExample() {
        return example;
    }

    public void setExample(M example) {
        this.example = example;
    }
}
