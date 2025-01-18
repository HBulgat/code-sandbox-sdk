package cn.bulgat;

import cn.bulgat.codesandbox.sdk.client.CodeSandboxClient;
import cn.bulgat.codesandbox.sdk.model.dto.ExecuteCodeRequest;
import cn.bulgat.codesandbox.sdk.model.dto.InputList;
import cn.bulgat.codesandbox.sdk.model.vo.ExecuteCodeResponse;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiTest {

//    @Test
//    public void testAPI(){
//        String code="import java.util.*;\n" +
//                "\n" +
//                "public class Main{\n" +
//                "    public static void main(String[] args){\n" +
//                "        Scanner scanner=new Scanner(System.in);\n" +
//                "        int a=scanner.nextInt();\n" +
//                "        int b=scanner.nextInt();\n" +
//                "        System.out.println(a+b);\n" +
//                "        scanner.close();\n" +
//                "    }\n" +
//                "}";
//        CodeSandboxClient codeSandboxClient=new CodeSandboxClient("你的accessKey","你的secretKey");
//        ExecuteCodeRequest executeCodeRequest=new ExecuteCodeRequest();
//        executeCodeRequest.setInputList(Arrays.asList("1 2","3 4"));
//        executeCodeRequest.setCode(code);
//        executeCodeRequest.setLanguage("java");
//        ExecuteCodeResponse executeCodeResponse = codeSandboxClient.executeCode(executeCodeRequest);
//        System.out.println(executeCodeResponse);
//    }
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
    public void test(){
        CodeSandboxClient client=new CodeSandboxClient("test-sdk-I6Sf3emoALlhPAx","yIQIufR42Ys1hNC8Hja9HOSrqPkAGLnO00F",
                "http://localhost:8077/api/execute/execute");
        ExecuteCodeRequest executeCodeRequest=new ExecuteCodeRequest();
        InputList inputList=new InputList();
        inputList.add("2 6\n" +
                "3 3");
        inputList.add(new File("/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/1.txt"));
        executeCodeRequest.setInputList(inputList);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");
        try {
            ExecuteCodeResponse executeCodeResponse = client.executeCode(executeCodeRequest);
            System.out.println(executeCodeResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
