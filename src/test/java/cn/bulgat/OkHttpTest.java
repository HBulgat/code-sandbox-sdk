package cn.bulgat;

import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequestByFileOrText;
import cn.bulgat.codesandbox.sdk.model.Input;
import cn.hutool.json.JSONUtil;
import okhttp3.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OkHttpTest {

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
    public void test() throws IOException {
        OkHttpClient client=new OkHttpClient();
        String url="http://localhost:8077/api/execute_code/execute_code/file";
        MediaType textPlainMediaType=MediaType.parse("text/plain");
        // 添加文件参数
        File file1 = new File("/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/1.txt");
        File file2 = new File("/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt");
        RequestBody file1Body=RequestBody.create(file1,textPlainMediaType);
        RequestBody file2BOdy=RequestBody.create(file2,textPlainMediaType);
        MediaType jsonMediaType=MediaType.parse("application/json");
        ExecuteCodeRequestByFileOrText executeCodeRequest=new ExecuteCodeRequestByFileOrText();
        List<Input> inputList=new ArrayList<>();
        inputList.add(new Input("file",null,"1.txt"));
        inputList.add(new Input("file",null,"2.txt"));
        inputList.add(new Input("text","2 6\n" +
                "3 3",null));
        executeCodeRequest.setInputList(inputList);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");
        RequestBody requestBodyJSON=RequestBody.create(JSONUtil.toJsonStr(executeCodeRequest),jsonMediaType);
        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("executeCodeRequest", null,requestBodyJSON)
                .addFormDataPart("files",file1.getName(),file1Body)
                .addFormDataPart("files",file2.getName(),file2BOdy)
                .build();
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient client1=new OkHttpClient();
        Response response = client1.newCall(request).execute();
        System.out.println(response.code());
        System.out.println(response.body().string());
    }
}
