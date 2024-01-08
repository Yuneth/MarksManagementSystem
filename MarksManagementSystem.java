import java.util.Arrays;
import java.util.Scanner;

public class MarksManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static int[] studentIDs = new int[100];
    private static String[] studentDetails = new String[100];
    private static int[] studentMarks = new int[100];
    private static int[] programmeFundamentalMarks = new int[100];
    private static int[] databaseManagementMarks = new int[100];
    private static int studentCount = 0;

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            // Display menu options
            System.out.println("+---------------------------------------------------------+");
            System.out.println("        | WELCOME TO GDSE MARKS MANAGEMENT SYSTEM |");
            System.out.println("+---------------------------------------------------------+");
            System.out.println("[1] Add New Student");
            System.out.println("[2] Add New Student With Marks");
            System.out.println("[3] Add Marks");
            System.out.println("[4] Update Student Details");
            System.out.println("[5] Update Marks");
            System.out.println("[6] Delete Student");
            System.out.println("[7] Print Student Details");
            System.out.println("[8] Print Student Ranks");
            System.out.println("[9] Best in Programming Fundamentals");
            System.out.println("[10] Best in Database Management System");
            System.out.println("[0] Exit");
            System.out.println("");

            int choice;
            do {
                System.out.print("Enter an Option to Continue > ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 0 && choice <= 10) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and 10.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid integer for the menu option.");
                    scanner.next();
                }
            } while (true);

            switch (choice) {
                case 1:
                    // Add New Student
                    do {
                        System.out.println("+------------------------+");
                        System.out.println("    | Add New Student |");
                        System.out.println("+------------------------+");

                        // Logic to add the student
                        int studentID = validateStudentID();

                        scanner.nextLine(); // Consume the newline

                        System.out.print("Enter Student Name: ");
                        String studentName = scanner.nextLine();

                        // Add the ID to the array
                        studentIDs[studentCount] = studentID;
                        // Add student details to the array
                        studentDetails[studentCount] = studentName;

                        studentCount++; // Increment the student count

                        // Print the success message
                        System.out.println("Student has been added successfully !!!");
                        System.out.println("");

                        System.out.print("Do you want to add a new student (Y/N): ");
                        String ch = scanner.nextLine();

                        if (!ch.equalsIgnoreCase("Y") && !ch.equalsIgnoreCase("N")) {
                            System.out.println("Invalid choice. Please enter Y or N.");
                            continue; // Go back to the beginning of the loop
                        } else if (ch.equalsIgnoreCase("N")) {
                            break; // Exit the loop if the user chooses not to add another student
                        }
                    } while (true);
                    break;

                case 2:
                    // Add New Student With Marks
                    do {
                        System.out.println("---------------------------------");
                        System.out.println(" | Add New Student With Marks |");
                        System.out.println("---------------------------------");

                        // Check if there are students with details
                        if (studentCount == 0) {
                            System.out.println("No students found. Please add students first.");
                            break;
                        }

                        // Prompt for Student ID
                        System.out.print("Enter Student ID to add marks (or enter a new ID to add a new student): ");
                        int studentIDToAddMarks = scanner.nextInt();

                        // Validate Student ID
                        int existingStudentIndex = getIndex(studentIDs, studentCount, studentIDToAddMarks);
                        if (existingStudentIndex == -1) {
                            // New Student ID, so add a new student
                            scanner.nextLine(); // Consume the newline
                            System.out.print("Enter Student Name: ");
                            String newStudentName = scanner.nextLine();

                            // Add the ID to the array
                            studentIDs[studentCount] = studentIDToAddMarks;

                            // Add the student details
                            studentDetails[studentCount] = newStudentName;

                            // Add Marks for the new student
                            System.out.print("Enter Programming Fundamentals Marks (0-100): ");
                            int programmingMarks1 = scanner.nextInt();
                            validateMarks(programmingMarks1);

                            System.out.print("Enter Database Management Systems Marks (0-100): ");
                            int dbMarks1 = scanner.nextInt();
                            validateMarks(dbMarks1);

                            // Store Marks
                            studentMarks[studentCount] = programmingMarks1 + dbMarks1;

                            programmeFundamentalMarks[studentCount] = programmingMarks1;
                            databaseManagementMarks[studentCount] = dbMarks1;

                            // Display success message
                            System.out.println("Marks added successfully for Student ID: " + studentIDToAddMarks);

                            studentCount++;

                        } else {
                            // Existing Student ID
                            // Check if marks already exist for the student
                            if (studentMarks[existingStudentIndex] != 0) {
                                System.out.println(
                                        "Marks already exist for this student. This option is only available for students whose marks haven't been given.");
                                System.out.println("Select number [4] if you want to update the marks.");
                                break;
                            }

                            // Display Student Name
                            System.out.println("Student Name: " + studentDetails[existingStudentIndex]);

                            // Add Marks for the existing student
                            System.out.print("Enter Programming Fundamentals Marks (0-100): ");
                            int programmingMarks1 = scanner.nextInt();
                            validateMarks(programmingMarks1);

                            System.out.print("Enter Database Management Systems Marks (0-100): ");
                            int dbMarks1 = scanner.nextInt();
                            validateMarks(dbMarks1);

                            // Store Marks
                            studentMarks[existingStudentIndex] = programmingMarks1 + dbMarks1;

                            // Display success message
                            System.out.println("Marks added successfully for Student ID: " + studentIDToAddMarks);
                            System.out.println("");
                        }

                        // Ask whether to add marks for another student or go back to the main menu
                        System.out.print("Do you want to add marks for another student (Y/N): ");
                        String addMoreMarksChoice = scanner.next();
                        if (!addMoreMarksChoice.equalsIgnoreCase("Y") && !addMoreMarksChoice.equalsIgnoreCase("N")) {
                            System.out.println("Invalid choice. Please enter Y or N.");
                            continue; // Go back to the beginning of the loop
                        } else if (addMoreMarksChoice.equalsIgnoreCase("N")) {
                            break; // Exit the loop if the user chooses not to continue
                        }

                    } while (true);
                    break;

                case 3:
                    // Add Marks
                    do {
                        System.out.println("-------------------------");
                        System.out.println("     | Add Marks |");
                        System.out.println("-------------------------");

                        // Check if there are students with details
                        if (studentCount == 0) {
                            System.out.println("No students found. Please add students first.");
                            break;
                        }

                        // Display existing student IDs
                        System.out.print("Existing Student IDs: ");
                        for (int id : studentIDs) {
                            if (id != 0) {
                                System.out.print(id + " ");
                            }
                        }
                        System.out.println();

                        // Prompt for Student ID
                        System.out.print("Enter Student ID to add marks: ");
                        int existingStudentID = scanner.nextInt();

                        // Validate Student ID
                        int existingStudentIndex = getIndex(studentIDs, studentCount, existingStudentID);
                        while (existingStudentIndex == -1) {
                            System.out.println("Invalid Student ID. Please enter a valid ID.");
                            System.out.print("Do you want to continue searching for another student (Y/N): ");
                            String continueSearchChoice = scanner.next();
                            if (continueSearchChoice.equalsIgnoreCase("N")) {
                                break;
                            }

                            System.out.print("Enter Student ID to add marks: ");
                            existingStudentID = scanner.nextInt();
                            existingStudentIndex = getIndex(studentIDs, studentCount, existingStudentID);
                        }

                        if (existingStudentIndex == -1) {
                            // User opted not to continue searching
                            break;
                        }

                        // Check if marks already exist for the student
                        if (studentMarks[existingStudentIndex] != 0) {
                            System.out.println(
                                    "Marks already exist for this student. This option is only available for students whose marks haven't been given.");
                            System.out.println("Select number [4] if you want to update the marks.");
                            break;
                        }

                        // Display Student Name
                        System.out.println("Student Name: " + studentDetails[existingStudentIndex]);

                        // Add Marks
                        System.out.print("Enter Programming Fundamentals Marks (0-100): ");
                        int programmingMarks1 = scanner.nextInt();
                        validateMarks(programmingMarks1);

                        System.out.print("Enter Database Management Systems Marks (0-100): ");
                        int dbMarks1 = scanner.nextInt();
                        validateMarks(dbMarks1);

                        // Store Marks
                        studentMarks[existingStudentIndex] = programmingMarks1 + dbMarks1;

                        databaseManagementMarks[existingStudentIndex] = dbMarks1;
                        programmeFundamentalMarks[existingStudentIndex] = programmingMarks1;

                        // Display success message
                        System.out.println("Marks added successfully for Student ID: " + existingStudentID);
                        System.out.println("");

                        // Ask whether to add marks for another student or go back to the main menu
                        System.out.print("Do you want to add marks for another student (Y/N): ");
                        String addMoreMarksChoice1 = scanner.next();
                        if (addMoreMarksChoice1.equalsIgnoreCase("N")) {
                            break;
                        }

                    } while (true);
                    break;

                case 4:
                    // Update Student Details
                    do {
                        System.out.println("---------------------------------");
                        System.out.println("   | Update Student Details |");
                        System.out.println("---------------------------------");

                        // Check if there are students with details
                        if (studentCount == 0) {
                            System.out.println("No students found. Please add students first.");
                            break;
                        }

                        // Display existing student IDs
                        System.out.print("Existing Student IDs: ");
                        for (int id : studentIDs) {
                            if (id != 0) {
                                System.out.print(id + " ");
                            }
                        }
                        System.out.println();

                        // Prompt for Student ID
                        System.out.print("Enter Student ID to update details: ");
                        int updateStudentID = scanner.nextInt();

                        // Validate Student ID
                        int existingStudentIndex = getIndex(studentIDs, studentCount, updateStudentID);
                        while (existingStudentIndex == -1) {
                            System.out.println("Invalid Student ID. Please enter a valid ID.");
                            System.out.print("Do you want to continue searching for another student (Y/N): ");
                            String continueSearchChoice = scanner.next();
                            if (continueSearchChoice.equalsIgnoreCase("N")) {
                                break;
                            }

                            System.out.print("Enter Student ID to update details: ");
                            updateStudentID = scanner.nextInt();
                            existingStudentIndex = getIndex(studentIDs, studentCount, updateStudentID);
                        }

                        if (existingStudentIndex == -1) {
                            // User opted not to continue searching
                            break;
                        }

                        // Display current Student Name
                        System.out.println("Current Student Name: " + studentDetails[existingStudentIndex]);

                        // Prompt for new Student Name
                        System.out.print("Enter new Student Name: ");
                        String newStudentName = scanner.next();
                        scanner.nextLine(); // Consume the newline

                        // Update Student Name
                        studentDetails[existingStudentIndex] = newStudentName;

                        // Display success message
                        System.out.println("Student details updated successfully for Student ID: " + updateStudentID);
                        System.out.println("");

                        // Ask whether to update details for another student or go back to the main menu
                        System.out.print("Do you want to update details for another student (Y/N): ");
                        String updateAnotherChoice = scanner.next();
                        if (updateAnotherChoice.equalsIgnoreCase("N")) {
                            break;
                        }
                    } while (true);
                    break;

                case 5:
                    // Update Marks
                    do {
                        System.out.println("---------------------------------");
                        System.out.println("    | Update Student Marks |");
                        System.out.println("---------------------------------");

                        // Check if there are students with details
                        if (studentCount == 0) {
                            System.out.println("No students found. Please add students first.");
                            break;
                        }

                        // Prompt for Student ID
                        System.out.print("Enter Student ID to update marks: ");
                        int updateMarksStudentID = scanner.nextInt();

                        // Validate Student ID
                        int existingStudentIndex = getIndex(studentIDs, studentCount, updateMarksStudentID);
                        while (existingStudentIndex == -1) {
                            System.out.println("Invalid Student ID. Please enter a valid ID.");
                            System.out.print("Do you want to continue searching for another student (Y/N): ");
                            String continueSearchChoice = scanner.next();
                            if (continueSearchChoice.equalsIgnoreCase("N")) {
                                break;
                            }

                            System.out.print("Enter Student ID to update marks: ");
                            updateMarksStudentID = scanner.nextInt();
                            existingStudentIndex = getIndex(studentIDs, studentCount, updateMarksStudentID);
                        }

                        if (existingStudentIndex == -1) {
                            // User opted not to continue searching
                            break;
                        }

                        // Check if marks already exist for the student
                        if (studentMarks[existingStudentIndex] == 0) {
                            System.out.println("Marks haven't been added for this student yet.");
                            break;
                        }

                        // Display current details
                        System.out.println("Current Student Name: " + studentDetails[existingStudentIndex]);
                        System.out.println("Current Programming Fundamentals Marks: "
                                + programmeFundamentalMarks[existingStudentIndex]);
                        System.out.println("Current Database Management System Marks: "
                                + databaseManagementMarks[existingStudentIndex]);

                        // Prompt for new Marks
                        System.out.print("Enter new Programming Fundamentals Marks (0-100): ");
                        int newProgrammingMarks = scanner.nextInt();
                        validateMarks(newProgrammingMarks);

                        System.out.print("Enter new Database Management Systems Marks (0-100): ");
                        int newDbMarks = scanner.nextInt();
                        validateMarks(newDbMarks);

                        // Update Marks
                        studentMarks[existingStudentIndex] = newProgrammingMarks + newDbMarks;
                        programmeFundamentalMarks[existingStudentIndex] = newProgrammingMarks;
                        databaseManagementMarks[existingStudentIndex] = newDbMarks;

                        // Display success message
                        System.out.println("Marks updated successfully for Student ID: " + updateMarksStudentID);
                        System.out.println("");

                        // Ask whether to update marks for another student or go back to the main menu
                        System.out.print("Do you want to update marks for another student (Y/N): ");
                        String updateMarksAnotherChoice = scanner.next();
                        if (updateMarksAnotherChoice.equalsIgnoreCase("N")) {
                            break;
                        }
                    } while (true);
                    break;

                case 6:
                    // Delete Student
                    do {
                        System.out.println("----------------------");
                        System.out.println("  | Delete Student |");
                        System.out.println("----------------------");

                        // Check if there are students with details
                        if (studentCount == 0) {
                            System.out.println("No students found. Please add students first.");
                            break;
                        }

                        // Prompt for Student ID
                        System.out.print("Enter Student ID to delete: ");
                        int deleteStudentID = scanner.nextInt();

                        // Validate Student ID
                        int deleteStudentIndex = getIndex(studentIDs, studentCount, deleteStudentID);
                        while (deleteStudentIndex == -1) {
                            System.out.println("Invalid Student ID. Please enter a valid ID.");
                            System.out.print("Do you want to continue searching for another student (Y/N): ");
                            String continueSearchChoice = scanner.next();
                            if (continueSearchChoice.equalsIgnoreCase("N")) {
                                break;
                            }

                            System.out.print("Enter Student ID to delete: ");
                            deleteStudentID = scanner.nextInt();
                            deleteStudentIndex = getIndex(studentIDs, studentCount, deleteStudentID);
                        }

                        if (deleteStudentIndex == -1) {
                            // User opted not to continue searching
                            break;
                        }

                        // Delete Student
                        studentIDs[deleteStudentIndex] = 0;
                        studentDetails[deleteStudentIndex] = null;
                        studentMarks[deleteStudentIndex] = 0;

                        // Display success message
                        System.out.println("Student deleted successfully for Student ID: " + deleteStudentID);
                        System.out.println("");

                        // Ask whether to delete another student or go back to the main menu
                        System.out.print("Do you want to delete another student (Y/N): ");
                        String deleteAnotherChoice = scanner.next();
                        if (deleteAnotherChoice.equalsIgnoreCase("N")) {
                            break;
                        }
                    } while (true);
                    break;

                case 7:
                    // Print Student Details
                    do {
                        System.out.println("-------------------------------");
                        System.out.println("   | View Student Details |");
                        System.out.println("-------------------------------");

                        // Check if there are students with details
                        if (studentCount == 0) {
                            System.out.println("No students found. Please add students first.");
                            break;
                        }

                        // Display existing student IDs
                        System.out.print("Existing Student IDs: ");
                        for (int id : studentIDs) {
                            if (id != 0) {
                                System.out.print(id + " ");
                            }
                        }
                        System.out.println();

                        // Prompt for Student ID
                        System.out.print("Enter Student ID to view details: ");
                        int studentIDToViewDetails = scanner.nextInt();
                        System.out.println("student id 7:" + studentIDToViewDetails);

                        // Validate Student ID
                        int existingStudentIndex = getIndex(studentIDs, studentCount, studentIDToViewDetails);
                        while (existingStudentIndex == -1) {
                            System.out.println("Invalid Student ID. Please enter a valid ID.");
                            System.out.print("Do you want to continue searching for another student (Y/N): ");
                            String continueSearchChoice = scanner.next();
                            if (continueSearchChoice.equalsIgnoreCase("N")) {
                                break;
                            }

                            System.out.print("Enter Student ID to view details: ");
                            studentIDToViewDetails = scanner.nextInt();
                            existingStudentIndex = getIndex(studentIDs, studentCount, studentIDToViewDetails);
                        }

                        if (existingStudentIndex == -1) {
                            // User opted not to continue searching
                            break;
                        }

                        // Check if marks exist for the student
                        if (studentMarks[existingStudentIndex] == 0) {
                            System.out.println("Marks yet to be added for this student.");
                            break;
                        }

                        // Display comprehensive details table
                        System.out.println("Student Details for ID: " + studentIDToViewDetails);
                        System.out.println("Name: " + studentDetails[existingStudentIndex]);

                        // Calculate total marks, average marks, and rank
                        int totalMarks = studentMarks[existingStudentIndex];
                        double averageMarks = totalMarks / 2.0; // Assuming two subjects
                        int rank = calculateRank(studentIDToViewDetails);
                        System.out.println("rank:" + rank);

                        // Display the table
                        System.out.println("+----------------------+------------------------------------+");
                        System.out.printf("| %-35s | %-20s |%n", "Programming Fundamentals Marks:",
                                programmeFundamentalMarks[existingStudentIndex]);
                        System.out.printf("| %-35s | %-20s |%n", "Database Management System Marks:",
                                databaseManagementMarks[existingStudentIndex]);
                        System.out.printf("| %-35s | %-20s |%n", "Total Marks:", totalMarks);
                        System.out.printf("| %-35s | %-20s |%n", "Average Marks:", averageMarks);
                        System.out.printf("| %-35s | %-20s |%n", "Rank:", rank + getRankSuffix(rank));
                        System.out.println("+----------------------+------------------------------------+");

                        // Display First, Second, Third, and Last rank positions
                        // System.out.println("First Rank: " + getRankDetails(studentMarks,
                        // studentCount, 1));
                        // System.out.println("Second Rank: " + getRankDetails(studentMarks,
                        // studentCount, 2));
                        // System.out.println("Third Rank: " + getRankDetails(studentMarks,
                        // studentCount, 3));
                        // System.out.println("Last Rank: " + getRankDetails(studentMarks, studentCount,
                        // studentCount));

                        // Ask whether to view details for another student or go back to the main menu
                        System.out.print("Do you want to view details for another student (Y/N): ");
                        String viewAnotherChoice = scanner.next();
                        if (viewAnotherChoice.equalsIgnoreCase("N")) {
                            break;
                        }
                    } while (true);
                    break;

                case 8:

                    displayAllStudentsWithRanks();
                    break;

                case 9:
                    displayTopProgrammingFundamentalsStudents();
                    break;

                case 10:
                    displayTopDBMSStudents();
                    break;

                case 0:
                    // Exit
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 10.");
                    break;
            }
        }

        // Close the scanner
        scanner.close();
    }

    // validate StudentID
    private static int validateStudentID() {
        int studentID;
        do {
            System.out.print("Enter Student ID: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for the student ID.");
                scanner.next();
            }
            studentID = scanner.nextInt();

            if (Arrays.binarySearch(studentIDs, 0, studentCount, studentID) >= 0) {
                System.out.println("Student ID already exists. Please enter a unique ID.");
            } else {
                break;
            }
        } while (true);

        return studentID;
    }

    // get RankSuffix
    private static String getRankSuffix(int rank) {
        if (rank >= 11 && rank <= 13) {
            return "th";
        }
        switch (rank % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    // method to validate marks
    private static void validateMarks(int marks) {
        while (marks < 0 || marks > 100) {
            System.out.println("Invalid input. Marks should be between 0 and 100.");
            System.out.print("Please enter valid marks: ");
            marks = scanner.nextInt();
        }
    }

    // method to check if an ID exists in the array
    private static boolean containsID(int[] array, int size, int id) {
        for (int i = 0; i < size; i++) {
            if (array[i] == id) {
                return true;
            }
        }
        return false;
    }

    // method to get the index of an ID in the array
    private static int getIndex(int[] array, int size, int id) {
        for (int i = 0; i < size; i++) {
            if (array[i] == id) {
                return i;
            }
        }
        return -1; // ID not found
    }

    // Case 8
    private static void displayAllStudentsWithRanks() {
        do {
            if (studentCount == 0) {
                System.out.println("No students found. Please add students first.");
                return;
            }

            System.out.println("+--------------------------------------+");
            System.out.println("         Print Student Ranks");
            System.out.println("+--------------------------------------+");
            System.out.println("");

            System.out.println("+-------------------------------------------------------------+");
            System.out.println("| Student ID | Student Name       | Total Marks | Avg. Marks |");
            System.out.println("+-------------------------------------------------------------+");

            for (int i = 0; i < studentCount; i++) {
                int studentID = studentIDs[i];
                int totalMarks = calculateTotalMarks(studentID);
                double avgMarks = calculateAverageMarks(studentID);

                // Display the student details in the table
                System.out.printf("| %-11s| %-20s| %-12s| %-11s|%n",
                        studentID, studentDetails[i], totalMarks, avgMarks);
            }

            System.out.println("+-------------------------------------------------------------+");

            // Prompt whether the user wants to stay or go back to the main menu
            System.out.println("");
            System.out.print("Do you want to stay here (Y/N): ");
            String stayChoice = scanner.next();
            if (!stayChoice.equalsIgnoreCase("Y")) {
                return;
            }
        } while (true);
    }

    private static int calculateTotalMarks(int studentID) {

        int totalMarks = 0;

        // Check if marks exist for the student
        int studentIndex = getIndex(studentIDs, studentCount, studentID);
        if (studentIndex != -1) {
            // Retrieve Programming Fundamentals marks
            int programmingMarks = programmeFundamentalMarks[studentIndex];

            // Retrieve Database Management System marks
            int dbMarks = databaseManagementMarks[studentIndex];

            // Calculate total marks
            totalMarks = programmingMarks + dbMarks;
        }

        return totalMarks;
    }

    private static double calculateAverageMarks(int studentID) {

        double averageMarks = 0.0;

        // Check if marks exist for the student
        int studentIndex = getIndex(studentIDs, studentCount, studentID);
        if (studentIndex != -1) {
            // Retrieve Programming Fundamentals marks
            int programmingMarks = programmeFundamentalMarks[studentIndex];

            // Retrieve Database Management System marks
            int dbMarks = databaseManagementMarks[studentIndex];

            // Calculate total marks
            int totalMarks = programmingMarks + dbMarks;

            // Calculate average marks
            int numberOfSubjects = 2;
            averageMarks = (double) totalMarks / numberOfSubjects;
        }

        return averageMarks;
    }

    // Case 09

    private static void displayTopProgrammingFundamentalsStudents() {
        do {
            // Sort the students based on PF Marks in descending order
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = 0; j < studentCount - i - 1; j++) {
                    if (programmeFundamentalMarks[j] < programmeFundamentalMarks[j + 1]) {
                        // Swap the students if PF Marks are in descending order
                        int tempID = studentIDs[j];
                        studentIDs[j] = studentIDs[j + 1];
                        studentIDs[j + 1] = tempID;

                        String tempName = studentDetails[j];
                        studentDetails[j] = studentDetails[j + 1];
                        studentDetails[j + 1] = tempName;

                        int tempPFMarks = programmeFundamentalMarks[j];
                        programmeFundamentalMarks[j] = programmeFundamentalMarks[j + 1];
                        programmeFundamentalMarks[j + 1] = tempPFMarks;

                        int tempDBMarks = databaseManagementMarks[j];
                        databaseManagementMarks[j] = databaseManagementMarks[j + 1];
                        databaseManagementMarks[j + 1] = tempDBMarks;

                        int tempMarks = studentMarks[j];
                        studentMarks[j] = studentMarks[j + 1];
                        studentMarks[j + 1] = tempMarks;
                    }
                }
            }

            // Display the sorted student details in the table
            System.out.println("+----------------------------------------------------------+");
            System.out.println("| Student ID | Student Name       | PF Marks | DB Marks |");
            System.out.println("+----------------------------------------------------------+");

            for (int i = 0; i < studentCount; i++) {
                int studentID = studentIDs[i];

                // Check if marks exist for the student
                if (studentMarks[i] != 0) {
                    int pfMarks = programmeFundamentalMarks[i];
                    int dbMarks = databaseManagementMarks[i];

                    // Display the student details in the table
                    System.out.printf("| %-11s| %-20s| %-9s| %-9s|%n",
                            "S" + String.format("%03d", studentID),
                            studentDetails[i], pfMarks, dbMarks);
                }
            }

            System.out.println("+----------------------------------------------------------+");

            // Prompt whether the user wants to stay or go back to the main menu
            System.out.println("");
            System.out.print("Do you want to stay here (Y/N): ");
            String stayChoice = scanner.next();
            if (!stayChoice.equalsIgnoreCase("Y")) {
                break;
            }
        } while (true);
    }

    // Case 10
    private static void displayTopDBMSStudents() {
        do {
            // Sort the students based on DBMS Marks in descending order
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = 0; j < studentCount - i - 1; j++) {
                    if (databaseManagementMarks[j] < databaseManagementMarks[j + 1]) {
                        // Swap the students if DBMS Marks are in descending order
                        int tempID = studentIDs[j];
                        studentIDs[j] = studentIDs[j + 1];
                        studentIDs[j + 1] = tempID;

                        String tempName = studentDetails[j];
                        studentDetails[j] = studentDetails[j + 1];
                        studentDetails[j + 1] = tempName;

                        int tempPFMarks = programmeFundamentalMarks[j];
                        programmeFundamentalMarks[j] = programmeFundamentalMarks[j + 1];
                        programmeFundamentalMarks[j + 1] = tempPFMarks;

                        int tempDBMarks = databaseManagementMarks[j];
                        databaseManagementMarks[j] = databaseManagementMarks[j + 1];
                        databaseManagementMarks[j + 1] = tempDBMarks;

                        int tempMarks = studentMarks[j];
                        studentMarks[j] = studentMarks[j + 1];
                        studentMarks[j + 1] = tempMarks;
                    }
                }
            }

            // Display the sorted student details in the table
            System.out.println("+----------------------------------------------------------+");
            System.out.println("| Student ID | Student Name       | PF Marks | DB Marks |");
            System.out.println("+----------------------------------------------------------+");

            for (int i = 0; i < studentCount; i++) {
                int studentID = studentIDs[i];

                // Check if marks exist for the student
                if (studentMarks[i] != 0) {
                    int pfMarks = programmeFundamentalMarks[i];
                    int dbMarks = databaseManagementMarks[i];

                    // Display the student details in the table
                    System.out.printf("| %-11s| %-20s| %-9s| %-9s|%n",
                            "S" + String.format("%03d", studentID),
                            studentDetails[i], pfMarks, dbMarks);
                }
            }

            System.out.println("+----------------------------------------------------------+");

            // Prompt whether the user wants to stay or go back to the main menu
            System.out.println("");
            System.out.print("Do you want to stay here (Y/N): ");
            String stayChoice = scanner.next();
            if (!stayChoice.equalsIgnoreCase("Y")) {
                return;
            }
        } while (true);
    }

    // calculate Rank
    private static int calculateRank(int studentID) {
        // Validate studentID
        if (studentID < 1 || studentID > databaseManagementMarks.length) {
            System.out.println("Invalid Student ID: " + studentID);
            return 0; 
        }

        // Assuming index is studentID - 1 (arrays are zero-based)
        int index = studentID - 1;

        // Get the marks for the given student
        int studentMarksValue = calculateTotalMarks(studentID);

        int rank = 1; // Default rank as 1

        // Iterate through all students and compare their marks
        for (int i = 0; i < databaseManagementMarks.length; i++) {
            // Skip the current student
            if (i != index) {
                int otherStudentMarks = calculateTotalMarks(i + 1);

                // Compare marks and update rank
                if (otherStudentMarks > studentMarksValue) {
                    rank++;
                }
            }
        }

        return rank;
    }
}