package login.decodeAni;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @Auther: yuepan
 * @Description:
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/6/13 18:16
 * ------------------------------------------------------
 */
public class DecodeParamsFormater extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jgen,
                          SerializerProvider provider)
            throws IOException {
        System.out.println("old:"+value);
        jgen.writeString("asdasdas奥术大师多");
    }
}