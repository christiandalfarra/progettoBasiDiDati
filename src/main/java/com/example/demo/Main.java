package com.example.demo;

import java.sql.*;
import java.util.Scanner;


public class Main {
    private static boolean checkInput(String input){
        return !input.contains(";");
    }
    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking", "chris", "ciao1234");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into utente(username, password, email) value (?,?,?)");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Quanti utenti vuoi inserire: ");
            int n = scanner.nextInt();
            System.out.println(" ");
            for (int i = 0; i < n; i++) {

                System.out.print("Inserisci username");
                String username = scanner.nextLine();

                System.out.print("Inserisci password: ");
                String passwd = scanner.nextLine();

                System.out.print("Inserisci email: ");
                String email = scanner.nextLine();

                if (checkInput(username) && checkInput(passwd) && checkInput(email)){
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,passwd);
                    preparedStatement.setString(3,email);
                    preparedStatement.execute();
                }else{
                    System.out.println("possibile injection");
                }
            }
            connection.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
