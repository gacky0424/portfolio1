package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.SalesRecord;
import model.SalesRecordLogic;

@WebServlet("/RecordDeleteServlet")
public class RecordDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ログインしてるかどうか確認、していなかった場合はログインサイトに飛ばす
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login loginUser = (Login) session.getAttribute("loginUser");

		if (loginUser != null) {
			//response.sendRedirect();
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/recordCheck.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/NoAccountServlet");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext app = this.getServletContext();
		HttpSession session = request.getSession();
		//チェックボックスのリクエストパラメータを取得（チェックされた商品ID）
		String[] numbers = request.getParameterValues("checkbox");

		if (numbers == null) {
			session.setAttribute("resultMsg", "削除したいデータが選択されていません。あるいは削除できるデータがありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/recordDelete.jsp");
			dispatcher.forward(request, response);
			session.removeAttribute("resultMsg");
		}

		//チェックボックスの数値をデータ削除のメソッドの引数にし、削除を実行
		SalesRecordLogic salesRecordLogic = new SalesRecordLogic();
		String Msg = salesRecordLogic.executeDelete(numbers);

		//結果メッセージをセッションスコープに保存
		session.setAttribute("resultMsg", Msg);

		//更新するため、削除する前の記録データを破棄する
		app.removeAttribute("salesRecordList");

		//記録データを更新
		//記録データを格納するためのコレクションのインスタンスを生成し、データを得る
		List<SalesRecord> salsesRecordList = salesRecordLogic.executeFindAll();

		//記録データをアプリケーションスコープに保存
		app.setAttribute("salesRecordList", salsesRecordList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/recordCheck.jsp");
		dispatcher.forward(request, response);
		session.removeAttribute("resultMsg");
	}

}
