package org.idoroiengel.testappmvn.model;

import java.util.*;


public class InputNumbersJson {

    // constants
    private static final String JSON_NUMBERS_ATTRIBUTE_NAME = "numbers";
    private static final String JSON_OPERATOR_ATTRIBUTE_NAME = "operator";

    // data
    private int[] numbers;
    private OperatorEnum operatorEnum;

    public enum OperatorEnum {
        PLUS("plus"),
        MINUS("minus");

        OperatorEnum(String operatorSign) {
            this.operatorSign = operatorSign;
        }

        private String operatorSign;

        public String getOperatorSign() {
            return operatorSign;
        }

        public static OperatorEnum getByValue(String value) {
            for (OperatorEnum operatorEnum : OperatorEnum.values()) {
                if (value.equalsIgnoreCase(operatorEnum.name())) {
                    return operatorEnum;
                }
            }
            // default value if none was inserted to avoid crashes
            return OperatorEnum.PLUS;
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public InputNumbersJson() {}

    public InputNumbersJson(Map jsonParsingMap) {
        try {
            ArrayList arrayList = (ArrayList) jsonParsingMap.get(JSON_NUMBERS_ATTRIBUTE_NAME);
            numbers = arrayList
                    .stream()
                    .mapToInt(o -> Integer.valueOf(o.toString()))
                    .peek(i -> {
                        if(Math.signum(i) < 0)
                            throw new NumberFormatException("all numbers must be positive value numbers.");
                    })
                    .toArray();
            operatorEnum = OperatorEnum.getByValue((String) jsonParsingMap.get(JSON_OPERATOR_ATTRIBUTE_NAME));
        }catch (NullPointerException e){
            throw new NullPointerException("some data is missing from your json.");
        }catch (ClassCastException e){
            throw new ClassCastException("please verify the data you entered is correct.");
        }
    }

    @Override
    public String toString() {
        return "InputNumbersJson{" +
                "numbers=" + Arrays.toString(numbers) +
                ", operatorEnum=" + operatorEnum +
                '}';
    }
}
