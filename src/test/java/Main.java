import java.sql.Connection;
import java.sql.DriverManager;

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

        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
