package com.example.lab_4.models;

public class InputArgs {
    private final String inputFile1;
    private final String inputFile2;
    private final String outputFile;

    public InputArgs(String inputFile1, String inputFile2, String outputFile) {
        this.inputFile1 = inputFile1;
        this.inputFile2 = inputFile2;
        this.outputFile = outputFile;
    }

    public String getInputFile1() { return inputFile1; }
    public String getInputFile2() { return inputFile2; }
    public String getOutputFile() { return outputFile; }
    public boolean hasOutputFile() { return outputFile != null && !outputFile.isEmpty(); }

    public static InputArgs parse(String[] args) {
        String f1 = null, f2 = null, out = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i1") && i + 1 < args.length) { f1 = args[++i]; }
            else if (args[i].equals("-i2") && i + 1 < args.length) { f2 = args[++i]; }
            else if (args[i].equals("-o") && i + 1 < args.length) { out = args[++i]; }
        }
        if (f1 == null || f2 == null) {
            System.err.println("Ошибка: нужно указать -i1 file1.txt -i2 file2.txt [-o out.txt]");
            System.exit(1);
        }
        return new InputArgs(f1, f2, out);
    }
}