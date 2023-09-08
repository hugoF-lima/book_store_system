package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAcessObjects.ContatoClienteDAO;

import acessoDadosBeans.ContatoClienteBean;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// busca o que foi escrito no formulário
		PrintWriter out = response.getWriter();
		
		// buscando os parâmetros no request
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String assunto = request.getParameter("assunto");
		String mensagem = request.getParameter("mensagem");
		
		
		
		// monta um objeto contato
		ContatoClienteBean ccb = new ContatoClienteBean();
		ccb.setNome_Cli_Contato(nome);
		ccb.setEmail_Cli_Contato(email);
		ccb.setAssunto_Cli_Contato(assunto);
		ccb.setMensagem_Cli_Contato(mensagem);
		
		// salva o contato
		ContatoClienteDAO.adicionarContato(ccb);
		
		// imprime o nome do contato que foi adicionado
		// camarada depois veja no banco se gravou certinho :)
		out.println("<html>");
		out.println("<body>");
		out.println("Contato: " + ccb.getNome_Cli_Contato() + " adicionado com sucesso !!!");
		out.println("</body>");
		out.println("</html>");
		//super.service(arg0, arg1);
	}

}
