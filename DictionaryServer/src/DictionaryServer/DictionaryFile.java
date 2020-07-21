package DictionaryServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class DictionaryFile {
	private Hashtable<String, String> Hmap = new Hashtable<String,String>();
	
	public DictionaryFile(String path) {
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
			BufferedReader in  = new BufferedReader(inputStreamReader);
			String str;
			while ((str = in.readLine()) != null) {
				String[] input = new String[2];
				str = StringFunc0(str);
				StringTokenizer st = new StringTokenizer(str,"?");
				int i = 0;
				while(st.hasMoreElements()) {
					input[i] =st.nextToken();
					input[i] = StringFunc(input[i]);
					i++;
				}
				if(input[0] != null&&input[1] != null) {
					input[0] = input[0].trim(); 
					input[1] = input[1].trim();
					Hmap.put(input[0],input[1]);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	//remove sign except letters
	public String StringFunc(String Str) {
		Str = Pattern.compile("[\"]+").matcher(Str).replaceAll(" ");
		return Str;
	}
	//make the mark
	public String StringFunc0(String Str) {
		Str = Pattern.compile(":").matcher(Str).replaceFirst("?");
		return Str;
	}
	//Query
	public String Query(String str) {
		return Hmap.get(str);
	}
	//Add
	public String Add(String s1,String s2) {
		if(Hmap.get(s1) != null) {//find
			return null;
		}
		else {
			Hmap.put(s1, s2);
			return "yes";
		}
	}
	//modify
	public String Modify(String s1,String s2) {
		if(Hmap.get(s1) == null) {//do not find
			return null;
		}
		else {
			Hmap.remove(s1);
			Hmap.put(s1, s2);
			return "yes";
		}
	}
	//Remove
	public String Remove(String str) {
		if(Hmap.get(str) == null) {//do not find
			return null;
		}
		else {
			Hmap.remove(str);
			return "yes";
		}
	}
}
