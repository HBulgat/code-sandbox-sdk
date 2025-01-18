package cn.bulgat.codesandbox.sdk.model.dto;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class InputList extends ArrayList<Object> {
    private static final int MAX_STRING_INPUT_LEN=256;
    private void valid(Object o) {
        if (!(o instanceof String||o instanceof File||o instanceof Path)){
            throw new IllegalArgumentException("Can not add this element, you have to add java.lang.String or java.io.File java.nio.file.Path.");
        }
    }
    @Override
    public boolean add(Object o) {
        valid(o);
//        if (o instanceof String){
//            String input=(String) o;
//            if (input.length()>MAX_STRING_INPUT_LEN){
//                File tempFile = FileUtil.createTempFile(".txt", true);
//                FileUtil.writeString(input,tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
//                return super.add(tempFile);
//            }
//        }
        return super.add(o);
    }

    @Override
    public void add(int index, Object o) {
        valid(o);
//        if (o instanceof String){
//            String input=(String) o;
//            if (input.length()>MAX_STRING_INPUT_LEN){
//                File tempFile = FileUtil.createTempFile(".txt", true);
//                FileUtil.writeString(input,tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
//                super.add(index,tempFile);
//                return;
//            }
//        }
        super.add(index, o);
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
