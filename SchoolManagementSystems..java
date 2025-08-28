import java.util.*;
import java.io.*;

public class SchoolManagementSystem {

    static String[] students = new String[100];
    static String[] teachers = new String[50];
    static int[][] subjectGrades = new int[100][10]; 
    static int studentCount = 0;
    static int teacherCount = 0;

    static final String STUDENT_FILE = "students.txt";
    static final String TEACHER_FILE = "teachers.txt";
    static final String GRADES_FILE = "grades.txt";
    static final String[] SUBJECTS = {
        "6ADPROG", "6DATAS", "7DISRU", "8MAEL1", "GENDSOC",
        "ETHICS", "STS", "PATH-Fit 2", "NSTP 2", "CCARES"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadStudentsFromFile();
        loadTeachersFromFile();
        loadGradesFromFile();

        int choice;
        do {
            System.out.println("\n===== SCHOOL MANAGEMENT SYSTEM =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Teachers");
            System.out.println("3. Manage Grades");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: studentMenu(scanner); break;
                case 2: teacherMenu(scanner); break;
                case 3: gradeMenu(scanner); break;
                case 4:
                    saveStudentsToFile();
                    saveTeachersToFile();
                    saveGradesToFile();
                    System.out.println("System exited.");
                    break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
                   // Student//
    public static void studentMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Edit Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addStudent(scanner); break;
                case 2: viewStudents(); break;
                case 3: editStudent(scanner); break;
                case 4: removeStudent(scanner); break;
            }
        } while (choice != 5);
    }

    public static void addStudent(Scanner scanner) {
        if (studentCount >= students.length) {
            System.out.println("Student limit reached.");
            return;
        }
        System.out.print("Enter student name: ");
        students[studentCount++] = scanner.nextLine();
        System.out.println("Student added.");
    }

    public static void viewStudents() {
        System.out.println("\n--- Students ---");
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }

    public static void editStudent(Scanner scanner) {
        viewStudents();
        System.out.print("Enter student number to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index < 1 || index > studentCount) {
            System.out.println("Invalid.");
            return;
        }
        System.out.print("Enter new name: ");
        students[index - 1] = scanner.nextLine();
        System.out.println("Student updated.");
    }

    public static void removeStudent(Scanner scanner) {
        viewStudents();
        System.out.print("Enter student number to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index < 1 || index > studentCount) {
            System.out.println("Invalid.");
            return;
        }
        for (int i = index - 1; i < studentCount - 1; i++) {
            students[i] = students[i + 1];
            subjectGrades[i] = subjectGrades[i + 1];
        }
        studentCount--;
        System.out.println("Student removed.");
    }

    public static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students[studentCount++] = line;
            }
        } catch (IOException e) {
            
        }
    }

    public static void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (int i = 0; i < studentCount; i++) {
                writer.write(students[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save students.");
        }
    }
                      //Teacher//
    public static void teacherMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Teacher Management ---");
            System.out.println("1. Add Teacher");
            System.out.println("2. View Teachers");
            System.out.println("3. Edit Teacher");
            System.out.println("4. Remove Teacher");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addTeacher(scanner); break;
                case 2: viewTeachers(); break;
                case 3: editTeacher(scanner); break;
                case 4: removeTeacher(scanner); break;
            }
        } while (choice != 5);
    }

    public static void addTeacher(Scanner scanner) {
        if (teacherCount >= teachers.length) {
            System.out.println("Teacher limit reached.");
            return;
        }
        System.out.print("Enter teacher name: ");
        teachers[teacherCount++] = scanner.nextLine();
        System.out.println("Teacher added.");
    }

    public static void viewTeachers() {
        System.out.println("\n--- Teachers ---");
        for (int i = 0; i < teacherCount; i++) {
            System.out.println((i + 1) + ". " + teachers[i]);
        }
    }

    public static void editTeacher(Scanner scanner) {
        viewTeachers();
        System.out.print("Enter teacher number to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index < 1 || index > teacherCount) {
            System.out.println("Invalid.");
            return;
        }
        System.out.print("Enter new name: ");
        teachers[index - 1] = scanner.nextLine();
        System.out.println("Teacher updated.");
    }

    public static void removeTeacher(Scanner scanner) {
        viewTeachers();
        System.out.print("Enter teacher number to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index < 1 || index > teacherCount) {
            System.out.println("Invalid.");
            return;
        }
        for (int i = index - 1; i < teacherCount - 1; i++) {
            teachers[i] = teachers[i + 1];
        }
        teacherCount--;
        System.out.println("Teacher removed.");
    }

    public static void loadTeachersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEACHER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                teachers[teacherCount++] = line;
            }
        } catch (IOException e) {
            
        }
    }

    public static void saveTeachersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEACHER_FILE))) {
            for (int i = 0; i < teacherCount; i++) {
                writer.write(teachers[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save teachers.");
        }
    }
                   // Grades//
    public static void gradeMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Grade Management ---");
            System.out.println("1. Assign Grade");
            System.out.println("2. View Grades");
            System.out.println("3. Back");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: assignGrade(scanner); break;
                case 2: viewGrades(); break;
            }
        } while (choice != 3);
    }

    public static void assignGrade(Scanner scanner) {
        viewStudents();
        System.out.print("Select student number: ");
        int studentIndex = scanner.nextInt();
        scanner.nextLine();
        if (studentIndex < 1 || studentIndex > studentCount) {
            System.out.println("Invalid student.");
            return;
        }

        int sIndex = studentIndex - 1;

        System.out.println("Available subjects:");
        for (int i = 0; i < SUBJECTS.length; i++) {
            System.out.println((i + 1) + ". " + SUBJECTS[i]);
        }

        System.out.print("Select subject number: ");
        int subjectIndex = scanner.nextInt();
        scanner.nextLine();
        if (subjectIndex < 1 || subjectIndex > SUBJECTS.length) {
            System.out.println("Invalid subject.");
            return;
        }

        System.out.print("Enter grade (0–100): ");
        int grade = scanner.nextInt();
        scanner.nextLine();

        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade.");
            return;
        }

        subjectGrades[sIndex][subjectIndex - 1] = grade;
        System.out.println("Grade assigned.");
    }

    public static void viewGrades() {
        System.out.println("\n--- Student Grades ---");
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". " + students[i]);
            for (int j = 0; j < SUBJECTS.length; j++) {
                int grade = subjectGrades[i][j];
                String status;
                if (grade == 0) {
                    status = "NOT ASSIGNED";
                } else {
                    status = (grade >= 75) ? "PASSED" : "FAILED";
                }
                System.out.println("  " + SUBJECTS[j] + ": " + (grade == 0 ? "N/A" : grade) + " (" + status + ")");
            }
        }
    }

    public static void saveGradesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GRADES_FILE))) {
            for (int i = 0; i < studentCount; i++) {
                for (int j = 0; j < SUBJECTS.length; j++) {
                    writer.write(subjectGrades[i][j] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save grades.");
        }
    }

    public static void loadGradesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(GRADES_FILE))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < studentCount) {
                String[] grades = line.trim().split(" ");
                for (int j = 0; j < grades.length && j < SUBJECTS.length; j++) {
                    subjectGrades[row][j] = Integer.parseInt(grades[j]);
                }
                row++;
            }
        } catch (IOException e) {
            
        }
    }
}
