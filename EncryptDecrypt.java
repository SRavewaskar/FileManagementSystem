package sctpl.javaInternship.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Saurabh
 *
 * A  class that encrypts or decrypts a file.
 */
public class EncryptDecrypt {
    private static final String key = "zxcvbnmasdfghjkl";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encryptFile(File inputFile, File outputFile) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            //initialize the cipher
            
Cipher cipher = Cipher.getInstance("AES"); 
//set the mode 
cipher.init(Cipher.ENCRYPT_MODE, secretKey); 
           FileInputStream inputStream = new FileInputStream(inputFile); 
byte[] inputBytes = new byte[(int) inputFile.length()]; 
inputStream.read(inputBytes); 
//get enc file into byte 
byte[] outputBytes = cipher.doFinal(inputBytes); 
//write the bytes to the location 
FileOutputStream outputStream = new FileOutputStream(outputFile); 
outputStream.write(outputBytes); 
//close the streams
            //inputStream.flush();
  // outputStream.flush();
inputStream.close();
outputStream.close();         	
			
//show message
            System.out.print("\nFile has been encrypted successfully..!\n");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void decryptFile(File inputFile,
                                   File outputFile) {
        try {

            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            
Cipher cipher = Cipher.getInstance("AES"); 
//set the mode 
cipher.init(Cipher.DECRYPT_MODE, secretKey); 
//read the file 
FileInputStream inputStream = new FileInputStream(inputFile); 
//get enc file into byte
byte[] inputBytes = new byte[(int) inputFile.length()]; 
           
            
inputStream.read(inputBytes); 
//get enc file into byte 
byte[] outputBytes = cipher.doFinal(inputBytes); 
//write the bytes to the location 
FileOutputStream outputStream = new FileOutputStream(outputFile); 
outputStream.write(outputBytes); 
//close the streams 
            //inputStream.flush();
//outputStream.flush();
           inputStream.close();
            outputStream.close();
            
			//show message            System.out.print("\nFile has been decrypted successfully..!\n");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}