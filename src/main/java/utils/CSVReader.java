package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Funko;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class CSVReader extends Thread {

    @Override
    public void run(){

        try{
            Thread.sleep(0);

            String archivoCsv = System.getProperty("user.dir") + "/data/funkos.csv";

            try(BufferedReader lector = new BufferedReader(new FileReader(archivoCsv))){
                Stream<Funko> funkos = lector.lines().skip(1)
                        .map(linea -> linea.split(","))
                        .map(valores -> Funko.builder()
                                .cod(UUID.fromString(valores[0].substring(0,35)))
                                .nombre(valores[1])
                                .modelo(valores[2])
                                .precio(Double.parseDouble(valores[3]))
                                .fechaLanzamiento(LocalDate.parse(valores[4]))
                                .build()
                        );
                funkos.forEach(System.out::println);
            }catch(IOException e){
                System.err.println("Error en lectura de archivo: " + e.getMessage());
            }

        }catch (InterruptedException e){
            System.out.println("Error en el hilo " + Thread.currentThread().getName() + ": " + e.getMessage());
        }

    }
}
