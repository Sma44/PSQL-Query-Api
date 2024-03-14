import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/Student";
        String user = "postgres";
        String password = "";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                System.out.println("Connection Successful");
            }else{
                System.out.println("Connection Unsuccessful");
            }

            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t" );
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getString("enrollment_date"));
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
