import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    static final String URL = "jdbc:mysql://localhost:3306/student_db";
    static final String USER = "root";
    static final String PASS = ""; // Add your MySQL password if set

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            while (true) {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Choose option: ");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1": addStudent(conn, sc); break;
                    case "2": viewStudents(conn); break;
                    case "3": searchStudent(conn, sc); break;
                    case "4": updateStudent(conn, sc); break;
                    case "5": deleteStudent(conn, sc); break;
                    case "6":
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Roll No: ");
        int roll = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        String sql = "INSERT INTO students (roll_no, name, course) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.executeUpdate();
            System.out.println("Student added successfully.");
        }
    }

    static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Student Records ---");
            System.out.println("Roll No | Name | Course");
            while (rs.next()) {
                System.out.printf("%7d | %s | %s%n",
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("course"));
            }
        }
    }

    static void searchStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Roll No to search: ");
        int roll = Integer.parseInt(sc.nextLine());

        String sql = "SELECT * FROM students WHERE roll_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("Found: %d | %s | %s%n",
                            rs.getInt("roll_no"),
                            rs.getString("name"),
                            rs.getString("course"));
                } else {
                    System.out.println("Student not found.");
                }
            }
        }
    }

    static void updateStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Roll No to update: ");
        int roll = Integer.parseInt(sc.nextLine());

        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Course: ");
        String course = sc.nextLine();

        String sql = "UPDATE students SET name = ?, course = ? WHERE roll_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, roll);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student updated successfully.");
            else
                System.out.println("Roll No not found.");
        }
    }

    static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Roll No to delete: ");
        int roll = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM students WHERE roll_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student deleted successfully.");
            else
                System.out.println("Roll No not found.");
        }
    }
}
