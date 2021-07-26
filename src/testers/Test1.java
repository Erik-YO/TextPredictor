/**
 * 
 */
package testers;

import java.io.FileNotFoundException;

import predictor.Predictor;

/**
 * @author Erik
 *
 */
public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Predictor p = new Predictor();
		
		p.insertWord("AC");
		p.insertWord("ABE");
		p.insertWord("D");
		p.insertWord("AB");
		p.insertWord("D");
		p.insertWord("ABE");
		p.insertWord("D");
		
		try {
			p.insertFile("./testfiles/in1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(p);

		System.out.println(p.getAllWords().size());
		
		System.out.println(p.predict("ha"));
		
		for(String s: p.getAllWords()) {
			p.removeWord(s);
		}
		
		//System.out.println(p.removeWord("había"));
		System.out.println(p.predict("ha"));
		//System.out.println(p.getAllWords().size());
		
	}

}
