package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class csvFileReader {

	// delimiter used in csv
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// data index
	private static final int DATA_ID_IDX = 0;
	private static final int DATA_TIMESTAMP_IDX = 1;
	private static final int DATA_X_IDX = 2;
	private static final int DATA_Y = 3;
	private static final int DATA_Z = 4;
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
	/**
	 * 
	 * @param fileNameReader
	 * @return Liste des enregistrement traités
	 * @throws IOException
	 */
	public static List<Entitie> readCsvFile(String fileNameReader) throws IOException {

		BufferedReader fileReader = null;
		List<Entitie> listeMemoire = new ArrayList<Entitie>();
		List<Entitie> listeFile = new ArrayList<Entitie>();

		try {

			String line = "";
			long diff;
			/*
			 * create fileReader based on fileNameReader et on lit que la 1er
			 * ligne
			 */
			fileReader = new BufferedReader(new FileReader(fileNameReader));
			line = fileReader.readLine();
			String[] token = line.split(COMMA_DELIMITER);
			/* on cree l'objet a partir de la ligne */
			Entitie firstObject = new Entitie(Integer.parseInt(token[DATA_ID_IDX]),
					Long.parseLong(token[DATA_TIMESTAMP_IDX]), Integer.parseInt(token[DATA_X_IDX]),
					Integer.parseInt(token[DATA_Y]), Integer.parseInt(token[DATA_Z]));
			/*
			 * on remplie la memoire et listefile du premiere objet, test reussi
			 */
			listeMemoire.add(firstObject);
			listeFile.add(firstObject);
			for (Entitie entitie : listeMemoire) {
				System.out.println("phase 1 " + entitie.toString());
			}

			/* boucle sur le fichier ligne par ligne */
			while ((line = fileReader.readLine()) != null) {

				/* on transforme en tableau la ligne */
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					/*
					 * create new object of our data provided from the line
					 * fetched
					 */
					Entitie entitie = new Entitie(Integer.parseInt(tokens[DATA_ID_IDX]),
							Long.parseLong(tokens[DATA_TIMESTAMP_IDX]), Integer.parseInt(tokens[DATA_X_IDX]),
							Integer.parseInt(tokens[DATA_Y]), Integer.parseInt(tokens[DATA_Z]));

					/* iter sur la liste */
					Iterator<Entitie> iter = listeMemoire.iterator();
					while (iter.hasNext()) {
						Entitie entBoucle = iter.next();
						diff = Math.abs(entitie.getTimestamp() - entBoucle.getTimestamp());
						if (entBoucle.getSid() == entitie.getSid() && diff < 40000000000L) {
							iter.remove();
							System.out.println("difference est petite entre les memes sid");
						} else {
							// l'objet est nouveau
							// je l'ajoute dans fichier sortie
						}
					} // fin d'iteration sur la liste

					listeMemoire.add(entitie);
				} // fin de tokens de la ligne
			} // fin de la boucle
			System.out.println("le size est de : " + listeMemoire.size());
		} catch (Exception e) {
			System.out.println("erreur");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (Exception e) {
				System.out.println("error");
			}
		}

		return listeMemoire;
	}
	
	/**
	 * @Process écris les enregistrement de la liste dans le fichier de sortie
	 * @param fileNameWriter
	 * @param listeMemoire
	 */
	public static void CsvWriterFile(String fileNameWriter,List<Entitie> listeMemoire){
		
	  FileWriter fileWriter = null;
		 
	  try{
			 fileWriter = new FileWriter(fileNameWriter);
			 for(Entitie entitie : listeMemoire){
				 fileWriter.append(String.valueOf(entitie.getSid()));
				 fileWriter.append(COMMA_DELIMITER);
				 fileWriter.append(String.valueOf(entitie.getTimestamp()));
				 fileWriter.append(COMMA_DELIMITER);
				 fileWriter.append(String.valueOf(entitie.getX()));
				 fileWriter.append(COMMA_DELIMITER);
				 fileWriter.append(String.valueOf(entitie.getY()));
				 fileWriter.append(COMMA_DELIMITER);
				 fileWriter.append(String.valueOf(entitie.getZ()));
				 fileWriter.append(NEW_LINE_SEPARATOR);
			 }
			 System.out.println("csv file has been succesfly filled");
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try{
				 fileWriter.flush();
				 fileWriter.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
	}
	

}
