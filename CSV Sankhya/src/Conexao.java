//package br.com.ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;

public class Conexao implements AcaoRotinaJava {
	public String registro;
	public static String status = "Não conectou...";
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Conexão
	public static Connection ObterConexao() {
		Connection conexao = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@200.251.88.52:1521:total", "", "");
			//Testa sua conexão//  	 
            if (conexao != null) {	 
                status = ("STATUS--->Conectado com sucesso!");	
            } else {	
                status = ("STATUS--->Não foi possivel realizar conexão");	 
            }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Status
    public static void RetornaStatus() {
    	System.out.println(status);
   }
 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////fecha conexão
	public static boolean FecharConexao() {
        try {
            Conexao.ObterConexao().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////Método que reinicia conexão 
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();

        return Conexao.ObterConexao(); 
    }
   
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Busca no banco
	public static String busca(Connection conec,String busca) throws SQLException {
		java.sql.Statement acesso = conec.createStatement();
		
		try{  
			String sql = "SELECT * FROM AD_TESTEMRS where id = '" + busca + "'";
			ResultSet res = acesso.executeQuery(sql);
			if(res != null && res.next()){
                status = res.getString("id");
            }
            System.out.println(status);  
        }  
        catch(SQLException ex){  
            ex.printStackTrace();  
        }  
	return status;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Update no banco
	public static String atualizar (Connection conec,String busca) throws SQLException{
		java.sql.Statement acesso = conec.createStatement();		
		try{  
			String sql = "UPDATE pw_licenca SET numeroContrato = '789' where idLicenca = '" + busca + "'";			
			int res = acesso.executeUpdate(sql);
			if(res !=  0){
                status = "OK";
            }
            System.out.println(status);  
        }  
        catch(SQLException ex){  
            ex.printStackTrace();  
        }  
	return status;
		
	}
	@Override
	public void doAction(ContextoAcao contexto) throws Exception {
		Connection a = Conexao.ObterConexao();			
		//RECEBE DO SANKHYA	
		String teste = contexto.getParam("BUSC").toString();
		//BUSCA
		//String aa = busca(a,teste);
		//UPDATE
		String aa = atualizar(a,teste);
		
	contexto.setMensagemRetorno(aa);
	}
	
	public static void main(String[] args) {
		Conexao.ObterConexao();
		Conexao.RetornaStatus();
	}

}
