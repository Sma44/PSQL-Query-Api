import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    static String url = "jdbc:postgresql://localhost:5432/Student";
    static String user = "postgres";
    static String password = "admin";
    static Connection connection;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try {
            // Tests connection validity
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                System.out.println("Connection Successful");
            }else{
                System.out.println("Connection Unsuccessful");
                System.exit(1);
            }
            connection.close();

            boolean flag = true;
            do{
                int userChoice;
                String fn,ln,email,date,id;
                userChoice = showMenu(in);

                switch (userChoice){
                    case 0:
                        flag = false;
                        break;
                    case 1:
                        getAllStu();
                        break;
                    case 2:
                        System.out.println("Enter first name, last name, email, and date:");
                        fn = in.nextLine();
                        ln = in.nextLine();
                        email = in.nextLine();
                        date = in.nextLine();
                        addStudent(fn,ln,email,date);
                        break;
                    case 3:
                        System.out.println("Enter student id, and email:");
                        id = in.nextLine();
                        email = in.nextLine();
                        updateEmail(id,email);

                        break;
                    case 4:
                        System.out.println("Enter student id:");
                        id = in.nextLine();
                        deleteStudent(id);
                        break;
                    default:
                        System.out.println("Error, userChoice undefined");
                        break;
                }


            }while (flag);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static int showMenu(Scanner input){
        int ret;
        System.out.print("\n\n");
        System.out.println("(1) Get All Students");
        System.out.println("(2) Add Student");
        System.out.println("(3) Update Student Email");
        System.out.println("(4) Delete Student");
        System.out.println("(0) EXIT");
        System.out.println("Enter Your Selection: ");
        ret = input.nextInt();
        input.nextLine();

        if (ret == 0) return ret;

        while (ret < 1 || ret > 4){
            System.out.println("Selection out of range. Try again: ");
            input.nextLine();
            ret = input.nextInt();
        }
        return ret;
    }

    public static void getAllStu(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();

            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t" );
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getString("enrollment_date"));
            }

            connection.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void addStudent(String first, String last, String email, String date){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" +
                    first + "', '" + last + "', '" + email + "', '" + date +"')" );

            connection.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStudent(String id){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM students WHERE student_id = " + id );

            connection.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateEmail(String id, String email){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET email = '" + email + "' WHERE student_id = " + id );

            connection.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
