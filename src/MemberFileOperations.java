import java.io.File;


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class MemberFileOperations {
	
	private Formatter memberFileFormatter;
	private Scanner memberFileScanner;
	private FileWriter memberFileWriter;
	
	private String fileName;
	//private Formatter memberFileFormatter;
	
	
	public  MemberFileOperations(String fileName){
		
		this.fileName = fileName;
		
	}
	
	public void openFormatter(boolean append){
		
		try{
			
			memberFileWriter = new FileWriter(fileName, append);
			memberFileFormatter = new Formatter(memberFileWriter);
			memberFileScanner = new Scanner(new File(fileName));
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "File Scanner Openning Fialed");
		}
		
	}
	
	
	public void addMembers(Member member){
		
		openFormatter(true);
		memberFileFormatter.format("%s %s \n", member.getName(),member.getPhone());
		memberFileFormatter.close();
			
	}
	

	public ArrayList<Member> getMembers(){
		
		openFormatter(true);
		ArrayList<Member> members = new ArrayList<>();
		
		while(memberFileScanner.hasNext()){
			
			String name = memberFileScanner.next();
			String Phone = memberFileScanner.next();
			members.add(new Member(name, Phone));	
		}		
		return members;
	}

	public void writeMembers(ArrayList<Member> allMembers) {
		
		openFormatter(false);
		
		for (Member member : allMembers) {
			memberFileFormatter.format("%s %s \n", member.getName(),member.getPhone());
			
		}
		memberFileFormatter.close();
	}

}

