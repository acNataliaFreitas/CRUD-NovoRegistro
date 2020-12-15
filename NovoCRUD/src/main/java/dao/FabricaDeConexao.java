package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
	private Connection conexao;
	private static volatile FabricaDeConexao instanciaUnica;

	private FabricaDeConexao() {

		try {
			conexao = DriverManager.getConnection(
					"jdbc:mysql://us-cdbr-east-02.cleardb.com/heroku_29233e2d8f9b041?reconnect=true", "b65f349e12df59",
					"ccf37ce4");
//			return DriverManager.getConnection("jdbc:mysql://localhost/sistemacrud?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			System.out.println("---------------------> " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		synchronized (FabricaDeConexao.class) {

			if (instanciaUnica == null) {
				System.out.println(FabricaDeConexao.class.getName()+ ": criando nova conexão de BD");
				instanciaUnica = new FabricaDeConexao();
			}
		}
		return instanciaUnica.conexao;
	}
}
