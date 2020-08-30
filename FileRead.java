import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * 
 * @author jeffrey Callender
 *	
 */

// To use program enter a working directory in the console when prompted to do so then hit enter.

public class FileRead {
	
	public static void main(String [] args) throws Exception {
		
		// Scanner object made to read in directory
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter a directory from your computer:");
		
		// string variable dir that read in the next line from scanner object
		String dir = obj.nextLine();
		
		// Path variable named directoryPath that gets the directory provided by the user using scanner object
		Path directoryPath = Paths.get(dir);
		
		// simple printing format that tells us what directory we printing the following files and folders for
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println("Main Directory files and folders: " + directoryPath);
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
    	
    	
    	
    	// recursively call our method directoryPath that will traverse through our folder and subfolder including all its files
    	// we set the depth to 0 here.
		Directory(directoryPath, 0);
		
		// close the object scanner 
		obj.close();
	}
	
	// method name spacing that is responsible for a tab for every new level we go in our directory in regards to our folders
	// it takes in an int variable called depth and returns a string
	public static String spacing(int depth)
	{
		// declare a string builder object to be use to append tabs to every new level we encounter
		StringBuilder builder = new StringBuilder();
		
		// for loops iterates through the depth of the directory and for each new depth adds a tab for the new level accordingly
		for(int i = 0; i < depth; i++)
		{
			builder.append("\t");
			
		}
		
		// we return a string by using the toString method to convert the stringbuilder object to a string
		return builder.toString();
		
	}
	
	// Method named Directory which takes in Path variable and a int depth variable and traverse through the folder and subfolder and list all its files
	// including  the size of each file in bytes
	public static void Directory(Path path, int depth) {
		
		try {
			
			// in our try catch we declare a BasicFileAttributes object name file that will be use to help traverse the tree structure
			BasicFileAttributes files = Files.readAttributes(path, BasicFileAttributes.class);
			
			// if object file is check to be a directory we list all the files and traverse down each of those directory
			if(files.isDirectory())
			{
				DirectoryStream<Path> paths= Files.newDirectoryStream(path);
				
				// we print out the directory( folders) using getFileName as we use <> to represent that we are in a folder for the directory.
				System.out.println(spacing(depth) + "<" + path.getFileName() + ">");
				
				// for each path we are going to recursively call Directory method  to traverse all the way through all the folders and subfolders 
				// as well as listing the files within each.
				for(Path dirPath: paths)
				{
					Directory(dirPath, depth + 1);
				}
			
			}
			else
			{
				// else we print out the file name since it is not a directory.
				long result;
				result = Files.size(path);
				// we use -- to represent a file in our tree structure. we aslo list the size of each file in bytes
				System.out.println(spacing(depth) + " -- " + path.getFileName() + " size_of_file:" + result + " bytes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


