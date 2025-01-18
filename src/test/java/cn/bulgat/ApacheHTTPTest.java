//package cn.bulgat;
//
//import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequestByFileOrText;
//import cn.bulgat.codesandbox.sdk.model.Input;
//import cn.hutool.json.JSONUtil;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ApacheHTTPTest {
//       static String code="import java.util.HashMap;\n" +
//            "import java.util.Map;\n" +
//            "import java.util.Scanner;\n" +
//            "\n" +
//            "public class Main {\n" +
//            "    public static void main(String[] args) {\n" +
//            "        Scanner scanner = new Scanner(System.in);\n" +
//            "        int n = scanner.nextInt();\n" +
//            "        int[] nums = new int[n];\n" +
//            "        int target = scanner.nextInt();\n" +
//            "        for (int i = 0; i < nums.length; i++) {\n" +
//            "            nums[i] = scanner.nextInt();\n" +
//            "        }\n" +
//            "        int[] ints = twoSum(nums, target);\n" +
//            "        if (ints!= null) {\n" +
//            "            System.out.println(ints[0] + \" \" + ints[1]);\n" +
//            "        }\n" +
//            "        scanner.close();\n" +
//            "    }\n" +
//            "\n" +
//            "    public static int[] twoSum(int[] nums, int target) {\n" +
//            "        Map<Integer, Integer> map = new HashMap<>();\n" +
//            "        for (int i = 0; i < nums.length; i++) {\n" +
//            "            if (map.containsKey(target - nums[i])) {\n" +
//            "                return new int[]{map.get(target - nums[i]), i};\n" +
//            "            }\n" +
//            "            map.put(nums[i], i);\n" +
//            "        }\n" +
//            "        return null;\n" +
//            "    }\n" +
//            "}\n";
////    @Test
////    public void testApache() throws IOException {
////        ExecuteCodeRequestByFileOrText executeCodeRequest=new ExecuteCodeRequestByFileOrText();
////        List<Input> inputList=new ArrayList<>();
////        Input input1=new Input();
////        input1.setType("text");
////        input1.setInputText("4 9\n" +
////                "2 7 11 15");
////        inputList.add(input1);
////
////        Input input2=new Input();
////        input2.setType("file");
////        input2.setInputFileName("2.txt");
////
////        executeCodeRequest.setInputList(inputList);
////        executeCodeRequest.setCode(code);
////        executeCodeRequest.setLanguage("java");
////
////        String url = "http://localhost:8077/api/execute_code/test";
////        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
////        HttpPost httpPost=new HttpPost(url);
////        MultipartEntityBuilder builder=MultipartEntityBuilder.create();
////        builder.addTextBody("executeCodeRequest", JSONUtil.toJsonStr(executeCodeRequest), ContentType.APPLICATION_JSON);
////
////        String filePath="/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt";
////        builder.addBinaryBody("files",new File(filePath));
////
////        HttpEntity httpEntity=builder.build();
////
////        httpPost.setEntity(httpEntity);
////
////        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
////        System.out.println(httpResponse.getStatusLine().getStatusCode());
////        HttpEntity httpResponseEntity = httpResponse.getEntity();
////        System.out.println(httpResponseEntity);
////        httpResponse.close();
////        httpClient.close();
////    }
//       @Test
//       public void test(){
//              CloseableHttpClient httpClient= HttpClientBuilder.create().build();
//       }
//}
