package persistance;

import java.sql.*;



public class DataAccess {
	
	public Connection connection;	
	private String URL;
	private String login;
	private String password;
	
	// Constructors
	DataAccess(){}
	
	DataAccess(String URL, String login, String password) throws SQLTimeoutException, SQLException, ClassNotFoundException{
		this.URL = URL;
		this.login = login;
		this.password = password;
		this.connect();
	}
	
	// Getters & Setters
	public void setURL(String URL) {
		this.URL = URL;
	}
	
	public String getURL() {
		return this.URL;
	}
	
	//Methods
	
	// Se connecte à la base de données
	public Boolean connect() throws SQLTimeoutException, SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Boolean result = false;
		if (this.URL.equals(null) || this.login.equals(null) || this.password.equals(null)) {
			System.out.println("Lack of information to connect to database\n");
		} else {			
			this.connection = DriverManager.getConnection(URL, login, password);
			if (!this.connection.equals(null)) {
				result = true;
			}
			
		}
		return result;
	}
	
	// Creer un statement non prepare
	public Statement createStmt() throws SQLException {
		Statement stmt = this.connection.createStatement();
		return stmt;
	}
	
	// Verifie si les informations necessaires à la connexion sont renseignees
	public String checkConnectionInfo() {
		String pass;
		if (this.password.equals(null))
			pass = "not set";
		else pass = "set";
			
		return ("URL : " + this.URL + ", login : " + this.login + ", password " + pass);
	}
	
	// Ferme la connection à la base de données
	public void close() throws SQLException {
		if (!this.connection.equals(null)) {
			this.connection.close();
		}
	}
	
}
