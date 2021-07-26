package testers;

import java.io.FileNotFoundException;

import gui.MainGui;

public class GuiTest {

	public static void main(String[] args) {
		

		long startTime = System.currentTimeMillis();
		try {
			System.out.println("Time(mili): " + (System.currentTimeMillis()-startTime));
			
			final MainGui mg = new MainGui();
			System.out.println("Node count: " + mg.p.getNodeCount() + " -> Words:" + mg.p.getWordCount() + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
			
			startTime=System.currentTimeMillis();
			
			mg.p.insertFile("./testfiles/in1.txt");
			System.out.println("Node count: " + mg.p.getNodeCount() + " -> Words:" + mg.p.getWordCount() + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
			mg.p.insertFile("./testfiles/heroedelaseras.txt");
			System.out.println("Node count: " + mg.p.getNodeCount() + " -> Words:" + mg.p.getWordCount() + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
			mg.p.insertFile("./testfiles/pozo.txt");
			System.out.println("Node count: " + mg.p.getNodeCount() + " -> Words:" + mg.p.getWordCount() + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
			mg.p.insertFile("./testfiles/imperio.txt");
			System.out.println("Node count: " + mg.p.getNodeCount() + " -> Words:" + mg.p.getWordCount() + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
			mg.p.insertFile("./testfiles/aleacion.txt");
			System.out.println("Node count: " + mg.p.getNodeCount() + " -> Words:" + mg.p.getWordCount() + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
			

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {

			System.out.println("Predictor Init" + " -> Time(mili): " + (System.currentTimeMillis()-startTime));
		}

	}

}
