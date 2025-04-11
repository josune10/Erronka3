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

import com.modelo.Erabiltzailea;
import com.modelo.ErabiltzaileaDAO;
import com.modelo.Erabiltzailea;
import com.modelo.ErabiltzaileaDAO;

/**
 * Servlet implementation class ErabiltzaileaServlet
 */
@WebServlet("/ErabiltzaileaServlet")
public class ErabiltzaileaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ErabiltzaileaDAO miErabiltzaileaDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErabiltzaileaServlet() {
        super();
        this.miErabiltzaileaDAO = new ErabiltzaileaDAO();
        // TODO Auto-generated constructor stub
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		//String action2 = request.getPathInfo();
		HttpSession sesion=request.getSession();
		
		String email = sesion.getAttribute("email").toString();

		System.out.println("sessionErabiltzaileaServlet ="+email);
		if (email != null){
		
			System.out.println("action = "+action);
			if (action == null || action.equals("/")) {action = "/listar";}
			try {
				switch(action) {
					case "/nuevo":
						mostrarNuevoFormulario(request,response);
						break;
					case "/insertar":
						gehituErabiltzailea(request,response);
						break;
					case "/eliminar":
						ezabatuErabiltzailea(request,response);
						break;
					case "/editar":
						mostrarFormularioParaEditar(request,response);
						break;
					case"/modificar":
						editarErabiltzaileaExistente(request,response);
						break;
					case"/listar":
						listarTodosLosErabiltzaileak(request,response);
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
	
	protected void mostrarNuevoFormulario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("erabiltzaileaFormularioNuevo.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void gehituErabiltzailea(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String email = request.getParameter("email");
		String pasahitza = request.getParameter("pasahitza");
		Erabiltzailea ErabBerria = new Erabiltzailea (email, pasahitza);
		miErabiltzaileaDAO.insertar(ErabBerria);
		response.sendRedirect("listar");
	}
	
	protected void ezabatuErabiltzailea(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("id"));
		miErabiltzaileaDAO.eliminar(id);
		response.sendRedirect("listar");
	}
	
	protected void mostrarFormularioParaEditar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Erabiltzailea erabiltzaileaExistente = miErabiltzaileaDAO.listarPorId(id);
		RequestDispatcher dispacher = request.getRequestDispatcher("usuarioFormularioNuevo.jsp");
		request.setAttribute("erabiltzailea", erabiltzaileaExistente);
		dispacher.forward(request, response);
	}
	
	protected void editarErabiltzaileaExistente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String email= request.getParameter("email");
		String pasahitza=request.getParameter("pasahitza");
		Erabiltzailea miErabiltzailea = new Erabiltzailea(id, email, pasahitza);
		miErabiltzaileaDAO.actualizar(miErabiltzailea);
		System.out.println("Editado");
		response.sendRedirect("listar");
	}
	
		private void listarTodosLosErabiltzaileak(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			ArrayList<Erabiltzailea> listarErabiltzaileak = miErabiltzaileaDAO.listarTodos();
			request.setAttribute("listarTodosLosErabiltzaileak", listarErabiltzaileak);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listarErabiltzaileak.jsp");
			dispatcher.forward(request, response);
		}
}
