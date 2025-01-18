package cn.bulgat;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class FileUtilTest {
    public static void main(String[] args) {
        File tempFile = FileUtil.createTempFile(".txt", true);
        System.out.println(tempFile);
        File file = FileUtil.writeString("1123123123", tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(FileUtil.readString(file,StandardCharsets.UTF_8));
    }
}
