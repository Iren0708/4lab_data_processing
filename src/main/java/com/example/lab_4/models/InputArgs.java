package com.example.lab_4.models;

public class InputArgs {
    private final String inputFile1;
    private final String inputFile2;
    private final String outputFile;
    private final boolean isValid;
    private final String errorMessage;

    private InputArgs(String inputFile1, String inputFile2, String outputFile,
                      boolean isValid, String errorMessage) {
        this.inputFile1 = inputFile1;
        this.inputFile2 = inputFile2;
        this.outputFile = outputFile;
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public String getInputFile1() { return inputFile1; }
    public String getInputFile2() { return inputFile2; }
    public String getOutputFile() { return outputFile; }
    public boolean hasOutputFile() { return outputFile != null && !outputFile.isEmpty(); }
    public boolean isValid() { return isValid; }
    public String getErrorMessage() { return errorMessage; }

    public static InputArgs parse(String[] args) {
        String f1 = null, f2 = null, out = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i1":
                    if (i + 1 < args.length) f1 = args[++i];
                    else return error("-i1 требует имя файла");
                    break;
                case "-i2":
                    if (i + 1 < args.length) f2 = args[++i];
                    else return error("-i2 требует имя файла");
                    break;
                case "-o":
                    if (i + 1 < args.length) out = args[++i];
                    else return error("-o требует имя файла");
                    break;
            }
        }

        if (f1 == null) return error("Не указан обязательный флаг -i1");
        if (f2 == null) return error("Не указан обязательный флаг -i2");

        return new InputArgs(f1, f2, out, true, null);
    }

    private static InputArgs error(String message) {
        return new InputArgs(null, null, null, false, message);
    }
}