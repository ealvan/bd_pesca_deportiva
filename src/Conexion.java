import java.sql.*;
public class Conexion {
				// Definir la ruta de la base de datos
				// establenciendo la time zone UTC
				private String dbUrl = "jdbc:mysql://localhost/bd_pescadeportiva?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				 // Definir el nombre de usuario de la base de datos
				private String dbUser = "root";
				 // Definir la contraseña de la base de datos
				private String dbPassword = "edsel";
				 // Definir controlador de carga
				private String jdbcName = "com.mysql.cj.jdbc.Driver";
			 
				 // Conéctate a la base de datos
				public Connection getConn() {
					Connection conn = null;
					try {
						Class.forName(jdbcName);
					} catch (Exception e) {
						 System.out.println ("¡Error al cargar el controlador!");
					}
					try {
						conn = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
					} catch (SQLException ex) {
						 ex.printStackTrace();
						 System.out.println ("¡Error al conectarse a la base de datos!");
					}
					return conn;
				}
			 
				 // prueba
				public static void main(String[] args) {
					Conexion a = new Conexion();
					Connection con = a.getConn();
					display(con);
					//insert(con);
					//update(con);
					
				}
				public Object[][] retrieveDataFromSelect(String table, Connection con, int cols) {
					String query = "SELECT * FROM " + table;
					Object[][] datosT = new Object[10][4];
					String str ="";
					try {
						Statement stmt = con.createStatement();  
						ResultSet rs = stmt.executeQuery(query);  
						int r = 0;
						while(rs.next()) {
							Object[] row = {
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
							};
							str += rs.getInt(1)+"  "+ rs.getString(2)+ "  "+rs.getString(3)+"  "+ rs.getString(4);
							str += "\n";
							datosT[r] = row;
						}
						con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println(str);
					return datosT;
				}
				static void display( Connection con) {
					//SELECT * FROM bd_pd_1.peces; tiene 4 atributos
					String query = "SELECT * FROM peces;";
					try {
						Statement stmt = con.createStatement();  
						ResultSet rs = stmt.executeQuery(query);  
						while(rs.next())  
							System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + "  "+rs.getString(4)); 
						con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				static void insert( Connection con) {
					
					try {
						String columns = "(AfiCod, AfiNom, AfiApePat, AfiApeMat, AfiFecInsAnio, AfiFecInsMes, AfiFecInsDia , PaiCod, EstCivCod )";
						
						String query = " insert into afiliados "+columns + "\n values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query);
					      preparedStmt.setInt (1, 8);//AfiCod
					      preparedStmt.setString (2, "Quintana");//AfiNom
					      preparedStmt.setString (3, "Coroles");//AfiApePat
					      preparedStmt.setString (4, "De Rosario");//AfiApeMat
					      preparedStmt.setInt    (5, 2001);//AfiFecInsAnio
					      preparedStmt.setInt    (6, 2);//AfiFecInsMes
					      preparedStmt.setInt    (7, 22);//AfiFecInsDia
					      preparedStmt.setInt    (8, 4);//PaiCod
					      preparedStmt.setInt    (9, 2);//EstCivCod
					      preparedStmt.execute();
					      System.out.print("Ingreso de Datos Correcto");
					      
					      con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}

				}
				static void delete(Connection con) {
					
					try {
						int id = 8;
						String query = "DELETE FROM afiliados WHERE AfiCod = ?";
						PreparedStatement sentencia = con.prepareStatement(query);
					    sentencia.setInt(1, id);
					    sentencia.execute();
					    System.out.println("Se Borro Exitosamente el REgistro numero : "+ id);
					    con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				static void update(Connection con) {
					try {
						int id = 6;
						String query = "update afiliados set AfiNom = ? where AfiCod = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
					    preparedStmt.setString  (1, "Kinto");
					    preparedStmt.setInt		(2, id);
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					    con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			
				
				
				
}
