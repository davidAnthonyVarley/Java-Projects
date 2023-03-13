import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordLinks {
	
	ArrayList <String> userList;
	ArrayList <String> dictList;
	boolean allWordsAreValid;
	

	public static void main(String[] args) throws IOException {
		WordLinks wl = new WordLinks();
		
		BufferedReader br = new BufferedReader(new FileReader("words.txt"));
		wl.dictList = wl.readDictionary(br);
		
		String[] localDictList = new String[wl.dictList.size()]; 
		localDictList = wl.secondReadDictionary(wl.dictList);
		
		System.out.println("Enter a comma separated list of words (or an empty list to quit):");
		
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();
		String newString = userInput.replace(" ", "");
		
		wl.userList = wl.readWordList(newString);
		
		
		String[] localUserList = new String[wl.userList.size()];
		for (int i = 0;i<wl.userList.size();i++) {
			localUserList[i] = wl.userList.get(i);
		}
		
		
		wl.isWordChain(localUserList, localDictList);
		
		
		//hey, there, you
		
//		AIS, AES, AET
//		AES
//		AET
	}
	
	 String[] secondReadDictionary(ArrayList<String> arr) throws IOException{ // maybe works
		String[] tmpdictList = new String[arr.size()];
		
		for (int i = 0;i<arr.size();i++) {
			tmpdictList[i] = arr.get(i);
		}
		
		return tmpdictList;
		
	}
	
	
	
	
	
	
	 ArrayList<String> readDictionary(BufferedReader br) throws IOException { //works
		ArrayList<String> tmpdictList = new ArrayList<String>();
		String line = br.readLine();
		
		for (int i = 0;line != null;i++) {
			tmpdictList.add(line);
			line = br.readLine();
		}
		
		return tmpdictList;
		
	}
	
	
	 ArrayList<String> readWordList(String userInput) { //works
		ArrayList<String> tmpwordList = new ArrayList<String>();
		String[] s = userInput.split(",");
		
		for (int i = 0;i<s.length;i++) {
			tmpwordList.add(s[i]);
		}
		
		return tmpwordList;
		
	}
	
	boolean isUniqueList(String[] wordList) { //works
		boolean result = true;
		
		for (int i = 0;i<wordList.length && result;i++) {
			
			for (int j = 0;j<wordList.length;j++) {
				if (i!=j) {
				if (wordList[i]==wordList[j])
					result=false;
				}
			}	
		}
		
		
		return result;
		
	}
	
	
	boolean isEnglishWord(String [] userList, String[] dictList) { //works
		boolean result = true;
		
		
		String [] tempUserList = new String [userList.length];
		for (int i = 0;i<tempUserList.length;i++) {
			tempUserList[i] = userList[i];
		}
		
		Arrays.sort(tempUserList);
		
		for (int i = 0;i<tempUserList.length && result;i++) {
			if (Arrays.binarySearch(dictList, tempUserList[i]) < 0)
				result=false;
		}
		
		return result;
		
	}
	
	boolean isDifferentByOne(String[] userList) { //works

		
		for (int i = 0;i<userList.length -1;i++) {
			String firstWord = userList[i];
			String secondWord = userList[i+1];
			
			if (firstWord.length()!=secondWord.length())
				return false;
			
			String[] f1 = firstWord.split("(?!^)");
			String[] f2 = secondWord.split("(?!^)");
			
			int numberOfDifferentChars = 0;
			for (int j = 0;j<f1.length;j++) {
				
				if ( !(f1[j].equals(f2[j]))) {
					numberOfDifferentChars++;
				}
					//System.out.println(f1[j]);
			}
			
			if (numberOfDifferentChars!=1)
				return false;

		}
		
		
		return true;
	}
	
	void isWordChain(String [] userList, String[] dictList) {
		WordLinks tempObj = new WordLinks(); 
		
		boolean result = tempObj.isUniqueList(userList);
		result = tempObj.isEnglishWord(userList, dictList);
		result = tempObj.isDifferentByOne(userList);
		
		System.out.println(result);
	}
	

}
