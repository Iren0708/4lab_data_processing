package com.example.lab_4.console;

import com.example.lab_4.core.DataProcessor;
import com.example.lab_4.core.FileUtils;
import com.example.lab_4.models.InputArgs;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConsoleApp {
    public static void main(String[] args) {
        try {
            // Шаг 3: разбор аргументов командной строки
            InputArgs params = InputArgs.parse(args);

            if (!params.isValid()) {
                System.err.println("Ошибка разбора аргументов: " + params.getErrorMessage());
                printUsage();
                System.exit(1);
            }

            // Найти файлы (с поиском в test/ если нужно)
            String file1Path = findFile(params.getInputFile1());
            String file2Path = findFile(params.getInputFile2());

            if (file1Path == null) {
                System.err.println("Файл не найден: " + params.getInputFile1());
                System.err.println("Искали в: текущей директории, test/" + params.getInputFile1());
                System.exit(1);
            }

            if (file2Path == null) {
                System.err.println("Файл не найден: " + params.getInputFile2());
                System.err.println("Искали в: текущей директории, test/" + params.getInputFile2());
                System.exit(1);
            }

            // Загрузка данных из двух файлов
            List<String> lines1 = FileUtils.readLines(file1Path);
            List<String> lines2 = FileUtils.readLines(file2Path);

            // Обработка данных через конвейер
            DataProcessor processor = new DataProcessor();
            String[] result = processor.processPipeline(
                    lines1.toArray(new String[0]),
                    lines2.toArray(new String[0])
            );

            // Вывод в консоль (только отсортированный список)
            System.out.println("\nОтсортированный результат:");
            for (String s : result) {
                System.out.println(s);
            }

            // Опциональная запись в файл
            if (params.hasOutputFile()) {
                // Создать директорию для выходного файла, если её нет
                Path outputPath = Paths.get(params.getOutputFile());
                if (outputPath.getParent() != null) {
                    Files.createDirectories(outputPath.getParent());
                }
                FileUtils.writeLines(params.getOutputFile(), result);
                System.out.println("\nРезультат сохранён в: " + params.getOutputFile());
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Поиск файла в нескольких местах:
     * 1. По указанному пути
     * 2. В папке test/ (относительно текущей директории)
     * 3. В текущей директории (если указано просто имя)
     */
    private static String findFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            return null;
        }

        Path path = Paths.get(filename);

        // 1. Прямой путь
        if (Files.exists(path)) {
            return filename;
        }

        // 2. В папке test/
        Path testPath = Paths.get("test", filename);
        if (Files.exists(testPath)) {
            return testPath.toString();
        }

        // 3. Если указан путь с test/ но файл в корне
        if (filename.startsWith("test/") || filename.startsWith("test\\")) {
            String simpleName = filename.substring(5); // убираем "test/"
            Path rootPath = Paths.get(simpleName);
            if (Files.exists(rootPath)) {
                return simpleName;
            }
        }

        return null;
    }

    private static void printUsage() {
        System.out.println("\nИспользование:");
        System.out.println("  java -jar app.jar -i1 <файл1> -i2 <файл2> [-o <файл_результата>]");
        System.out.println("\nПримеры:");
        System.out.println("  java -jar app.jar -i1 input1.txt -i2 input2.txt -o result.txt");
        System.out.println("  java -jar app.jar -i1 test/input1.txt -i2 test/input2.txt");
        System.out.println("\nПоиск файлов происходит в:");
        System.out.println("  - указанном пути");
        System.out.println("  - папке test/ (автоматически)");
    }
}