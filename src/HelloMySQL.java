
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
			
			Connection con = MysqlConnection.getConnection(); // Busca uma conexão com o banco de dados

			
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
			
			
			
			// inserindo dados - utilizando sempre a mesma conexão - objeto con
			// prepared statement facilita a proteção contra SQL injection
			PreparedStatement pst = con.prepareStatement("INSERT INTO aluno(nome, dt_nasc) values (?, ?);");
			
			pst.setString(1, "carlos"); // a interrogação na posição 1
			
			String dtNascStr = "1970/09/04";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(dtNascStr, formatter);
			
			pst.setDate(2, java.sql.Date.valueOf(localDate)); // a interrogação na posição 2
			
			pst.execute();

			System.out.println("Inserção realizada");		
			
			
			// Prática: pesquisar e testar a edição (UPDATE) e remoção (DELETE) de alunos
			
			
			
			
			


		} catch (Exception ex){ // Tratamento das exceções

			System.out.println("Erro: " + ex);

		}

	}

}
