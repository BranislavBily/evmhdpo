package sample.password;

import connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordChangerJava {

    public static void changePassword(String email, String newPassword) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String query = "UPDATE users SET password = ? WHERE email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            if(preparedStatement.executeUpdate() == 1) {
                System.out.println("Updatnute heslo");
            } else {
                System.out.println("Nastala chybaa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
