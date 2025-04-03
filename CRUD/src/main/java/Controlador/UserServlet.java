package Controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 * 
 * @author = Iker Coranti Gabilondo (iCrouk)
 */
@WebServlet("/")
//@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* 1- DAO class bat behar dugu.*/
	private UsuarioDAO miUsuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        this.miUsuarioDAO = new UsuarioDAO();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch(action) {
				case "/nuevo":
					mostrarNuevoFromulario(request,response);
					break;
				case "/insertar":
					insertarUsuario(request,response);
					break;
				case "/modificar":
					actualizarUsuario(request,response);
					break;
				case "/eliminar":
					eliminarUsuario(request,response);
					break;
				default:
					listarTodoLosUsuarios(request,response);
					break;
				}//EndSwitch
		}catch(SQLException eSQL){
			eSQL.getMessage();
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
/*
 *  Listara todos las tuplas de una tabla.
 */
	private void listarTodoLosUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ArrayList<Usuario> listarUsuarios = miUsuarioDAO.listarTodos();
		request.setAttribute("listarTodoLosUsuarios", listarUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listarUsuarios.jsp");
		dispatcher.forward(request, response);
	}
/*
 * 
 */
	protected void mostrarNuevoFromulario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispacherNEW = request.getRequestDispatcher("usuarioFormularioNuevo.jsp");
		dispacherNEW.forward(request, response);
	}
	protected void editarUsuarioExistente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		String name= request.getParameter("name");
		String email=request.getParameter("email");
		Usuario miUsuario = new Usuario(name,email);
		miUsuarioDAO.agregar(miUsuario);
		response.sendRedirect("listar");
	}
	/*
	 * Actualiza los datos de un objeto tipo Usuario y los updatea en la base de datos.
	 */
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
	

		Usuario Usubook = new Usuario(id, name, email);
		miUsuarioDAO.actualizar(Usubook);
		response.sendRedirect("listar");
	}
	/*
	 * Insterta un nuevo usuario en la BD y despues vuelve a listar ta tabla.
	 */
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		try{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
	
			Usuario nuevoUsuario = new Usuario(name, email);
			miUsuarioDAO.agregar(nuevoUsuario);
			response.sendRedirect("listar");
		}catch(SQLException e) {
			e.getLocalizedMessage();
		}
	}
	/*
	 * Elimina una tupla usando el ID y vuelve a mostrat todas las tuplas de la tabla.
	 */
	protected void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("id"));
		miUsuarioDAO.eliminar(id);
		response.sendRedirect("listar");
	}
	

}
