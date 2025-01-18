package cn.bulgat.codesandbox.sdk.client;

import cn.bulgat.codesandbox.sdk.constant.CodeSandboxConstant;
import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequestDTO;
import cn.bulgat.codesandbox.sdk.model.Input;
import cn.bulgat.codesandbox.sdk.model.dto.ExecuteCodeRequest;
import cn.hutool.core.util.RandomUtil;
import cn.bulgat.codesandbox.sdk.model.vo.ExecuteCodeResponse;
import cn.bulgat.codesandbox.sdk.utils.SignUtils;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CodeSandboxClient {
    private final String accessKey;
    private final String secretKey;
    private final String url;
    public CodeSandboxClient(String accessKey,String secretKey,String url){
        this.accessKey=accessKey;
        this.secretKey=secretKey;
        this.url=url;
    }
    public CodeSandboxClient(String accessKey,String secretKey){
        this(accessKey,secretKey, CodeSandboxConstant.DEFAULT_REMOTE_SERVER_URL);
    }
    private List<File> getFileList(List<Object> inputObjList){
        List<File> fileList=new ArrayList<>();
        for (Object inputObj : inputObjList) {
            if (inputObj instanceof File){
                fileList.add((File) inputObj);
            } else if (inputObj instanceof Path) {
                fileList.add(((Path)inputObj).toFile());
            }
        }
        return fileList;
    }
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) throws IOException {
        ExecuteCodeRequestDTO executeCodeRequestDTO=buildRequestParams(executeCodeRequest);
        System.out.println(executeCodeRequestDTO);
        List<Object> inputObjList = executeCodeRequest.getInputList();
        List<File> fileList=getFileList(inputObjList);
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (Object fileObj : fileList) {
            RequestBody requestBody=RequestBody.create((File) fileObj,
                    MediaType.parse(ContentType.TEXT_PLAIN.getValue()));
            multipartBodyBuilder.addFormDataPart("files",((File)fileObj).getName(),requestBody);
        }
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequestDTO);
        RequestBody requestParamsBody=RequestBody.create(jsonStr,
                MediaType.parse(ContentType.JSON.getValue()));
        multipartBodyBuilder.addFormDataPart("executeCodeRequest",null,requestParamsBody);
        RequestBody requestBody = multipartBodyBuilder.build();
        Map<String, String> headersMap = buildHeaders(jsonStr);
        Headers headers=Headers.of(headersMap);
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(headers)
                .build();

        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时为10秒
                .writeTimeout(300, TimeUnit.SECONDS)     // 设置写入超时为20秒
                .readTimeout(80, TimeUnit.SECONDS)
                .build();

        Response response=client.newCall(request)
                .execute();

        if (!response.isSuccessful()){
            throw new RuntimeException("code sandbox request failed, the return code is "+response.code());
        }
        if (response.body()==null){
            throw new RuntimeException("code sandbox response body is null");
        }
        String responseBodyString = response.body().string();
        Map responseBodyMap = JSONUtil.toBean(responseBodyString, Map.class);
        Integer code = (Integer) responseBodyMap.get("code");
        if (code==null||code!=0){
            throw new RuntimeException("code sandbox invoke failed, the return code is "+code);
        }
        String executeCodeResponseJSON = responseBodyMap.get("data").toString();
        ExecuteCodeResponse executeCodeResponse=JSONUtil.toBean(executeCodeResponseJSON, ExecuteCodeResponse.class);
        if (executeCodeResponse==null){
            throw new RuntimeException("code sandbox invoke failed, the execute code response is null.");
        }
        return executeCodeResponse;
    }

    private ExecuteCodeRequestDTO buildRequestParams(ExecuteCodeRequest executeCodeRequest) {
        List<Object> inputObjList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();
        ExecuteCodeRequestDTO executeCodeRequestDTO=new ExecuteCodeRequestDTO();
        executeCodeRequestDTO.setCode(code);
        executeCodeRequestDTO.setLanguage(language);
        List<Input> inputList=new ArrayList<>();
        for (Object inputObj : inputObjList) {
            Input input=new Input();
            if (inputObj instanceof String){
                input.setType("text");
                input.setInputText(inputObj.toString());
            }else if (inputObj instanceof File){
                input.setType("file");
                input.setInputFileName(((File)inputObj).getName());
            } else if (inputObj instanceof Path) {
                input.setType("file");
                input.setInputFileName(((Path)inputObj).getFileName().toString());
            }else{
                continue;
            }
            inputList.add(input);
        }
        executeCodeRequestDTO.setInputList(inputList);
        return executeCodeRequestDTO;
    }

    private Map<String,String> buildHeaders(String params){
        Map<String,String> headers=new HashMap<>();
        headers.put(CodeSandboxConstant.AUTH_HEADER_ACCESS_KEY,accessKey);
        headers.put(CodeSandboxConstant.AUTH_HEADER_NONCE, RandomUtil.randomNumbers(4));
        headers.put(CodeSandboxConstant.AUTH_HEADER_PARAMS,params);
        headers.put(CodeSandboxConstant.AUTH_HEADER_TIMESTAMP,String.valueOf(System.currentTimeMillis()));
        headers.put(CodeSandboxConstant.AUTH_HEADER_SIGN, SignUtils.getSign(headers,secretKey));
        return headers;
    }
}
