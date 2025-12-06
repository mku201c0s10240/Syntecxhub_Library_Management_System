package librarymanagementsystem;
import java.util.*;
import java.io.*;

public class Management {
	
	static final String library="library.txt";
	
	static void printHeader(String text) {
		System.out.println();
		System.out.println("=============================================");
		System.out.println(text);
		System.out.println("=============================================");
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(true){
		
		printHeader("      ...Library Management System...");
		System.out.println("\n      *****WELCOME TO ALL*****\n");
		System.out.println("1. Add Books");
		System.out.println("2. Remove Books");
		System.out.println("3. Search Books");
		System.out.println("4. Display All Books");
		System.out.println("5. Exist");
		System.out.print("Select Your Choice");
		String choice=sc.nextLine();
		switch(choice) {
		case "1":addbooks(sc);
				break;
		case "2":removebooks(sc);
				break;
		case "3":searchbooks(sc);
				break;
		case "4":displaybooks(sc);
				break;
		case "5":
			System.out.println("Existing Program");
			sc.close();
			return;
		default :
			System.out.println("Invalid choice! Please enter 1–5.");
		}
		
	}

	}
	public static void addbooks(Scanner sc){
		try {
			System.out.println("Enter Book Id");
			String id=sc.nextLine();
			System.out.println("Enter Book Name");
			String name=sc.nextLine();
			System.out.println("Enter Book Author");
			String author=sc.nextLine();
			
			if (id.isEmpty() || name.isEmpty() || author.isEmpty()) {
				System.out.println("File Cannot be Empty..");
				return;
			}
			
			BufferedWriter bw=new BufferedWriter(new FileWriter(library,true));
			bw.write(id+","+name+","+author);
			bw.newLine();
			bw.close();
			System.out.println("Book Added Successfully");
		}catch(IOException e) {
			System.out.println("Error Writing to File..");
		}
		
	}
	public static void removebooks(Scanner sc) {
		System.out.println("Enter Book Id");
		String id=sc.nextLine().trim();
		
		File file=new File(library);
		
		if(!file.exists()) {
			System.out.println("The Library File Not Exixts");
			return;
		}
		
		File tempfile=new File("tempfile.txt");
		
		boolean found=false;
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			BufferedWriter bw=new BufferedWriter(new FileWriter(tempfile));
			String line;
			
			while((line=br.readLine())!=null) {
				String[] data=line.split(",");
				if(data[0].equals(id)) {
					found=true;
					continue;
				}
				bw.write(line);
				bw.newLine();
			}
			br.close();
			bw.close();
			file.delete();
			tempfile.renameTo(file);
			if(found) {
				System.out.println("Books Removed Successfully..");
			}else {
				System.out.println("Books Not Found");
			}
			
		}catch(IOException e) {
			System.out.println("Error Processing File");
		}
		
	}
	public static void searchbooks(Scanner sc) {
		System.out.println("Enter Book Id");
		String id=sc.nextLine();
		try {
			BufferedReader br=new BufferedReader(new FileReader(library));
			String line;
			while((line=br.readLine())!=null) {
				String[] data=line.split(",");
				if(data[0].equals(id)) {
					printHeader("               ===View Books===");
					System.out.println("Book Id     :"+data[0]);
					System.out.println("Book Name   :"+data[1]);
					System.out.println("Book Author :"+data[2]);
					br.close();
					return;
				}
			}
			br.close();
			System.out.println("Books Are Not Found!..");
			
		}catch(IOException e) {
			System.out.println("Error Reading File..");
		}
		
	}
	public static void displaybooks(Scanner sc) {
		try {
			BufferedReader br=new BufferedReader(new FileReader(library));
			String line;
			System.out.println("                                  All Books");
			System.out.println("==============================================================================");
			while((line=br.readLine())!=null) {
				String[] data=line.split(",");
				System.out.printf("%-12s : %s\t","ID",data[0]);
				System.out.printf("%-12s : %s\t","| NAME",data[1]);
				System.out.printf("%-12s : %s\n","| AUTHOR",data[2]);
			}
			br.close();
			
		}catch(IOException e) {
			System.out.println("Error Reading File..");
		}
		
	}

}
