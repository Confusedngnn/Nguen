package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Forbes {
    private int rang; // ранг
    private String name; // Имя
    private double netWorth; //Чистый капитал
    private int age; //Возраст
    private String country; //Страна
    private String source; //источник дохода
    private String industry; //Индустрия

    public Forbes(String rang, String name, String netWorth, String age, String country, String source, String industry) {
        GetRang(rang);
        this.name = name;
        GetNetWorth(netWorth);
        GetAge(age);
        this.country = country;
        this.source = source;
        this.industry = industry;
    }

    public void GetRang(String rang) {
        this.rang = Integer.parseInt(rang);
    }

    public void GetNetWorth(String netWorth) {
        this.netWorth = Double.parseDouble(netWorth);
    }

    public void GetAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public void db(PreparedStatement ps) throws SQLException {
        ps.setInt(1, this.rang);
        ps.setString(2, this.name);
        ps.setDouble(3, this.netWorth);
        ps.setInt(4, this.age);
        ps.setString(5, this.country);
        ps.setString(6, this.source);
        ps.setString(7, this.industry);
        ps.executeUpdate();
    }
}
