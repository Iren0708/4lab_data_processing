package com.example.lab_4.console;

import com.example.lab_4.core.DataProcessor;
import com.example.lab_4.core.FileUtils;
import com.example.lab_4.models.InputArgs;
import java.util.List;

public class ConsoleApp {
    public static void main(String[] args) {
        try {
            InputArgs params = InputArgs.parse(args);
            List<String> lines1 = FileUtils.readLines(params.getInputFile1());
            List<String> lines2 = FileUtils.readLines(params.getInputFile2());
            System.out.printf("Прочитано: list1 – %d, list2 – %d%n", lines1.size(), lines2.size());

            DataProcessor processor = new DataProcessor();
            String[] result = processor.processPipeline(
                    lines1.toArray(new String[0]),
                    lines2.toArray(new String[0])
            );

            System.out.println("\nОтсортированный результат:");
            for (String s : result) System.out.println(s);

            if (params.hasOutputFile()) {
                FileUtils.writeLines(params.getOutputFile(), result);
                System.out.println("\nСохранено в " + params.getOutputFile());
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}