import com.sun.javafx.image.BytePixelSetter;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class DoggyDayCare {

    public static String url;
    public static String driver;
    public static String user;
    public static String password;

    public static ResultSet rs = null;
    public static Statement stmt = null;
    public static PreparedStatement pStmt = null;
    public static Connection conn = null;
    public static String table;

    public static void main(String args[]) throws IOException, SQLException {

        Scanner sc = new Scanner(System.in);

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

        int option, subOption, subOpt2;

        do {
            System.out.println("------Main Menu:-----");
            System.out.println("\n--Entity Management--");
            System.out.println("1. Dogs");
            System.out.println("2. Owners");
            System.out.println("3. Caretakers");
            System.out.println("\n---Time Management---");
            System.out.println("4. Caretaker Clock-In");
            System.out.println("5. Dog Check-In");
            System.out.println("\n6. Exit");
            System.out.println("\nPlease enter a selection [1,2,3,4,5,6]:");
            option = sc.nextInt();

            switch (option) {
                case 1: {

                    do {
                        table = "dog";
                        System.out.println("Dogs:");
                        System.out.println("1. List All");
                        System.out.println("2. Add");
                        System.out.println("3. Change Diet");
                        System.out.println("4. Show Dietary Restrictions");
                        System.out.println("5. Back");
                        System.out.println("Please enter a selection [1,2,3,4,5]:");
                        subOption = sc.nextInt();

                        switch (subOption) {
                            case 1: {
                                showTable(table);
                                break;
                            }
                            case 2: {
                                addDog();
                                break;
                            }
                            case 3: {
                                updateDogDiet();
                                break;
                            }
                            case 4: {
                                dietaryRestrictions();
                                break;
                            }
                            case 5: {
                                System.out.println("Back to Main Menu\n");
                                break;
                            }

                            default: {
                                System.out.println("Invalid selection; please select 1-6:\n");
                                continue;
                            }
                        }
                    } while (subOption != 5);
                    break;
                }

                case 2:

                {

                    do {
                        table = "owner";
                        System.out.println("Owners:");
                        System.out.println("1. List All");
                        System.out.println("2. Add");
                        System.out.println("3. Show Dogs");
                        System.out.println("4. Update Payment Info");
                        System.out.println("5. Update Phone Number");
                        System.out.println("6. Back");
                        System.out.println("Please enter a selection [1,2,3,4,5,6]:");
                        subOption = sc.nextInt();

                        switch (subOption) {
                            case 1: {
                                showTable(table);
                                break;
                            }
                            case 2: {
                                addOwner();
                                break;
                            }
                            case 3: {
                                showDogs();
                                break;
                            }
                            case 4: {
                                updatePaymentInfo();
                                break;
                            }
                            case 5: {
                                updatePhoneNumber();
                                break;
                            }
                            case 6: {
                                System.out.println("Back to Main Menu\n");
                                break;
                            }

                            default: {
                                System.out.println("Invalid selection; please select 1-6:\n");
                                continue;
                            }
                            // option = sc.nextInt();
                        }
                    } while (subOption != 6);
                    break;
                }

                case 3:

                {

                    do {
                        table = "caretaker";
                        System.out.println("Caretakers:");
                        System.out.println("1. List All");
                        System.out.println("2. Add");
                        System.out.println("3. Back");
                        System.out.println("Please enter a selection [1,2,3]:");
                        subOption = sc.nextInt();

                        switch (subOption) {
                            case 1: {
                                showTable(table);
                                break;
                            }
                            case 2: {
                                addCaretaker();
                                break;
                            }
                            case 3: {
                                System.out.println("Back to Main Menu\n");
                                break;
                            }

                            default: {
                                System.out.println("Invalid selection; please select 1-6:\n");
                                continue;
                            }
                            // option = sc.nextInt();
                        }
                    } while (subOption != 3);
                    break;
                }

                case 4:

                {

                    do {
                        table = "clock_in";
                        System.out.println("Clock-In:");
                        System.out.println("1. Clock-in");
                        System.out.println("2. Clock-out");
                        System.out.println("3. Show clocked-in");
                        System.out.println("4. Delete clock-in");
                        System.out.println("5. Back");
                        System.out.println("Enter your option [1,2,3,4,5]:");
                        subOption = sc.nextInt();

                        switch (subOption) {
                            case 1: {
                                clockIn();
                                break;
                            }
                            case 2: {
                                clockOut();
                                break;
                            }
                            case 3: {
                                showTable(table);
                                break;
                            }
                            case 4: {
                                System.out.println("Needs implemented\n");
                                break;
                            }
                            case 5: {
                                System.out.println("Back to Main Menu\n");
                                break;
                            }

                            default: {
                                System.out.println("Invalid selection; please select 1-6:\n");
                                continue;
                            }
                            // option = sc.nextInt();
                        }
                    } while (subOption != 5);
                    break;

                }

                case 5: {

                    do {
                        table = "check_in";
                        System.out.println("Dog Check-in:");
                        System.out.println("1. Check-In");
                        System.out.println("2. Check-Out");
                        System.out.println("3. Show checked-in");
                        System.out.println("4. Record Play Time");
                        System.out.println("5. Record Feeding");
                        System.out.println("6. Delete Check-In");
                        System.out.println("7. Back");
                        System.out.println("Enter your option [1,2,3,4,5,6,7]:");
                        subOption = sc.nextInt();

                        switch (subOption) {
                            case 1: {
                                checkIn();
                                break;
                            }
                            case 2: {
                                checkOut();
                                break;
                            }
                            case 3: {
                                showTable(table);
                                break;
                            }
                            case 4: {
                                recordPlaytime();
                                break;
                            }
                            case 5: {
                                System.out.println("Needs implemented\n");
                                break;
                            }
                            case 6: {
                                System.out.println("Needs implemented\n");
                                break;
                            }
                            case 7: {
                                System.out.println("Back to Main Menu\n");
                                break;
                            }

                            default: {
                                System.out.println("Invalid selection; please select 1-6:\n");
                                continue;
                            }
                            // option = sc.nextInt();
                        }
                    } while (subOption != 7);
                    break;

                }

                case 6: {
                    System.out.println("Shutting down\n");
                    break;
                }

                default: {
                    System.out.println("Invalid selection; please select 1-6:\n");
                    continue;
                }

            }
            //Closes resources after every call
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
        } while (option != 6);
        System.exit(0); //Exits
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
        System.out.print("Enter Dog Breed: ");
        String breed = input.next();
        //System.out.print("Enter K9 ID: ");
        int k9Id = 0;
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
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Shows the dogs owned by an Owner, for which the user inputs an Owner_ID
    public static void showDogs() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Owner ID: ");
        int ownerId = input.nextInt();

        try {
            conn = DriverManager.getConnection(url, user, password);
            pStmt = conn.prepareStatement("SELECT d.name, d.age, d.breed, d.diet FROM dog d, owner o WHERE o.Owner_ID=? and d.Owner_ID = o.Owner_ID");
            pStmt.setInt(1, ownerId);
            rs = pStmt.executeQuery();

            //Identify columns and display column headers
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i=1; i <= numColumns; i++){
                System.out.print("|" + metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");

            while (rs.next()) {
                System.out.println("|" + rs.getString("name") + "\t" + "|" + rs.getInt("age") + "\t|" +
                        "|" + rs.getString("breed") + "|" + rs.getString("diet") + "\t");
            }
            pStmt.clearParameters();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Shows all the dogs that have dietary restrictions
    public static void dietaryRestrictions() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT d.name, d.age, d.breed, d.diet FROM dog d WHERE d.diet = 'Special'");

            //Identify columns and display column headers
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i=1; i <= numColumns; i++){
                System.out.print("|" + metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");

            while (rs.next()) {
                System.out.println("|" + rs.getString("name") + "\t" + "|" + rs.getInt("age") + "\t|" +
                        "|" + rs.getString("breed") + "|" + rs.getString("diet") + "\t");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Adds playtime to a dog's check-in
    public static void recordPlaytime() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter K9_ID for the dog whose playtime you would like to update: ");
        int k9_id = input.nextInt();
        System.out.print("Enter the amount of playtime: ");
        int playTime = input.nextInt();

        try {
            // Load JDBC driver
            Class.forName(driver);

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            // Create a statement and an update query
            pStmt = conn.prepareStatement("update check_in c set c.play_time = ? where c.K9_ID = ?;");
            pStmt.setInt(1,playTime);
            pStmt.setInt(2,k9_id);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success.");
            }
            pStmt.clearParameters();
            conn.commit();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    //Adds Owner to the DB by asking User for information
    public static void addOwner(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Owner Name: ");
        String name = input.nextLine();
        int ownerId = 0;
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
                System.out.println("Success\n");
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
        int empId = 0;
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
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to display contents of a table
    // Recieves table name from Menu selection
    public static void showTable(String table) throws SQLException {

        try {
            // Load JDBC driver
            Class.forName(driver);

            //Prepare the statement
            String qry;
            if (table.equals("clock_in")){
                String qryIn = "SELECT * FROM $tableName WHERE clock_out is null";
                qry = qryIn.replace("$tableName", table);
            }
            else if (table.equals("check_in")){
                String qryIn = "SELECT * FROM $tableName WHERE pickup_time is null";
                qry = qryIn.replace("$tableName", table);
            }
            else {
                String qryIn = "SELECT * FROM $tableName";
                qry = qryIn.replace("$tableName", table);
            }

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            // Create a statement and a query
            pStmt = conn.prepareStatement(qry);
            rs = pStmt.executeQuery();

            //Identify columns and display column headers
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i=1; i <= numColumns; i++){
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");

            // Display the query results
            while (rs.next()) {
                for (int i=1; i <= numColumns; i++) {
                    Object obj = rs.getObject(i);
                    if (obj != null)
                        System.out.print(rs.getObject(i).toString() + "\t");
                    else
                        System.out.print("[...]\t");
                }
                System.out.println();
            }
            System.out.println("\n");
            pStmt.clearParameters();

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    // Method to update our dog table
    // Updates any tuples where the breed is "Poodle" to the breed "Labrador"
    public static void updateDogDiet() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter K9 ID to Update: ");
        int dogID = input.nextInt();
        System.out.print("Enter S for Special Diet, N for Normal Diet: ");
        String diet = "";
        if (input.next().equalsIgnoreCase("S"))
            diet = "Special";
        else
            diet = "Normal";

        try {
            // Load JDBC driver
            Class.forName(driver);

            // Make a connection
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            // Create a statement and a query
            pStmt = conn.prepareStatement("select d.k9_id, d.name, d.diet from dog d where d.k9_id = ?");
            pStmt.setInt(1, dogID);
            rs = pStmt.executeQuery();

            // Display the query results
            System.out.println("Before update: ");
            System.out.println();
            System.out.println("K9_ID\t" + "Name\t" + "Diet\t");
            while (rs.next())
                System.out.println(rs.getInt("K9_ID") + "\t" +
                        rs.getString("name") + "\t"+ rs.getString("diet"));
            System.out.println();

            pStmt.clearParameters();
            // Create a statement and an update query
            pStmt = conn.prepareStatement("update dog d set d.diet = ? where d.k9_id = ?;");
            pStmt.setString(1,diet);
            pStmt.setInt(2,dogID);

            if(pStmt.executeUpdate() > 0){
                System.out.println("Success.");
            }
            pStmt.clearParameters();
            conn.commit();
            // Display the query results of the updated table
            pStmt = conn.prepareStatement("select d.k9_id, d.name, d.diet from dog d where d.k9_id = ?");
            pStmt.setInt(1, dogID);
            rs = pStmt.executeQuery();

            System.out.println("After update: ");
            System.out.println();

            System.out.println("K9_ID\t" + "Name\t" + "Diet\t");
            while (rs.next())
                System.out.println(rs.getInt("K9_ID") + "\t"+
                        rs.getString("name") + "\t" + rs.getString("diet"));
            System.out.println();
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
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    //Method for caretaker clock-in
    public static void clockIn(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Caretaker ID: ");
        int id = input.nextInt();
        System.out.print("Enter Room Number: ");
        String room = input.next();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("INSERT INTO clock_in VALUE(current_time(),?,?,null)");
            pStmt.setInt(1,id);
            pStmt.setString(2,room);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //Method for caretaker clock-out
    public static void clockOut(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Caretaker ID: ");
        int id = input.nextInt();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("UPDATE clock_in SET clock_out = current_time() where Employee_ID = ? AND clock_out is null");
            pStmt.setInt(1,id);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //Method for dog check-in
    public static void checkIn(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter K9 ID: ");
        int id = input.nextInt();
        System.out.print("Enter Room Number: ");
        String room = input.next();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("INSERT INTO check_in VALUE(0,?,?,null,current_timestamp(),null,null)");
            pStmt.setInt(1,id);
            pStmt.setString(2,room);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //Method for dog check-out
    public static void checkOut(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter K9 ID: ");
        int id = input.nextInt();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("UPDATE check_in SET pickup_time = current_time() where K9_ID = ? AND pickup_time is null");
            pStmt.setInt(1,id);
            if(pStmt.executeUpdate() > 0){
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
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
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updatePaymentInfo(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter OWNER ID: ");
        int id = input.nextInt();
        System.out.print("Enter New Credit Card Number: ");
        String ccNumber = input.next();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("UPDATE owner SET credit_card_nbr =? where OWNER_ID = ?");
            pStmt.setString(1, ccNumber);
            pStmt.setInt(2,id);

            if(pStmt.executeUpdate() > 0){
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void updatePhoneNumber(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter OWNER ID: ");
        int id = input.nextInt();
        System.out.print("Enter New Phone Number: ");
        String phoneNumber = input.next();
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            pStmt = conn.prepareStatement("UPDATE owner SET phone =? where OWNER_ID = ?");
            pStmt.setString(1, phoneNumber);
            pStmt.setInt(2,id);

            if(pStmt.executeUpdate() > 0){
                System.out.println("Success\n");
            }
            pStmt.clearParameters();
            conn.commit();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
