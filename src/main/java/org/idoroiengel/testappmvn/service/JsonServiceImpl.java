package org.idoroiengel.testappmvn.service;

import org.idoroiengel.testappmvn.interfaces.JsonService;
import org.idoroiengel.testappmvn.parser.InputNumbersJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class JsonServiceImpl implements JsonService {

    @Autowired
    private InputNumbersJsonParser inputNumbersJsonParser;

    @Override
    public void parse(String data) {
        if(inputNumbersJsonParser == null){
            inputNumbersJsonParser = new InputNumbersJsonParser();
        }
        inputNumbersJsonParser.convertInputToJson(data);
    }

}
