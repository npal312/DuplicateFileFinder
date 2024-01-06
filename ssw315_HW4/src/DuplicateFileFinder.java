import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DuplicateFileFinder {
	
	private List<FileInfo> fileList  = new ArrayList<FileInfo>(); //MAKE LIST
	private List<List<FileInfo>> duplicateList = new ArrayList<List<FileInfo>>(); //MAKE LIST FOR DUPLICATES
	
	public DuplicateFileFinder() {
	}
	
	public class FileInfo{
		public long size;
		public String fileType; //stores file type (May not even be needed)
		public String contents; //for text files
		public byte[] binary; //for binary files
		public byte[] digested; //messagedigest stuff when I figure that out
		public String path;
		
		FileInfo(long size, String contents, String path, byte[] digested){ //add messagedigest here too
			this.size = size;
			this.contents = contents;
			this.path = path;
			this.digested = digested;
		}
		
		FileInfo(long size, byte[] binary, String path, byte[] digested){ //add messagedigest here too
			this.size = size;
			this.binary = binary;
			this.path = path;
			this.digested = digested;
		}
		
		public String getTrueSize() { //use for printing in duplicates.log instead of .size
			//have divide by 1000 for each time and also add bytes, KB, MB, etc.
			if (this.size / 1024 < 1) {
				//byte
				return this.size + " Bytes";
			}
			else if (this.size / (1024 * 1024) < 1){
				//kilobyte
				double byteSize = this.size / 1024.0;
				return Math.round(byteSize * 100.0) / 100.0 + " KB";
			}
			else if (this.size / (1024 * 1024 * 1024) < 1){
				//megabyte
				double byteSize = this.size / (1024.0 * 1024.0);
				return Math.round(byteSize * 100.0) / 100.0 + " MB";
			}
			else if (this.size / (1024 * 1024 * 1024 * 1024) < 1){
				//gigabyte
				double byteSize = this.size / (1024.0 * 1024.0 * 1024.0);
				return Math.round(byteSize * 100.0) / 100.0 + " GB";
			}
			else {
				//terabyte
				double byteSize = this.size / (1024.0 * 1024.0 * 1024.0 * 1024.0);
				return Math.round(byteSize * 100.0) / 100.0 + " TB";
			}
		}
		
		public String getDigested() {
			StringBuilder format = new StringBuilder(); //change to stringbuilder maybe
			
			for (int i = 0; i < this.digested.length; i++) {
				format.append(Integer.toHexString(0xFF & this.digested[i]));
			}
			return format.toString();
		}
	}
	
	public void getFileList(File file) throws IOException, NoSuchAlgorithmException {
		
		if (file.isDirectory()) { //if file is folder
			File[] files = file.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				getFileList(files[i]);
			}
		}
		else { //if file is file
			//where comparing and file stuff should happen
			String fileType = file.getPath().substring(file.getPath().lastIndexOf('.')).toLowerCase(); //gets file type to see if text file or binary file
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			if (fileType.equals(".txt")) {
				String contents = "";
				Scanner fileContents = new Scanner(file);
				while (fileContents.hasNext()) {
					contents += fileContents.nextLine();
				}
				fileContents.close();
				md.update(contents.getBytes());
				byte[] digest = md.digest();
				fileList.add(new FileInfo(file.length(), contents, file.getPath(), digest));
				md.reset();
			}
			
			else {
				byte[] contents = Files.readAllBytes(Paths.get(file.getPath()));
				md.update(contents);
				byte[] digest = md.digest();
				fileList.add(new FileInfo(file.length(), contents, file.getPath(), digest));
				md.reset();
			}
		}
	}
	
	public void findDuplicates() {
		for (int i = 0; i < fileList.size(); i++) {
			boolean there = false; //resets boolean
			List<FileInfo> duplicates = new ArrayList<FileInfo>(); //resets duplicate list
			FileInfo f1 = fileList.get(i); //comparison point
			for (int j = 0; j < fileList.size(); j++) {
				FileInfo f2 = fileList.get(j); //every file in fileList
				if (f1.size == f2.size && i != j) { //compares size (and messageDigest eventually) to see if same
					there = true;
					duplicates.add(fileList.get(j)); //adds to duplicate list
					fileList.remove(j); //removes from fileList so it doesn't become comparison point later on
				}
			}
			
			if (there == true) {
				duplicates.add(fileList.get(i)); //adds comparison point
				duplicateList.add(duplicates); //adds duplicate list into list of duplicate lists
			}//no need to remove comparison point because i moves ahead of it and no other duplicates of comparison point are left now
		}
		return;
	}
	
	public void logFile() throws IOException {
		PrintWriter logger = new PrintWriter("duplicates.log");
		for (int i = 0; i < duplicateList.size(); i++) {
			logger.println("#" + "\t" + duplicateList.get(i).size() + "\t" + duplicateList.get(i).get(0).getTrueSize() + "\t" + duplicateList.get(i).get(0).getDigested()); //when done
			System.out.println("#" + "\t" + duplicateList.get(i).size() + "\t" + duplicateList.get(i).get(0).getTrueSize() + "\t" + duplicateList.get(i).get(0).getDigested());
			for (int j = 0; j < duplicateList.get(i).size(); j++) {
				logger.println(duplicateList.get(i).get(j).path);
				System.out.println(duplicateList.get(i).get(j).path);
			}
			logger.println();
			System.out.println();
		}
		logger.close();
	}
	
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		System.out.println("Enter a directory: ");
		Scanner input = new Scanner(System.in);
		String startingDir = input.nextLine();
		input.close();
		
		DuplicateFileFinder dir = new DuplicateFileFinder();
		dir.getFileList(new File(startingDir));
		dir.findDuplicates();
		dir.logFile();
		
	}

}
