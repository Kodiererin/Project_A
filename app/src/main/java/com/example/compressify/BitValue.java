package com.example.compressify;

package Project_v2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BitValue {

    public static void main(String[] args) {
        computeFilePath();
    }

    public static void computeFilePath() {
//    	String filePath = "C:\\Users\\Ujjwal\\Desktop\\Algorithm_Design_Project\\Eclipse\\Algo?rithm_Design_Project\\src\\Project_v2\\Hello000.txt";
//    	String filePath = "C:\\Users\\Ujjwal\\Desktop\\Ujjwal-Files\\BackgroundLinkeIn.jpg";
        String filePath = "C:\\Users\\Ujjwal\\Desktop\\Algorithm_Design_Project\\Eclipse\\Algorithm_Design_Project\\src\\Project_v2\\Testing\\TestingFile.txt";
        TreeMap<Byte, Integer> frequencyMap = compute(filePath);
//        System.out.println(compute(filePath));
//        	System.out.println(frequencyMap);

        // Display the frequency of each byte
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            byte byteValue= entry.getKey();
            Integer frequency= entry.getValue();
            System.out.println("Byte Value : " + byteValue+ ", Frequency: " +frequency);
        }
//        compute(filePath);
    }

    public static TreeMap<Byte, Integer> compute(String filePath) {
        TreeMap<Byte, Integer> frequencyMap = new TreeMap<>();

        // Create a map to store byte frequencies
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Read the file byte by byte
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                // Update the frequency map
                frequencyMap.merge((byte) byteRead, 1, Integer::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(frequencyMap);

        // Create a frequency-wise sorted TreeMap
//        TreeMap<Integer, Byte> frequencyWise = new TreeMap<>();
//        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
//            byte byteValue = entry.getKey();
//            int frequency = entry.getValue();
//            frequencyWise.put(frequency, byteValue);
//            System.out.println(frequency+"		"+byteValue);
//        }

//        TreeMap<Integer, Byte> frequencyWise = new TreeMap<>();
//        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
//        	System.out.println(entry.getValue()+"	"+entry.getKey());
//            frequencyWise.put(entry.getValue(), entry.getKey());
//            System.out.println(frequencyWise);
//        }

        return frequencyMap;
    }
}
