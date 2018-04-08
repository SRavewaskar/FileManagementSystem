package sctpl.javaInternship.filemanager;

import java.io.IOException;
import java.util.*; //used to include scanner

/**
 * @author Saurabh
 */

public class MenuMain {
    public static void main(String args[]) throws IOException {
        //declare a scanner for user Input
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        //switch the choice from user
        String strInputFile = "";
        do {
            //display our selection menu
            System.out.println("*** Welcome To File Management System ***");
            System.out.println("1. Create file");
            System.out.println("2. Rename file");
            System.out.println("3. Delete file");
            System.out.println("4. Create directory");
            System.out.println("5. Rename directory");
            System.out.println("6. Delete directory");
            System.out.println("7. View Files in a Directory");
            System.out.println("8. Copy File");
            System.out.println("9. Encrypt");
            System.out.println("10. Decrypt");
            System.out.println("11. Compress");
            System.out.println("12. Decompress");
            System.out.println("13. Exit");
            System.out.println("**********************************************");
            System.out.println("Please enter your choice:");
			
            //get user input
            choice = scanner.nextInt();
            scanner = new Scanner(System.in);
            switch (choice) {
                case 1:
                    //  prompt to get file path
                    System.out.println("Enter the file path where you want to create the file ");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    //prompt user
                    System.out.println("Enter the content :");
                    // get their input as a String
                    String strContent = scanner.nextLine();

                    FileUtility.createFile(strInputFile, strContent);
                    break;

                case 2:
                    //  prompt to get file path
                    System.out.println("Enter the file path where you want to rename the file ");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    //  prompt to get file path
                    System.out.println("Enter the new name of a file with extension ex : testing.txt");
                    // get their input as a String
                    String strNewFileName = scanner.nextLine();

                    FileUtility.reNameFile(strInputFile, strNewFileName);
                    break;

                case 3:
                    //  prompt to get file path
                    System.out.println("Enter the file path that you want to delete");
                    // get their input as a String
                    strInputFile = scanner.nextLine();

                    FileUtility.deleteFile(strInputFile);
                    break;

                case 4:
                    //  prompt to get file path
                    System.out.println("Enter the directory that you want to create");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    FileUtility.createDirectory(strInputFile);
                    break;
                case 5:
                    //  prompt to get file path
                    System.out.println("Enter the directory that you want to rename");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    //  prompt to get file path
                    System.out.println("Enter the new name(not path) of a directory  ex : Sid : ");
                    // get their input as a String
                    String strNewDirectoryName = scanner.nextLine();
                    FileUtility.reNameDirectory(strInputFile, strNewDirectoryName);
                    break;
                case 6:
                    //  prompt to get file path
                    System.out.println("Enter the directory that you want to delete");
                    // get their input as a String
                    strInputFile = scanner.nextLine();

                    FileUtility.deleteDirectory(strInputFile);
                    break;

                case 7:
                    //  prompt to get file path
                    System.out.println("Enter the directory that you want to view");
                    // get their input as a String
                    strInputFile = scanner.nextLine();

                    FileUtility.listFilesFromDirectory(strInputFile);
                    break;

                case 8:
                    //  prompt to get file path
                    System.out.println("Enter the file path that you want to copy");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    System.out.println("Enter the path where you want to paste the selected file ");
                    String strOutputPath = scanner.nextLine();
                    FileUtility.copyFile(strInputFile, strOutputPath);
                    break;
                case 9:
                    //  prompt to get file path
                    System.out.print("\nEnter the file path that you want to encrypt : ");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    FileUtility.encryptFile(strInputFile);
                    break;

                case 10:

                    //  prompt to get file path
                    System.out.print("\nEnter the file path that you want to Decrypt : ");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    FileUtility.decryptFile(strInputFile);
                    break;

                case 11:
                    //  prompt to get file path
                    System.out.print("\nEnter the file path that you want to Compress : ");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    FileUtility.compressFile(strInputFile);
                    break;

                case 12:
                    //  prompt to get file path
                    System.out.print("\nEnter the file path that you want to Decompress : ");
                    // get their input as a String
                    strInputFile = scanner.nextLine();
                    FileUtility.decompressFile(strInputFile);
                    break;

                case 13://exit
                    System.out.println("Exit....!");
                    break;
                default://default
                    System.out.println("You entered an invalid choice");
            }
        } while (choice != 13);

    }//main

}//class