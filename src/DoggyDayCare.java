import java.sql.*;
import java.util.Scanner;

public class DoggyDayCare {

    public static String url;
    public static String driver;
    public static String user;
    public static String password;

    public static ResultSet rs = null;
    public static Statement stmt = null;
    public static PreparedStatement pStmt = null;
    public static Connection conn = null;

    public static void main(String[] args) {

        DoggyDayCare ddc = new DoggyDayCare();

        // read arguments from command line, assign each to a variable
        String _url = args[0];
        url = _url;

        String _driver = args[3];
        driver = _driver;

        String _user = args[1];
        user = _user;

        String _pw = args[2];
        password = _pw;

        int choice = -1;

        String menu = "\nOption \tFunction";
        menu += "\n---------------------------";
        menu += "\n1" + "\tADD DOG";
        menu += "\n2" + "\tADD OWNER";
        menu += "\n3" + "\tADD CARETAKER";
        menu += "\n4" + "\tADD CHECKIN";    //TODO
        menu += "\n5" + "\tUPDATE CHECKIN PICKUP TIME"; //TODO
        menu += "\n6" + "\tDELETE DOG"; //TODO
        menu += "\n7" + "\tDELETE OWNER";   //TODO
        menu += "\n8" + "\tDELETE CARETAKER";   //TODO
        menu += "\n9" + "\tUPDATE DOG BREED";
        menu += "\n10" + "\tQUERY 1: SELECT DOG BY BREED";
        menu += "\n11" + "\tQUERY 2: DELETE CLOCK IN";
        menu += "\n12" + "\tQUERY 3: SELECT CHECKIN BY ID";
        //Add more functions
        menu += "\n0" + "\tEXIT";
        Scanner scanner = new Scanner(System.in);
        while (choice != 0){
            System.out.println(menu);   //Displays Menu
            choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    addDog();
                    break;
                }
                case 2:{
                    addOwner();
                    break;
                }
                case 3:{
                    addCaretaker();
                    break;
                }
                case 9:{
                    updateDog();
                    break;
                }
                case 10:{
                    queryBreed();
                    break;
                }
                case 11: {
                    deleteClockIn();
                    break;
                }
                case 12:
                    queryCheckinById();
                    break;
            }

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (pStmt != null){
                    pStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.exit(0);
    }

