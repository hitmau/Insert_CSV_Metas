import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;

public class InsereMeta implements AcaoRotinaJava {
	String arquivoCSV = "C:\\csv\\arquivo.csv";
	
	public static void main(String[] args) {
		
		InsereMeta obj = new InsereMeta();
	    obj.run();
	    
	}
	
	@Override
	public void doAction(ContextoAcao arg0) throws Exception {
		// TODO Auto-generated method stub

	
		
	}
	
	  public void run() {

		    BufferedReader br = null;
		    String linha = "";
		    String csvDivisor = ",";
		    try {

		        br = new BufferedReader(new FileReader(arquivoCSV));
		        while ((linha = br.readLine()) != null) {

		            String[] pais = linha.split(csvDivisor);

		            System.out.println("País [code= " + pais[pais.length-2] 
		                                 + " , name=" + pais[pais.length-1] + "]");

		        }

		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        if (br != null) {
		            try {
		                br.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		  }

}
