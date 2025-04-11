package com.modelo;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ErabiltzaileaDAO {

	private Connection conn;
	private final String url ="jdbc:mysql://localhost:3306/erronka3";
	private final String user ="root";
	private final String password ="Kazuki1414";
	private final String driver="com.mysql.cj.jdbc.Driver";
	
	private final String SELECT_USER="SELECT id_erabiltzaile, email, pasahitza, fk_id_rola FROM erronka3.Erabiltzaileak";
	private final String SELECT_USER_BY_ID="SELECT id_erabiltzaile, email, pasahitza, fk_id_rola FROM erronka3.Erabiltzaileak WHERE id_erabiltzaile=?";
	private final String CREATE_USER="INSERT INTO erronka3.Erabiltzaileak (email, pasahitza, fk_id_rola) VALUES (?, ?, ?)";
	private final String DELETE_USER="DELETE FROM erronka3.Erabiltzaileak WHERE id_erabiltzaile=?";
	private final String UPDATE_USER="UPDATE erronka3.Erabiltzaileak SET email=?, pasahitza=?, fk_id_rola=? WHERE id_erabiltzaile=?";
	private final String VALIDATE_USER="SELECT count(*) FROM erronka3.Erabiltzaileak WHERE email=? AND pasahitza=?";
	
public ErabiltzaileaDAO() {}

private Connection conectarBD() throws SQLException, IOException {
	try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
		System.out.println("Conexión exitosa a la base de datos");
	} catch (Exception e) {
		e.getStackTrace();
	}
	return conn;
}//FINconectarDB

private void desconectarBD() {
    try {
        if(conn !=null && !conn.isClosed()) {
            conn.clearWarnings();
            conn.close();
            System.out.println("DesConexión exitosa de la base de datos");
        }
    } catch (Exception e) {
        e.getStackTrace();		
    }	
}//FINdesconectarDB

public ArrayList<Erabiltzailea> listarTodos() {
    ArrayList<Erabiltzailea> mylista = new ArrayList<>();
    try {
        conn=this.conectarBD();
    	PreparedStatement ps = conn.prepareStatement(SELECT_USER);
    	ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	Erabiltzailea e = new Erabiltzailea();
        	e.setId_erabiltzaile(rs.getInt("id_erabiltzaile"));
        	e.setEmail(rs.getString("email"));
        	e.setPasahitza(rs.getString("pasahitza"));
        	e.setFk_id_rola(rs.getInt("fk_id_rola"));
            mylista.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return mylista;
}//FINlistarTodos

public Erabiltzailea listarPorId (int id) throws SQLException{
	Erabiltzailea erab=null;
	try {
		conn=this.conectarBD();
		PreparedStatement pst = conn.prepareStatement(SELECT_USER_BY_ID);
		pst.setInt(1, id);
		ResultSet rst = pst.executeQuery();
		
		while(rst.next()) {
			int erabid= rst.getInt("id_erabiltzaile");
			String erabEmail = rst.getString("email");
			String erabPasahitza = rst.getString("pasahitza");
			int erabFk_id_rola = rst.getInt("fk_id_rola");
			erab = new Erabiltzailea(erabid,erabEmail,erabPasahitza,erabFk_id_rola);
		}	
	}catch(Exception e) {
		e.getMessage();
	}
	return erab;
}//FINlistarPorId

public void insertar(Erabiltzailea erabiltzailea) throws SQLException{
    
	try {
		conn=this.conectarBD();
    	PreparedStatement ps = conn.prepareStatement(CREATE_USER);
    	System.out.println(CREATE_USER+" - "+erabiltzailea.getEmail()+" - "+erabiltzailea.getPasahitza()+" - "+erabiltzailea.getFk_id_rola());
    	ps.setString(1, erabiltzailea.getEmail());
        ps.setString(2, erabiltzailea.getPasahitza());
        ps.setInt(3, erabiltzailea.getFk_id_rola());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}//FINinsertar

public void actualizar(Erabiltzailea erabiltzailea) throws SQLException{
	
	try{
		conn=this.conectarBD();
        PreparedStatement ps = conn.prepareStatement(UPDATE_USER);
    	ps.setString(1, erabiltzailea.getEmail());
        ps.setString(2, erabiltzailea.getPasahitza());
        ps.setInt(3, erabiltzailea.getFk_id_rola());
        //ps.setInt(4, erabiltzailea.getId_erabiltzaile());//
        ps.executeUpdate();
        System.out.println("Actualizado");
    } catch (Exception e) {
        e.printStackTrace();
    }
}//FINactualizar

public void eliminar(int id_erabiltzaile) throws SQLException{
    try (PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
        ps.setInt(1, id_erabiltzaile);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}//FINeliminar

/*public boolean loginErabiltzailea(String email, String pasahitza) {
	boolean resultado=false;
	try{
		conn = this.conectarBD();
		PreparedStatement pst = conn.prepareStatement(VALIDATE_USER);
		//System.out.println(VALIDATE_USER+" - "+email+" - "+pasahitza);
		pst.setString(1, email);
		pst.setString(2, pasahitza);
		ResultSet rst=pst.executeQuery();
		
		while (rst.next()){
			resultado= (rst.getInt(1))>0;
		}
		pst.close();
		rst.close();
	}catch(Exception e) {
		e.printStackTrace();
	}finally{
		
	}
	return resultado;
}*/


}//FIN_TODO
