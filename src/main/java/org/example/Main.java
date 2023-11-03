package org.example;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import au.com.bytecode.opencsv.CSVReader;
import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        DataBase.create(readForbes());
//      Таблица
        var countryList = new ArrayList<String>();
        var valueList = new ArrayList<Integer>();
        var graph = DataBase.executeQuery("SELECT country, SUM(netWorth) as sumNetWorth FROM Forbes GROUP BY country;");
        while(graph.next()){
            countryList.add(graph.getString("country"));
            valueList.add(graph.getInt("sumNetWorth"));
        }
        System.out.println("Самый молодой миллиардер из Франции, капитал которого превышает 10 млрд:");
        System.out.println(DataBase.executeQuery("SELECT * FROM Forbes WHERE country = 'France' AND netWorth > 10 ORDER BY age LIMIT 1;").getString("name"));
        System.out.println("Имя и компания бизнесмена, имеющего самый большой капитал в сфере Energy:");
        graph = DataBase.executeQuery("SELECT * FROM Forbes WHERE country = 'United States' AND industry = 'Energy ' ORDER BY netWorth DESC LIMIT 1;");
        System.out.println(graph.getString("name") + " " + graph.getString("source"));
        var histodram = new Histogram();
        histodram.createPanel(countryList, valueList);
    }

    private static ArrayList<Forbes> readForbes() throws IOException {
        var list = new ArrayList<Forbes>();
        var fileName = "src/main/resources/Forbes.csv";
        try (var readData = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(readData)) {
            reader.readNext();
            var nextLine = reader.readNext();
            while (nextLine != null) {
                list.add(new Forbes(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6]));
                nextLine = reader.readNext();
            }
        }
        return list;
    }
}
