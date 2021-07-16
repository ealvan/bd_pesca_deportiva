import java.sql.*;
import java.util.ArrayList;

public class Conexion {
	// Definir la ruta de la base de datos
	// establenciendo la time zone UTC

	private String dbUrl = "jdbc:mysql://localhost/bd_pescadeportiva?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	// Definir el nombre de usuario de la base de datos
	private String dbUser = "root";
	// Definir la contrase�a de la base de datos
	private String dbPassword = "Jeampier123A";
	// Definir controlador de carga
	private String jdbcName = "com.mysql.cj.jdbc.Driver";

	// private String dbUrl =
	// "jdbc:mysql://localhost/bd_pescadeportiva?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	// // Definir el nombre de usuario de la base de datos
	// private String dbUser = "root";
	// // Definir la contrase�a de la base de datos
	// private String dbPassword = "edsel";
	// // Definir controlador de carga
	// private String jdbcName = "com.mysql.cj.jdbc.Driver";

	// Con�ctate a la base de datos
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(jdbcName);
		} catch (Exception e) {
			System.out.println("�Error al cargar el controlador!");
		}
		try {
			conn = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("�Error al conectarse a la base de datos!");
		}
		return conn;
	}

	// prueba
	public static void main(String[] args) {
		Conexion a = new Conexion();
		Connection con = a.getConn();
		getData("eventos", con);
	}

	public Object[][] retrieveDataFromSelect(String table, Connection con, int cols) {
		String query = "SELECT * FROM " + table;
		Object[][] datosT = new Object[10][4];
		String str = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int r = 0;
			while (rs.next()) {
				Object[] row = { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), };
				str += rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4);
				str += "\n";
				datosT[r] = row;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(str);
		return datosT;
	}

	static void display(Connection con) {
		// SELECT * FROM bd_pd_1.peces; tiene 4 atributos
		String query = "SELECT * FROM peces;";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
				System.out.println(
						rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void insert(Connection con) {

		try {
			String columns = "(AfiCod, AfiNom, AfiApePat, AfiApeMat, AfiFecInsAnio, AfiFecInsMes, AfiFecInsDia , PaiCod, EstCivCod )";

			String query = " insert into afiliados " + columns + "\n values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, 8);// AfiCod
			preparedStmt.setString(2, "Quintana");// AfiNom
			preparedStmt.setString(3, "Coroles");// AfiApePat
			preparedStmt.setString(4, "De Rosario");// AfiApeMat
			preparedStmt.setInt(5, 2001);// AfiFecInsAnio
			preparedStmt.setInt(6, 2);// AfiFecInsMes
			preparedStmt.setInt(7, 22);// AfiFecInsDia
			preparedStmt.setInt(8, 4);// PaiCod
			preparedStmt.setInt(9, 2);// EstCivCod
			preparedStmt.execute();
			System.out.print("Ingreso de Datos Correcto");

			con.close();
		} catch (Exception e) {
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
			System.out.println("Se Borro Exitosamente el REgistro numero : " + id);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void update(Connection con) {
		try {
			int id = 6;
			String query = "update afiliados set AfiNom = ? where AfiCod = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, "Kinto");
			preparedStmt.setInt(2, id);
			System.out.println("Se Actualizo Exitosamente el Registro numero : " + id);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static ArrayList<String> getColumns(String tableName, Connection con) {
		if (tableName.isEmpty()) {
			tableName = "peces";
		}
		ArrayList<String> columns = new ArrayList<String>();

		String query = "SHOW COLUMNS FROM " + tableName + ";";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				columns.add(rs.getString(1));
			}
			// System.out.println(columns.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return columns;
	}

	// get data, nos devuelve los datos, junto con sus columnas
	// de una tabla que se especifique en tableName
	static ArrayList<String[]> getData(String tableName, Connection con) {
		// obtener columnas
		ArrayList<String> columns = getColumns(tableName, con);
		ArrayList<String[]> data = new ArrayList<String[]>();
		// pasamos a un array statico las columnas
		String[] cols = new String[columns.size()];
		int p = 0;
		for (String col : columns) {
			cols[p] = col;
			p++;
		}
		// agregamos como el head de la tabla
		// siempre la primera fila, son las columnas!
		data.add(cols);

		// get type of data
		String[] types = new String[columns.size()];
		// son las queryes que usamos
		// qtypes, se usa para la descripcion de de la tabla
		String qTypes = "SHOW COLUMNS FROM " + tableName + ";";
		String qData = "SELECT * FROM " + tableName + ";";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qTypes);
			int i = 0;
			while (rs.next()) {
				types[i] = rs.getString(2);
				i++;
			}

			// get table of data
			Statement stmtData = con.createStatement();
			ResultSet resSet = stmtData.executeQuery(qData);
			// vamos fila, por fila
			while (resSet.next()) {
				String[] row = new String[types.length];
				// ahora vamos por las celdas de la fila
				for (int j = 1; j <= row.length; j++) {
					if (types[j - 1].startsWith("int")) {
						row[j - 1] = String.valueOf(resSet.getInt(j));
					} else {
						row[j - 1] = resSet.getString(j);
					}
				}
				data.add(row);
			}
			/*
			 * for(int q =0; q < data.size(); q++) { for(int u =0; u < data.get(q).length;
			 * u++) { System.out.print(data.get(q)[u] + " " ); } System.out.println(); }
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		// retornamos, si no se logra, tendra un error
		// de indexOfBounds Exception o otro error del ArrayList
		return data;
	}

}
