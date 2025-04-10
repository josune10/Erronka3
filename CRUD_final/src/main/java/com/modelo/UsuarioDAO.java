package com.modelo;

import java.io.IOException;
import java.sql.*;
import java.util.*;


public class UsuarioDAO {
	/*
	 * Conexion url, user,pass, driver
	 * */
	private Connection conn;
	private final String url ="jdbc:mysql://localhost:3306/SecureDB";
	private final String user ="root";
	private final String password ="Kazuki1414";
	private final String driver="com.mysql.cj.jdbc.Driver";
	/* *
	 * SQL Sentences
	 * */
	private final String SELECT_USER="SELECT id, nombre, email, password FROM SecureDB.usuarios";
	private final String SELECT_USER_BY_ID="SELECT id, nombre, email, password FROM SecureDB.usuarios WHERE id=?";
	private final String CREATE_USER="INSERT INTO SecureDB.usuarios (nombre, email, password) VALUES (?, ?, ?)";
	private final String DELETE_USER="DELETE FROM SecureDB.usuarios WHERE id=?";
	private final String UPDATE_USER="UPDATE SecureDB.usuarios SET nombre=?, email=?, password=? WHERE id=?";
	private final String VALIDATE_USER="SELECT count(*) FROM SecureDB.usuarios WHERE email=? AND password=?";
	/*
	 * CONSTRUCTOR
	 */
	public UsuarioDAO() {}
	
	/*
	 * DATABASE CONNECTION & DISCONNECT
	 */
	private Connection conectarBD() throws SQLException, IOException {
		try {
			Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");
		}catch(Exception e) {
			e.getStackTrace();
		}
		return conn;
	}
	
	private void desconectarBD() {
		try {
			if(conn !=null && !conn.isClosed()) {
				conn.clearWarnings();
				conn.close();
				System.out.println("DesConexión exitosa de la base de datos");
			}
		}catch(Exception e) {
			e.getStackTrace();		
		}
	}
	/* *
	 * READ USERS 
	 * */
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> mylista = new ArrayList<>();
        try {
            conn=this.conectarBD();
        	PreparedStatement ps = conn.prepareStatement(SELECT_USER);
        	ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Usuario u = new Usuario();
            	u.setId(rs.getInt("id"));
            	u.setNombre(rs.getString("nombre"));
            	u.setEmail(rs.getString("email"));
            	u.setPassword(rs.getString("password"));
                mylista.add(u);
                //System.out.println(rs.getInt("id")+" - "+rs.getString("nombre"));
            }
            //this.desconectarBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mylista;
    }
    
    public Usuario listarPorId (int id) throws SQLException{
    	Usuario usu=null;
    	try {
    		conn=this.conectarBD();
    		PreparedStatement pst = conn.prepareStatement(SELECT_USER_BY_ID);
    		//System.out.println(SELECT_USER_BY_ID);
    		pst.setInt(1, id);
    		ResultSet rst = pst.executeQuery();
    		
    		while(rst.next()) {
    			int usuid= rst.getInt("id");
    			String usuNombre = rst.getString("nombre");
    			String usuEmail = rst.getString("email");
    			String usuPassword = rst.getString("password");
        		//System.out.println(usuid+"- -"+usuNombre+"- -"+usuEmail);
    			usu = new Usuario(usuid,usuNombre,usuEmail,usuPassword);
    		}
    		
    	}catch(Exception e) {
    		e.getMessage();
    	}
    	return usu;
    }
	/* * *
	 * CREATE USER 
	 * * */
    public void insertar(Usuario usuario) throws SQLException{
    
    	try {
    		conn=this.conectarBD();
    		//System.out.println("Antes de insertar");
        	PreparedStatement ps = conn.prepareStatement(CREATE_USER);
        	System.out.println(CREATE_USER+" - "+usuario.getNombre()+" - "+usuario.getEmail()+" - "+usuario.getPassword());
        	ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();
            //System.out.println("una vez insertado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //** UPDATE USER**//
    public void actualizar(Usuario usuario) throws SQLException{
    	
    	try{
    		conn=this.conectarBD();
            PreparedStatement ps = conn.prepareStatement(UPDATE_USER);
            //System.out.println(UPDATE_USER+" - "+usuario.getNombre()+" - "+usuario.getEmail()+" - "+usuario.getId());
        	ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
            System.out.println("Actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //** DELETE USER**//
    public void eliminar(int id) throws SQLException{
        try (PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * VALIDAR LOGIN
     *
     */
    public boolean loginUsuario(String usuario, String password) {
    	boolean resultado=false;
    	try{
    		conn = this.conectarBD();
    		PreparedStatement pst = conn.prepareStatement(VALIDATE_USER);
    		//System.out.println(VALIDATE_USER+" - "+usuario+" - "+password);
    		pst.setString(1, usuario);
    		pst.setString(2, password);
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
    }
}
