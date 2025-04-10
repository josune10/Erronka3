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


@WebServlet("/")
//@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO miUsuarioDAO;
       
    
    public UserServlet() {
        super();
        this.miUsuarioDAO = new UsuarioDAO();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

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
	
	private void listarTodoLosUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ArrayList<Usuario> listarUsuarios = miUsuarioDAO.listarTodos();
		request.setAttribute("listarTodoLosUsuarios", listarUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listarUsuarios.jsp");
		dispatcher.forward(request, response);
	}

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
		request.setAttribute("miUsuario", miUsuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuarioFormularioEditar.jsp");
		dispatcher.forward(request, response);
		response.sendRedirect("listar");
	}
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
	

		Usuario Usubook = new Usuario(id, name, email);
		miUsuarioDAO.actualizar(Usubook);
		response.sendRedirect("listar");
	}
	
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
	
	protected void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("id"));
		miUsuarioDAO.eliminar(id);
		response.sendRedirect("listar");
	}
	

}
