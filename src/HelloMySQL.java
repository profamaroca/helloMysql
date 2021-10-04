
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DatabaseConnection.MysqlConnection;

public class HelloMySQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		try{
			
			Connection con = MysqlConnection.getConnection(); // Busca uma conex�o com o banco de dados

			
			// consultando dados
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM aluno;");
			  
			// varrendo os resultados
			while (rs.next()) {
				int matricula = rs.getInt("matricula");
				String nome = rs.getString("nome");
				Date dt_nasc = rs.getDate("dt_nasc");
				
				// print the results
				System.out.format("%s, %s, %s\n", matricula, nome, dt_nasc);
			}
			
			
			
			// inserindo dados - utilizando sempre a mesma conex�o - objeto con
			// prepared statement facilita a prote��o contra SQL injection
			PreparedStatement pst = con.prepareStatement("INSERT INTO aluno(nome, dt_nasc) values (?, ?);");
			
			pst.setString(1, "carlos"); // a interroga��o na posi��o 1
			
			String dtNascStr = "1970/09/04";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(dtNascStr, formatter);
			
			pst.setDate(2, java.sql.Date.valueOf(localDate)); // a interroga��o na posi��o 2
			
			pst.execute();

			System.out.println("Inser��o realizada");		
			
			
			// Pr�tica: pesquisar e testar a edi��o (UPDATE) e remo��o (DELETE) de alunos
			
			
			
			
			


		} catch (Exception ex){ // Tratamento das exce��es

			System.out.println("Erro: " + ex);

		}

	}

}
