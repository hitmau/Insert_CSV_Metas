import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {
	static String arquivoCSV = "C:\\csv\\arquivo.csv";
	static String csvDivisor = ",";
	static int pk;
	
	public static void ObterCSV() throws SQLException {
		BufferedReader br = null;
		String linha = "";
		
		
		try {
			Connection con = Conexao.ObterConexao();
			br = new BufferedReader(new FileReader(arquivoCSV));
			while ((linha = br.readLine()) != null) {

				String[] pais = linha.split(csvDivisor);
				//pais[pais.length - 2].replaceAll("\"", "");
				System.out.println(pais[pais.length - 3].replaceAll("\"", ""));
				System.out.println(pais[pais.length - 2].replaceAll("\"", ""));
				System.out.println(pais[pais.length - 1].replaceAll("\"",  ""));
				
				String busca = Conexao.busca(con, "5412");
				//Statement statement = Conexao.createStatement();
				String query = "SELECT count(id) as id FROM AD_TESTEMRS";
				//ResultSet resultSet = statement.executeQuery(query);
				/*
				if (resultSet.next()) {
					pk = resultSet.getInt("id");
					// System.out.println(resultSet.getInt("CODPROD") + " " +
					// resultSet.getString("descrprod"));
				}
				if (pk != 0) {
					query = "SELECT max(id) + 1 as id FROM AD_TESTEMRS";
					resultSet = statement.executeQuery(query);
					if (resultSet.next()) {
						pk = resultSet.getInt("id");
					}

				} else {
					pk = 1;
				};*/
				/*

			PreparedStatement stmt = CSV.prepareStatement("insert into AD_TESTEMRS(id, nome, nome2, lugar)values(?,?,?,?)");
				// preenche os valores
				stmt.setInt(1, pk);
				stmt.setString(2,pais[pais.length - 3].replaceAll("\"", "").replace(" ", ""));
				stmt.setString(3,pais[pais.length - 2].replaceAll("\"", "").replace(" ", ""));
				stmt.setString(4,pais[pais.length - 1].replaceAll("\"",  "").replace(" ", ""));

				// executa
				stmt.execute();
				stmt.close();
				*/
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			//conexao.close();
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
