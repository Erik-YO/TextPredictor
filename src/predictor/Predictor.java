/**
 * 
 */
package predictor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Erik
 *
 */
public class Predictor {
	
	private PNode origin;
	
	
	
	public Predictor() {
		
		origin = new PNode("");
	}
	
	public void insertWord(String word) {
		
		origin.insertWord(word);
	}
	
	public ArrayList<String> getAllWords(){
		return origin.getAllWords("");
	}
	
	public String toString() {
		return origin.getString("", 0);
	}
	
	
	
	public void insertFile(String fileName) throws FileNotFoundException {
		ArrayList<String> wrds = Predictor.getAllWordsFromFile(fileName);
		this.insertAllWords(wrds);
	}
	
	public void insertAllWords(Collection<String> words) {
		for(String s: words) {
			origin.insertWord(s);
		}
	}


	private static ArrayList<String> getAllWordsFromFile(String fileName) throws FileNotFoundException{
		ArrayList<String> wds= new ArrayList<String>();

		File f = new File(fileName);
		if(!f.exists()) throw new FileNotFoundException();

		Scanner myReader = new Scanner(f);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();


			for(String s: data.split(" ")) {

				if((!s.isEmpty()) && (!s.isBlank())) {

					if(Predictor.isWord(s)) {
						s = Predictor.wordFormat(s);

						if(!s.isEmpty()) wds.add(s);
					}

				}				

			}

		}
		myReader.close();

		return wds;
	}


	public ArrayList<String> predict(String w){
		HashMap<String, Integer> hm;
		if(w.length()==0) {
			hm = origin.getWordsEnds("");
		}else {
			hm = origin.findPredict(w, 0);
		}
		return Predictor.sortPredictions(hm);
	}




	private static ArrayList<String> sortPredictions(HashMap<String, Integer> mp){

		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer> > list =
				new LinkedList<Map.Entry<String, Integer> >(mp.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2)
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		ArrayList<String> temp = new ArrayList<String>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.add(aa.getKey());
		}
		Collections.reverse(temp);

		return temp;

	}


	private static boolean isWord(String w) {

		return w.toLowerCase()!=w.toUpperCase();		
	}

	private static String wordFormat(String w) {
		int l = w.length();
		while(l>0 && (w.endsWith(":") || w.endsWith(".") || w.endsWith(",") || w.endsWith("\t") || w.endsWith("(") || w.endsWith(")") || w.endsWith("[") || w.endsWith("]") || w.endsWith("\"") || w.endsWith("\'") || w.endsWith("#") || w.endsWith("?") || w.endsWith("¿") || w.endsWith("!") || w.endsWith("¡"))) {
			w=w.substring(0, l-1);
			l--;
		}

		while(l>0 && (w.startsWith(":") || w.startsWith(".") || w.startsWith(",") || w.startsWith("\t") || w.startsWith("(") || w.startsWith(")") || w.startsWith("[") || w.startsWith("]") || w.startsWith("\"") || w.startsWith("\'") || w.startsWith("#") || w.startsWith("?") || w.startsWith("¿") || w.startsWith("!") || w.startsWith("¡"))) {
			w=w.substring(1);
			l--;
		}

		return w;
	}
	
	
	public boolean removeWord(String w) {
		return origin.removeWord(w, 0);		
	}
	
	
	public int getNodeCount() {
		return 1+origin.getSubnodeCount();
	}
	
	public int getWordCount() {
		return origin.getWordCount();
	}
	
	
	
	public LinkedHashMap<String, Integer> predictFrequency(String w){
		HashMap<String, Integer> hm;
		if(w.length()==0) {
			hm = origin.getWordsEnds("");
		}else {
			hm = origin.findPredict(w, 0);
		}
		
		ArrayList<String> al = Predictor.sortPredictions(hm);
		
		LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>();
		for(String s: al) {
			lhm.put(s, hm.get(s));
		}
		
		return lhm;
	}


}





















/**/