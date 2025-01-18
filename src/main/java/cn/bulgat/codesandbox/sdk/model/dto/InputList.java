package cn.bulgat.codesandbox.sdk.model.dto;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class InputList extends ArrayList<Object> {
    private void valid(Object o) {
        if (!(o instanceof String||o instanceof File||o instanceof Path)){
            throw new IllegalArgumentException("Can not add this element, you have to add java.lang.String or java.io.File java.nio.file.Path.");
        }
    }
    @Override
    public boolean add(Object o) {
        valid(o);
        return super.add(o);
    }

    @Override
    public void add(int index, Object element) {
        valid(element);
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<?> c) {
        for (Object o : c) {
            valid(o);
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        for (Object o : c) {
            valid(o);
        }
        return super.addAll(index, c);
    }
}
