import java.util.ArrayList;
import java.util.Scanner;

public class AttendanceSystem {

    // Data structures
    static ArrayList<String> studentNames = new ArrayList<>();
    static ArrayList<String> studentIDs = new ArrayList<>();
    static ArrayList<ArrayList<String>> attendanceRecords = new ArrayList<>();

    static int sessionCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        // Main menu loop
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    registerStudent(input);
                    break;
                case 2:
                    markAttendance(input);
                    break;
                case 3:
                    viewAttendance(input);
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        input.close();
    }

    // Display the main menu
    static void displayMenu() {
        System.out.println("\n--- Student Attendance Management System ---");
        System.out.println("1. Register Student");
        System.out.println("2. Mark Attendance");
        System.out.println("3. View Attendance");
        System.out.println("4. Generate Report");
        System.out.println("5. Exit");
    }

    // Register a new student
    static void registerStudent(Scanner input) {
        input.nextLine(); // clear input buffer
        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter student ID: ");
        String id = input.nextLine();

        studentNames.add(name);
        studentIDs.add(id);

        // Initialize attendance record for this student
        attendanceRecords.add(new ArrayList<>());

        System.out.println("Student registered successfully.");
    }

    // Mark attendance for all registered students
    static void markAttendance(Scanner input) {
        if (studentNames.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        System.out.println("\nMarking attendance for Session " + (sessionCount + 1));

        for (int i = 0; i < studentNames.size(); i++) {
            System.out.print("Is " + studentNames.get(i) + " present? (P/A): ");
            String status = input.next().toUpperCase();
            attendanceRecords.get(i).add(status);
        }

        sessionCount++;
        System.out.println("Attendance marked successfully.");
    }

    // View attendance for a specific student
    static void viewAttendance(Scanner input) {
        input.nextLine(); // clear input buffer
        System.out.print("Enter student ID to view attendance: ");
        String id = input.nextLine();

        boolean found = false;
        for (int i = 0; i < studentIDs.size(); i++) {
            if (studentIDs.get(i).equals(id)) {
                found = true;
                System.out.println("Attendance for " + studentNames.get(i) + ":");
                for (int j = 0; j < attendanceRecords.get(i).size(); j++) {
                    System.out.println("Session " + (j + 1) + ": " + attendanceRecords.get(i).get(j));
                }
            }
        }

        if (!found) {
            System.out.println("Student ID not found.");
        }
    }

    // Generate and display attendance report
    static void generateReport() {
        if (studentNames.isEmpty() || sessionCount == 0) {
            System.out.println("No data available to generate report.");
            return;
        }

        System.out.println("\n--- Attendance Report ---");
        for (int i = 0; i < studentNames.size(); i++) {
            int presentCount = 0;
            ArrayList<String> records = attendanceRecords.get(i);

            for (String record : records) {
                if (record.equals("P")) {
                    presentCount++;
                }
            }

            double percentage = (presentCount * 100.0) / sessionCount;
            System.out.printf("%s (%s): %.2f%% Present\n", studentNames.get(i), studentIDs.get(i), percentage);
        }
    }
}

