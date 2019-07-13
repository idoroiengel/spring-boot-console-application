package org.idoroiengel.testappmvn.logger;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ResultFileLogger {

    public void logResult(int result) {
        String str = "The result is: " + result;
        System.out.println(str);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("./result.txt"));

            writer.write(str);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            System.out.println("there was a problem with saving the result to a local file");
            System.out.println(e.getMessage());
        }
    }
}
