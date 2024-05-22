package com.example.demo;

import java.sql.*;
import java.util.Scanner;


public class Main {
    private static boolean checkInput(String username, String password, String email){
        return checkString(username) && checkString(password) && checkString(email);
    }
    private static boolean checkString(String input){
        return !input.contains(";");
    }
    private static void viewUtenti(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("select username, email from utente");
        while (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            System.out.println("username: " + username + ", email: " + email);
        }
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

                if (checkInput(username, passwd, email)){
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,passwd);
                    preparedStatement.setString(3,email);
                    preparedStatement.execute();
                }else{
                    System.out.println("possibile injection");
                }
            }
            viewUtenti(connection);
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
