package com.Soucelo.repository;

import com.Soucelo.domain.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserRepository
{
    private final String url = "jdbc:sqlite:database.db";
    public void save(User user)
    {
        String sql = "INSERT INTO usuario (nome) VALUES (?)";
    }
}



//package org.example;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String url = "jdbc:sqlite:test.db";
//
//        String sql = """
//            CREATE TABLE IF NOT EXISTS usuario (
//                id INTEGER PRIMARY KEY AUTOINCREMENT,
//                nome TEXT NOT NULL,
//                email TEXT NOT NULL
//            );
//        """;
//
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//
//            stmt.execute(sql);
//            System.out.println("Tabela criada com sucesso!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}