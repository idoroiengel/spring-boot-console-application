package org.idoroiengel.testappmvn.parser;

import org.idoroiengel.testappmvn.logger.ResultFileLogger;
import org.idoroiengel.testappmvn.model.InputNumbersJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Configuration
public class InputNumbersJsonParser {

    @Autowired
    @Qualifier("inputNumbersJson")
    private InputNumbersJson inputNumbersJson;

    @Autowired
    @Qualifier("resultFileLogger")
    private ResultFileLogger resultFileLogger;

    public void convertInputToJson(String input) {
        JsonParser jsonParser = new JacksonJsonParser();
        try {
            Map map = jsonParser.parseMap(input);
            int result = calculateInput(new InputNumbersJson(map));
            resultFileLogger.logResult(result);
        } catch (
            // when the JSON is not constructed properly
            JsonParseException |

            // when there's a negative number
            NumberFormatException |

            // when there are values missing in the JSON
            NullPointerException |

            // when the json isn't what we expected to receive
            ClassCastException
                    e)
        {
            System.out.println(e.getMessage());
        }
    }

    private int calculateInput(InputNumbersJson inputNumbersJson) {
        int result = 0;
        for(int i = 0; i < inputNumbersJson.getNumbers().length; i++){
            if(inputNumbersJson.getOperatorEnum().equals(InputNumbersJson.OperatorEnum.PLUS)){
                result += inputNumbersJson.getNumbers()[i];
            }else{
                result -= inputNumbersJson.getNumbers()[i];
            }
        }
        return result;
    }
}
