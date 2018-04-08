package sctpl.javaInternship.filemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;



/**
 * @author Saurabh
 */
public class FileUtility {
    // constant variable declaration
    private static final String zipFolder = "C:\\JavaInternship\\zip folder\\";

    private static final String extractFolder = "C:\\JavaInternship\\Extracted Files";

    private static final String encryptedFileLocation = "C:\\JavaInternship\\Encrypted Files\\";

    private static final String decryptedFileLocation = "C:\\JavaInternship\\Decrypted File\\";
    ///file operations
    public static void createFile(String inputFile, String content) throws IOException {
        try {
			boolean flag = false;
            File file = new File(inputFile);
            // if file does not exists, then create it
            if (!file.exists()) {
                flag = file.createNewFile();
            }
			//get the file in File writer
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			//initialize the buffer writer with the file
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			//write the content in the file
			bufferWriter.write(content);
			//flush and close the buffer
            bufferWriter.flush();
            bufferWriter.close();

            System.out.println("File " + inputFile + " has been created successfully..!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reNameFile(String inputFile, String newFileName) {
        //get the file path in File object
        File file = new File(inputFile);
        //validate the file
        if (file.isFile()) {
            String fileDirectory = file.getParent();
            File newName = new File(fileDirectory + "\\" + newFileName);
            //perform rename and check it also
            if (file.renameTo(newName)) {
                System.out.println("File has been Renamed.");
            } else {
                System.out.println("Error in Renaming the file.");
            }
        } else {
            System.out.println("Invalid file path");
        }
    }

    public static void deleteFile(String inputFile) {
        //get the file path in File object
        File file = new File(inputFile);
        //validate the file
        if (file.isFile()) {
            try {
                //delete and check if the opertaion has completed or not
				file.delete();
				if(!file.exists()){
					System.out.println("File deleted successfully");
				}
				else
					System.out.println("Deletion unsuccessful");
                }
			catch (IOException e) {
            e.printStackTrace();
        }
		}
	}
    public static void createDirectory(String inputDirectory) {
        //get the file path in File object
        File file = new File(inputDirectory);
		boolean flag = false;
        if (file.exists()) {
            System.out.println("The directory is already present");
        } else {
			if(file.mkdirs()){
			System.out.println("Directory "+file.getName()+" created.");
			}
			else
				System.out.println("Directory creation unsuccessful");//use mkdirs() or mkdir() and check its return value
        }
	}
    public static void reNameDirectory(String inputFile, String newDirName) {
        //get the file path in File object
        File file = new File(inputFile);
        if (file.isDirectory()) {
            File newName = new File(file.getParent() + "\\" + newDirName);
            if(file.renameTo(newName)){
				System.out.println("The directory has been renamed");
			}
			else{
				System.out.println("Renaming unsuccessful");
			}//use renameTo() and check its return value 
        }
		else
			System.out.println("Invalid path");
	}
    public static void deleteDirectory(String inputDirectory) {
        //get the file path in File object
        File file = new File(inputDirectory);
		
        //check if its a directory  or not
        if (file.isDirectory ()){
			//check if the directory has childs or not
			if (file.list().length == 0) {
				file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());
			}
			else{
				Scanner sc = new Scanner(System.in);// make a scanner object to read user input
				System.out.println("The current directory is not empty\nDo you want to delete it? Enter 1:Yes 2:No"); //1 to delete the file 0 to avoid
				if(sc.next()== "1"){//check if the user wants to delete the file
					//delete files inside the directory one by one
					deleteFilesInsideDirectory(file);
					//delete parent directory
					file.delete();
				}
				else
					System.out.println("Delete directory request cancelled by user.");
			}
		}
		
		if (!file.exists()) {
		System.out.println("Directory has been deleted.");
		}else {
		System.out.println("Deletion failed");
		}
		
	}
	
	// code this method in a recursive fashion
    private static void deleteFilesInsideDirectory(File element) {
        if (element.isDirectory()) {
			if (element.listFiles().length == 0) {
				//delete directory
				element.delete();
			} else {
			//delete files one by one
			for (File sub : element.listFiles()) {
			//recurse the function if directory has the file
				deleteFilesInsideDirectory(sub);
				}
			}
		}
		//delete file
		element.delete();
    }

    public static void listFilesFromDirectory(String inputDirectory) {
        //get the file path in File object
        File directory = new File(inputDirectory);
        //check if its a directory  or not
        if (directory.isDirectory()) {
            //check if the directory has childs or not
            File lstFiles[] = directory.listFiles();
            //seperate the files and print [Folder] or [File]
            for (int i = 0; i < lstFiles.length; i++) {
			System.out.print(""+lstFiles[i].getName()+"\t");//show file names here by using file.getName()
			}
        } else {
            System.out.println("Invalid file directory");
        }
    }

	
    public static void copyFile(String inputPath, String outputPath) throws IOException {

		InputStream is = null;
        OutputStream os = null;
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath + "//" + inputFile.getName());
		if(!outputFile.exists()){
			try {
				outputFile.createNewFile();
				System.out.println("Creating destination file");
				} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(inputFile.exists()){
			if (inputFile.isFile()){
				is = new FileInputStream(inputFile);
				os = new FileOutputStream(outputFile);
				byte[] buffer = new byte[1024];
				int length;
				//write the streams to the OutputStream to copy the data
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				System.out.println("File copied successfully");
			} else{
				System.out.println("Input is not of file type");
			}
		}else {
            System.out.println("Please enter valid file path");
        }
	}
// --------------------
	
	//Phase 2 of the project : security operations
    public static void encryptFile(String inputPath)throws IOException,FileNotFoundException {
        File inputFile = new File(inputPath);
        //boolean flag = false ;
        //check if file is exist or not

        if(inputFile.exists()&& inputFile.isFile()) {
		//create directory if not present
		//Encrypted file location
            File encryptedFileLocation = new File(FileUtility.encryptedFileLocation + inputFile.getName());
            try {
			//Encrypt file
                EncryptDecrypt.encryptFile(inputFile, encryptedFileLocation);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.print("\nInvalid file path \n");
        }

    }

    public static void decryptFile(String inputPath) throws IOException,FileNotFoundException {
        File inputFile = new File(inputPath);
         
        //check if file is exist or not
        if(inputFile.exists()&& inputFile.isFile()) {
		//create directory if not present
        //Decrypted file location
            File decryptedFileLocation = new File(FileUtility.decryptedFileLocation + inputFile.getName());
			try {
        //Decrypt file
				EncryptDecrypt.decryptFile(inputFile, decryptedFileLocation);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
        //show error if file is not present
        System.out.print("\nInvalid file path \n");
      }
    } 

// --------------

    //Phase 3 of the project : compress - decompress
    public static void compressFile(String inputFile) throws IOException,FileNotFoundException {

        File file = new File(inputFile);
        String fileNameWithExtension = file.getName();
        String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));

        boolean flag = false ;
        //check if file is exist or not
        if(file.exists()){
			//create directory if not present
			file = new File(zipFolder,inputFile);
			//perform zip - logic and code explained in the pdf doc 

			//Creating Stream to compress the file        
			ZipOutputStream zip = new ZipOutputStream (new FileOutputStream (file+".zip"));
			//Inserting the file inside the stream
			ZipEntry zipEntry = new ZipEntry(fileNameWithExtension);
			zip.putNextEntry(zipEntry);
			//Writing the content
			FileInputStream fis = new FileInputStream(file);
			final int BUFFER = 2048;			
			byte buffer[] = new byte[BUFFER]; 
			int length; 
			while ((length = fis.read(buffer)) > 0) { 
				zip.write(buffer, 0, length); 	
			}
			System.out.print("\nFile has been compressed successfully..!\n");
		}
	}
		
		
    public static void decompressFile(String zipFile)throws FileNotFoundException, IOException {
        //perform un-zip - logic and code explained in the pdf doc 
		File file = new File(zipFile);
		//Create zip object and pass fileobject into it
		ZipFile zip = new ZipFile(zipFile); 
		//use enumeration for looping
		Enumeration zipFileEntries = zip.entries(); 
		// Process each entry 
		while (zipFileEntries.hasMoreElements()) { 
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry)); 
			int currentByte; 
			// establish buffer for writing file
			final int BUFFER = 2048;			
			byte data[] = new byte[BUFFER]; 
			// write the current file to disk 
			FileOutputStream fos = new FileOutputStream(extractFolder); 
			BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER); 
			// read and write until last byte is encountered 
			while ((currentByte = is.read(data, 0, BUFFER)) != -1) { 
				dest.write(data, 0, currentByte); 
			} 

		}

    }

} // end of FileUtility class

