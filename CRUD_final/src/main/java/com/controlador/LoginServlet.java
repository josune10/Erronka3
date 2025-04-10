package com.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modelo.UsuarioDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO miUsuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        miUsuarioDAO = new UsuarioDAO();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1- coger los parametros del Login
		
		String usuarioLogin = request.getParameter("email");
		String passwordLogin = request.getParameter("password");
		
		//System.out.println(usuarioLogin+" - "+passwordLogin);

		//2- Hacer la validacion con un metodo de la Clase DAO. Devolvera TRUE si existe USU.
		boolean validar = miUsuarioDAO.loginUsuario(usuarioLogin, passwordLogin);
		System.out.println("validarLogin = "+validar);
		//3 - Si es TRUE SESSION y PASA A Listar.
		if (validar) {
			// Inician Session
			 HttpSession sesion=request.getSession();
			 System.out.println(usuarioLogin+" - "+passwordLogin);
	         sesion.setAttribute("nombre", usuarioLogin);
	         System.out.println("sessionLoginServlet ="+sesion);
	         response.sendRedirect("listarUsuarios.jsp");
	         //RequestDispatcher dispatcher = request.getRequestDispatcher("listarUsuarios.jsp");
	         //dispatcher.forward(request, response);
		}else {
			request.setAttribute("mensajeError", "Login incorrecto!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("formularioLogin.jsp");
            dispatcher.forward(request, response);
		}
	}
}