package createBruteForceAttackFile;


// おちんぽ
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class BruteForceAttack {
	
    public static String[] passChar = {
    	
		"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", 
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "w", "z", 
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "W", "Z", 
		null
	};


	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		FileCreate_and_Rereset();
		
		int           count       = 0;
		int           length      = passChar.length;
		Long          startTime;
		List<Integer> indexList   = new ArrayList<Integer>();
		List<String>  stringList  = new ArrayList<String>();
		String        attackPass  = "";
		
		System.out.print("桁数をよこせ：");
		int endLength = scan.nextInt();
		System.out.println();
		
		startTime = System.currentTimeMillis();

		indexList.add(0);
		indexList.add(null);
		
		System.out.print("|");
		
		while (endLength > indexList.size() -2) {
			
			count++;
			
			attackPass = "";
			
			attackPass = Conversion (indexList, attackPass);
			
			for (int i = 0; i < stringList.size(); i++) {
				attackPass += stringList.get(i);
			}
			
			System.out.print(attackPass + "|");
			
			if( count % 10 == 0 ){
				System.out.print("\n|");
			}
			
			 FileWrite(attackPass);
			 
			indexList = CountUp (indexList, length);
			
		}
		
		Finish (startTime, count, attackPass);
		
	}

	static String Conversion(List<Integer> indexList, String attackPass) {
		
		for (int i = 0; i < indexList.size() -1; i++) {
			attackPass += passChar[ indexList.get(i) ] ;
		}
		
		return attackPass;
	}
	
	static List<Integer> CountUp(List<Integer> indexList, int length) {
		
		int i = 0;
		
		indexList.set(0, indexList.get(0) + 1 );
		
		while(true){
			
			int nextIndex = i + 1;
			
			if (indexList.get(i) >= length -1){
				
				if ( indexList.get(nextIndex) == null ){
					
					indexList.add(null);
					indexList.set( nextIndex , 0 );
					
				} else {
					
					indexList.set( nextIndex , indexList.get(nextIndex) + 1 );
					
				}
				
				indexList.set(i, 0);
				i++;
				
			} else {
				break;
			}
		}
		return indexList;
	}
	
	static void Finish(Long startTime, int count, String attackPass){
		
		Long endTime = System.currentTimeMillis();
		Long millTime = (long)((endTime - startTime));
		System.out.println();
		System.out.println();
		System.out.println("終了");
		System.out.println("pass:" + attackPass);
		System.out.println("attack count:" + count);
		System.out.printf("time:" + millTime + "/1000(s)");
		
	}
	
	static void FileCreate_and_Rereset(){
		
		try {
			
			File file = new File("BruteForceAttack.txt");
			String path = file.getAbsolutePath();
			System.out.println("ファイル-> " + path);
			file.createNewFile();
			
			FileWriter fr = new FileWriter(file);
			fr.write("");
			
			fr.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	static void FileWrite(String attackPass){
		
		try{
			
			File file = new File("BruteForceAttack.txt");
			
			PrintWriter pw = new PrintWriter( new FileWriter(file , true) );
			pw.println(attackPass);
			
			pw.close();
			
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