    //Adds dog to the database by asking User for Information
    public static void addDog(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Dog Name: ");
        String name = input.next();
        System.out.print("Enter Dog Age: ");
        int age = input.nextInt();
        System.out.print("Enter Dog Gender (M for Male; F for Female): ");
        String gender = input.next();
        System.out.print("Special Diet? (Enter T for True, F for False): ");
        String diet = input.next();
        System.out.print("Enter Dog Breed");
        String breed = input.next();
        System.out.print("Enter K9 ID: ");
        int k9Id = input.nextInt();
        System.out.print("Enter Dog Owner ID: ");
        int ownerId = input.nextInt();
        System.out.print("Enter Reg-Date (YYYY-MM-DD): ");
        String regDate = input.next();

        try {
            Class.forName(driver);
            // Make a connection
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("INSERT INTO dog VALUES(?,?,?,?,?,?,?,?)");
            pStmt.setInt(1, k9Id);
            pStmt.setInt(2,ownerId);
            pStmt.setString(3, name);
            pStmt.setInt(4, age);
            pStmt.setString(5,gender);
            pStmt.setString(6,regDate);
            pStmt.setString(7,breed);
            if (diet.equals("T"))
                pStmt.setString(8,"Special");
            else
                pStmt.setString(8,"Normal");
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //Adds Owner to the DB by asking User for information
    public static void addOwner(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Owner Name: ");
        String name = input.nextLine();
        System.out.print("Enter Owner ID: ");
        int ownerId = input.nextInt();
        System.out.print("Enter Email: ");
        String email = input.next();
        System.out.print("Enter Phone Number: ");
        String phone = input.next();
        System.out.println("Enter Credit Card Number: ");
        String creditCard = input.next();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("INSERT INTO owner VALUES(?,?,?,?,?)");
            pStmt.setInt(1,ownerId);
            pStmt.setString(2,name);
            pStmt.setString(3,email);
            pStmt.setString(4,phone);
            pStmt.setString(5,creditCard);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //Adds Caretaker to the DB by asking User for Information
    public static void addCaretaker(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Caretaker Name: ");
        String name = input.nextLine();
        System.out.print("Enter Employee ID: ");
        int empId = input.nextInt();
        System.out.print("Enter hire date (YYYY-MM-DD): ");
        String hireDate = input.next();

        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("INSERT INTO caretaker VALUES(?,?,?)");
            pStmt.setInt(1,empId);
            pStmt.setString(2,name);
            pStmt.setString(3,hireDate);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to update our dog table
    // Updates any tuples where the breed is "Poodle" to the breed "Labrador"
    public static void updateDog() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Breed to Update: ");
        String updateBreed = input.next();
        System.out.print("Enter New Breed: ");
        String newBreed = input.next();

        try {
            // Load JDBC driver
            Class.forName(driver);

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            // Create a statement and a query
            pStmt = conn.prepareStatement("select d.* from dog d where d.breed = ?");
            pStmt.setString(1, newBreed);
            rs = pStmt.executeQuery();

            // Display the query results
            System.out.println("Before update: ");
            System.out.println();

            while (rs.next())
                System.out.println(rs.getInt("K9_ID") + " " + rs.getInt("Owner_ID") + " " +
                        rs.getString("name") + " " + rs.getInt("age") + " " +
                        rs.getString("gender") + " " + rs.getDate("reg_date") + " "
                + rs.getString("breed") + " " + rs.getString("diet"));
            System.out.println();

            pStmt.clearParameters();
            // Create a statement and an update query
            pStmt = conn.prepareStatement("update dog d set d.breed = ? where d.breed = ?;");
            pStmt.setString(1,newBreed);
            pStmt.setString(2,updateBreed);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success.");
            }
            pStmt.clearParameters();
            conn.commit();
            // Display the query results of the updated table
            pStmt = conn.prepareStatement("select d.* from dog d where d.breed = ?");
            pStmt.setString(1,newBreed);
            rs = pStmt.executeQuery();

            System.out.println("After update: ");
            System.out.println();

            while (rs.next())
                System.out.println(rs.getInt("K9_ID") + " " + rs.getInt("Owner_ID") + " " +
                        rs.getString("name") + " " + rs.getInt("age") + " " +
                        rs.getString("gender") + " " + rs.getDate("reg_date") + " "
                        + rs.getString("breed") + " " + rs.getString("diet"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }


    // Method that deletes a tuple from the clock_in table in the database
    public static void deleteClockIn() {
        try {
            // Load JDBC driver
            Class.forName(driver);

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);

            // Create a statement and a query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select c.* from clock_in c");

            // Display the query results
            System.out.println("Before delete: ");
            System.out.println();

            while (rs.next())
                System.out.println(rs.getDate("clock_in") + " " + rs.getInt("Employee_ID") + " " +
                        rs.getInt("Room_Number") + " " + rs.getDate("clock_out"));

            // Create a new query
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from clock_in where Employee_ID = 19975");

            // Display the query results of the updated table
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select c.* from clock_in c");

            System.out.println("After delete: ");
            System.out.println();

            while (rs.next())
                System.out.println(rs.getDate("clock_in") + " " + rs.getInt("Employee_ID") + " " +
                        rs.getInt("Room_Number") + " " + rs.getDate("clock_out"));
    }
        catch (Exception e) {
        System.out.println(e);
    }
    }

    // Method that takes an argument and queries the database
    public static void queryBreed() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Breed: ");
        String breed = input.next();
        try {
            // Load JDBC driver
            Class.forName(driver);

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);

            // Create a statement and make a query with a prepared statement
            pStmt = conn.prepareStatement("select d.K9_ID, d.name, d.breed from dog d where d.breed = ?");
            pStmt.setString(1, breed);
            rs = pStmt.executeQuery();

            // Display the results
            System.out.println("K9_ID" + "\tNAME" + "\tBREED");
            System.out.println("-----------------------------");
            while (rs.next()) {
                System.out.print(rs.getInt("K9_ID") + "\t");
                System.out.print(rs.getString("name") + "\t");
                System.out.println(rs.getString("breed" + "\t"));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    //Querys Check_In table by a check_in ID
    public static void queryCheckinById(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter CheckIn ID: ");
        int id = input.nextInt();
        try {
            // Load JDBC driver
            Class.forName(driver);

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);

            // Create a statement and make a query with a prepared statement
            pStmt = conn.prepareStatement("select d.name, c.room_number, c.drop_time, c.pickup_time FROM check_in c," +
                            "dog d WHERE c.k9_id = d.k9_id AND c.check_in_id = ?;");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();

            // Display the results
            System.out.println("DOG NAME" + "\tROOM NUMBER" + "\tDROP TIME" + "\tPICKUP TIME");
            System.out.println("----------------------------------------------------------------");
            while (rs.next()) {
                System.out.print(rs.getString("NAME") + "\t\t");
                System.out.print(rs.getInt("ROOM_NUMBER") + "\t");
                System.out.print(rs.getString("DROP_TIME")+ "\t");
                System.out.println(rs.getString("PICKUP_TIME") + "\t");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
