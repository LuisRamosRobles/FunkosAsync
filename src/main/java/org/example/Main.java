package org.example;

import utils.CSVReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CSVReader lectorCSV = new CSVReader();
        lectorCSV.start();

        try{
            lectorCSV.join();
        }catch (InterruptedException e){
            System.out.println("Error en el hilo " + Thread.currentThread().getName() + ": " + e.getMessage());
        }

    }
}