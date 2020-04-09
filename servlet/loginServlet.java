package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utente;
import model.Auto;
import model.Categoria;
import model.Cliente;
import model.Noleggio;
import database.Database;


@WebServlet("/loginservlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String admin_email="matteo.aiello@gmail.com";
	private String admin_password="progettofinaleGeneration";
	String risposta1_jsp;
	String risposta2_jsp;

    public loginServlet() {
        super();
    }
    


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/header.jsp").include(request,response);
		request.getServletContext().getRequestDispatcher("/jsp/login.jsp").include(request,response);
		request.getServletContext().getRequestDispatcher("/jsp/footer.jsp").include(request,response);
		System.out.println(request.getSession().getAttribute("utente")!=null);
		System.out.println(request.getSession().getAttribute("cliente")!=null);
		System.out.println(request.getSession().getAttribute("mail_admin")!=null);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		risposta1_jsp=request.getParameter("mail");
		risposta2_jsp=request.getParameter("password");
		//System.out.println(risposta1_jsp);
		//System.out.println(risposta2_jsp);
		//Cliente cliente=Database.getIstance().getCliente(risposta1_jsp,risposta2_jsp);
		//request.setAttribute("cliente",cliente);
		//System.out.println("OK!!!!!");
		if(admin_email.equals(risposta1_jsp)&&admin_password.equals(risposta2_jsp)){
			session.setAttribute("email_admin", request.getParameter("email"));
			System.out.println("OK malfidati!!!!!");
		}else if(Database.getIstance().getUtente(risposta1_jsp, risposta2_jsp)!=null)
		{
			Utente utente=Database.getInstance().getUtente(risposta1_jsp,risposta2_jsp);
			session.setAttribute("utente", utente);
			System.out.println("Ciao!!!");
		}else if(Database.getIstance().getCliente(risposta1_jsp, risposta2_jsp)!=null){
			Cliente cliente=Database.getInstance().getCliente(risposta1_jsp,risposta2_jsp);
			session.setAttribute("cliente",cliente);
			System.out.println("OK!!!!!");
		}else if(risposta1_jsp==null||risposta2_jsp==null)
		{
			String errore_null="Non hai inserito tutti i campi!!!";
			session.setAttribute("errore_null", errore_null);
		}
		else {
			String errore="siete scemi";
			session.setAttribute("errore", errore);
		}
		response.sendRedirect("logoutservlet");
	}
	/*public Utente checkUtente(String email,String password) {
		Utente utente=Database.getIstance().getUtente(email, password);
		return utente;
	}
	public Cliente checkCliente(String email,String password) {
		Cliente cliente=
	}*/
}

