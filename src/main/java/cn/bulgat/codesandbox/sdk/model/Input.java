package cn.bulgat.codesandbox.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input implements Serializable {
    private String type;
    private String inputText;
    private String inputFileName;
}
