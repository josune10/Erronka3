package modelo;

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
	private final String SELECT_USER="SELECT id, nombre, email FROM SecureDB.usuarios";
	private final String CREATE_USER="INSERT INTO SecureDB.usuarios (nombre, email) VALUES (?, ?)";
	private final String DELETE_USER="DELETE FROM SecureDB.usuarios WHERE id=?";
	private final String UPDATE_USER="UPDATE SecureDB.usuarios SET nombre=?, email=? WHERE id=?";
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
	 * READ USER 
	 * */
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> mylista = new ArrayList<>();
        try {
            this.conectarBD();
            System.out.println("Conexión exitosa a la base de datos");
        	PreparedStatement ps = conn.prepareStatement(SELECT_USER);
        	ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Usuario u = new Usuario();
            	u.setId(rs.getInt("id"));
            	u.setNombre(rs.getString("nombre"));
            	u.setEmail(rs.getString("email"));
                mylista.add(u);
                System.out.print(rs.getInt("id")+""+rs.getString("nombre"));
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
    		conn = this.conectarBD();
    		PreparedStatement pst = conn.prepareStatement(CREATE_USER);
    		ResultSet rst = pst.executeQuery();
    		
    		while(rst.next()) {
    			int usuid= rst.getInt("id");
    			String usuNombre = rst.getString("nombre");
    			String usuEmail = rst.getString("email");
    			usu = new Usuario(usuid,usuNombre,usuEmail);
    		}
    		//this.desconectarBD();
    	}catch(Exception e) {
    		e.getMessage();
    	}
    	return usu;
    }
	/* * *
	 * CREATE USER 
	 * * */
    public void agregar(Usuario usuario) throws SQLException{
    	//boolean numrow;
    	try {
            this.conectarBD();
        	PreparedStatement ps = conn.prepareStatement(CREATE_USER);
        	ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            //numrow=ps.executeUpdate()>0;
            //this.desconectarBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //** UPDATE USER**//
    public void actualizar(Usuario usuario) throws SQLException{
    	//boolean numrow;
    	try{
        	this.conectarBD();
            PreparedStatement ps = conn.prepareStatement(UPDATE_USER);
        	ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getId());
            //numrow=ps.executeUpdate()>0;
            //this.desconectarBD();
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
}

