package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープの破棄
		HttpSession session = request.getSession();
		session.invalidate();

		//トップ画に移動
		response.sendRedirect("/portfolio1/LoginServlet");
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		//dispatcher.forward(request, response);
	}

}
