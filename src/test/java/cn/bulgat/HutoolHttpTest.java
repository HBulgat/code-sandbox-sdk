package cn.bulgat;

import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequestByFileOrText;
import cn.bulgat.codesandbox.sdk.model.Input;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.*;

import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HutoolHttpTest {
       static String code="import java.util.HashMap;\n" +
            "import java.util.Map;\n" +
            "import java.util.Scanner;\n" +
            "\n" +
            "public class Main {\n" +
            "    public static void main(String[] args) {\n" +
            "        Scanner scanner = new Scanner(System.in);\n" +
            "        int n = scanner.nextInt();\n" +
            "        int[] nums = new int[n];\n" +
            "        int target = scanner.nextInt();\n" +
            "        for (int i = 0; i < nums.length; i++) {\n" +
            "            nums[i] = scanner.nextInt();\n" +
            "        }\n" +
            "        int[] ints = twoSum(nums, target);\n" +
            "        if (ints!= null) {\n" +
            "            System.out.println(ints[0] + \" \" + ints[1]);\n" +
            "        }\n" +
            "        scanner.close();\n" +
            "    }\n" +
            "\n" +
            "    public static int[] twoSum(int[] nums, int target) {\n" +
            "        Map<Integer, Integer> map = new HashMap<>();\n" +
            "        for (int i = 0; i < nums.length; i++) {\n" +
            "            if (map.containsKey(target - nums[i])) {\n" +
            "                return new int[]{map.get(target - nums[i]), i};\n" +
            "            }\n" +
            "            map.put(nums[i], i);\n" +
            "        }\n" +
            "        return null;\n" +
            "    }\n" +
            "}\n";
    @Test
    public void testHutool() {
        String url = "http://localhost:8077/api/execute_code/execute_code/file";
        // 创建 HttpRequest 对象并设置为 POST 方法
        HttpRequest httpRequest = HttpRequest.post(url);
        String filePath = "/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt";
        ExecuteCodeRequestByFileOrText executeCodeRequest = new ExecuteCodeRequestByFileOrText();
        List<Input> inputList = new ArrayList<>();
        inputList.add(new Input("text", "4 9\n" +
                "2 7 11 15\n", null));
        inputList.add(new Input("file", null, "2.txt"));
        executeCodeRequest.setInputList(inputList);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        System.out.println(jsonStr);
        // 手动设置 multipart/form-data 的 Content-Type 和边界
        httpRequest.contentType("multipart/form-data")
                .form("files", FileUtil.file(filePath), "2.txt")
                .form("executeCodeRequest", jsonStr);

        // 发送请求
        HttpResponse httpResponse = httpRequest.execute();
        System.out.println(httpResponse.getStatus());
        System.out.println(httpResponse.body());
    }
}
