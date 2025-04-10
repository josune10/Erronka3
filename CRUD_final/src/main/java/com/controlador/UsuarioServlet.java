package com.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modelo.Usuario;
import com.modelo.UsuarioDAO;
/**
 * Servlet implementation class UserServlet
 * 
 * @author = Iker Coranti Gabilondo (@iCrouk)
 */
@WebServlet("/")
//@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* 1- DAO class bat behar dugu.*/
	private UsuarioDAO miUsuarioDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        this.miUsuarioDAO = new UsuarioDAO();
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
		//String action2 = request.getPathInfo();
		HttpSession sesion=request.getSession();
		
		String nombre = sesion.getAttribute("nombre").toString();

		System.out.println("sessionUsuarioServlet ="+nombre);
		if (nombre != null){
		
			System.out.println("action = "+action);
			//System.out.println("action2 = "+action2);
			if (action == null || action.equals("/")) {action = "/listar";}
			try {
				switch(action) {
					case "/nuevo":
						mostrarNuevoFromulario(request,response);
						break;
					case "/insertar":
						insertarUsuario(request,response);
						break;
					case "/eliminar":
						eliminarUsuario(request,response);
						break;
					case "/editar":
						mostrarFormularioParaEditar(request,response);
						break;
					case"/modificar":
						editarUsuarioExistente(request,response);
						break;
					case"/listar":
						listarTodosLosUsuarios(request,response);
						break;
					default:
						response.sendError(HttpServletResponse.SC_NOT_FOUND);
						break;
					}//EndSwitch
			}catch(SQLException eSQL){
				eSQL.getMessage();
			}//endCatch
		}else{
			response.sendRedirect("error.jsp");
		}
	}
	/*
	 * 1-(nuevo)
	 * Para Mostran el formulario para escribir nuevos datos.
	 */
	protected void mostrarNuevoFromulario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispacher = request.getRequestDispatcher("usuarioFormularioNuevo.jsp");
		dispacher.forward(request, response);
	}
	/* 2-(insertar)
	 * Insertara el nuevo usuario que escribamos en el formulario y volvera a la lista.
	 */
	protected void insertarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("nombre");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Usuario UsuNuevo = new Usuario(name, email, password);
		miUsuarioDAO.insertar(UsuNuevo);
		response.sendRedirect("listar");
	}
	/*
	 * 3--(eliminar)--
	 * Elimina una tupla usando el ID y vuelve a mostrat todas las tuplas de la tabla.
	 */
	protected void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("id"));
		miUsuarioDAO.eliminar(id);
		response.sendRedirect("listar");
	}
	/*
	 * 4-()
	 * Mostrara el fomulario de insertar nuevos usuarios, pero esta vez con datos ya que los pasmos
	 * como si fueran atributos.
	 */
	protected void mostrarFormularioParaEditar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuarioExistente = miUsuarioDAO.listarPorId(id);
		RequestDispatcher dispacher = request.getRequestDispatcher("usuarioFormularioNuevo.jsp");
		request.setAttribute("usuario", usuarioExistente);
		dispacher.forward(request, response);
	}
	/*
	 * 5()
	 * 
	 */
	protected void editarUsuarioExistente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name= request.getParameter("nombre");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Usuario miUsuario = new Usuario(id, name, email, password);
		miUsuarioDAO.actualizar(miUsuario);
		System.out.println("Editado");
		response.sendRedirect("listar");
	}
	/*
	 *  Listara todos las tuplas de una tabla.
	 */
		private void listarTodosLosUsuarios(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			ArrayList<Usuario> listarUsuarios = miUsuarioDAO.listarTodos();
			request.setAttribute("listarTodoLosUsuarios", listarUsuarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listarUsuarios.jsp");
			dispatcher.forward(request, response);
		}
}
