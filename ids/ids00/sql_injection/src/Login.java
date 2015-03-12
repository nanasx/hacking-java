import java.sql.*;

/**
 * Created by nastja on 11/03/15.
 */
public class Login {
    public Connection getConnection() throws Exception {
        // register the driver
        String sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);

        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sTempDb = "test.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;
        // which will produce a legitimate Url for SqlLite JDBC :

        // create a database connection
        return DriverManager.getConnection(sDbUrl);
    }

    public String hashPassword(char[] password) {
        //TODO
        return String.valueOf(password);
    }

    public void privilegedAction(String username, char[] password) throws Exception {
        Connection connection = getConnection();
        if (connection == null) {
            //TODO deal with it
        }

        try {
            String pwd = hashPassword(password);

            String sqlString = "SELECT * FROM db_user WHERE username = '" + username + "' AND password = '" + pwd + "'";

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sqlString);

//            if (!rs.next()) {
//                throw new SecurityException("Username or password incorrect");
//            } else {
                while (rs.next())
                {
                    System.out.println(rs.getString(0));
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                }

  //          }
        } finally {
            try {
                connection.close();

            } catch (SQLException e) {
                //TODO
            }
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        try {
            login.privilegedAction(args[0], args[1].toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
