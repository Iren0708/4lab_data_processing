package com.example.lab_4.core;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    // для двухсписков
    public String[] processPipeline(String[] list1, String[] list2) {
        List<Double> nums1 = convertToNumbers(list1);
        List<Double> nums2 = convertToNumbers(list2);

        List<Double> merged = new ArrayList<>();
        merged.addAll(nums1);
        merged.addAll(nums2);

        bubbleSort(merged);

        return convertToStrings(merged);
    }

    private List<Double> convertToNumbers(String[] strings) {
        List<Double> numbers = new ArrayList<>();
        for (String s : strings) {
            try {
                s = s.trim();
                if (!s.isEmpty()) numbers.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: '" + s + "' не число, пропущено");
            }
        }
        return numbers;
    }

    private void bubbleSort(List<Double> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (list.get(j) > list.get(j + 1)) {
                    Double tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
    }

    private String[] convertToStrings(List<Double> numbers) {
        String[] res = new String[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            double v = numbers.get(i);
            if (v == (long) v) res[i] = String.valueOf((long) v);
            else res[i] = String.valueOf(v);
        }
        return res;
    }
}