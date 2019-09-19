import java.io.File;


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ProcessFile {
	private Formatter memberFileFormatter;
	private Scanner memberFileScanner;
	private FileWriter memberFileWriter;
	
	private String fileName;
	
	
	public  ProcessFile(String fileName){
		
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
	
	
	public void addMeal(Meal  meal){
		
		openFormatter(true);
		memberFileFormatter.format("%s %s %s\n", meal.getName2(),meal.getMeal(),meal.getExpanditure());
		memberFileFormatter.close();
			
	}
	

	public ArrayList<Meal> getMeal(){
		
		openFormatter(true);
		ArrayList<Meal> meal = new ArrayList<>();
		
		while(memberFileScanner.hasNext()){
			
			String name = memberFileScanner.next();
			String meal1 =memberFileScanner.next();;
			String Expanditure = memberFileScanner.next();
			meal.add(new Meal(name, meal1,Expanditure));	
		}		
		return meal;
	}

	public void writeMembers(ArrayList<Meal> allmeals) {
		
		openFormatter(false);
		
		for (Meal meal : allmeals) {
			memberFileFormatter.format("%s %s %s\n", meal.getName2(),meal.getMeal(),meal.getExpanditure());
			
		}
		memberFileFormatter.close();

    }
}