package csv;

import java.io.IOException;
import java.util.List;

public class run {

	private static final String  fileNameReader ="D:\\Projets\\QaoBee\\Livrable\\samples_old.csv";
	private static final String fileNameWriter="D:\\Projets\\QaoBee\\Livrable\\samples_new.csv";
	
	public static void main(String[] args) throws IOException {
		
	List<Entitie> listeMemoire = csvFileReader.readCsvFile(fileNameReader);
	
	for(Entitie entitie : listeMemoire){
		System.out.println(entitie.toString());
	}
		
	csvFileReader.CsvWriterFile(fileNameWriter, listeMemoire);	
		
	}

}
