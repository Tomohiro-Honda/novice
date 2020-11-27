package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Product;
import model.logic.product.ProductSearchLogic;

/**
 * フリーワード検索するクラス
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		/**----------------------キーワード検索----------------------**/
		if(action==null) {
			//スペースが入っているところで区切る　区切った数だけリストに入れてDAOまで渡す。
			List<String> wordList = new ArrayList<String>();

			String keyWord = request.getParameter("keyWord");
			String searchWord = keyWord;//検索結果表示で使用する変数
			//全角スペースを半角スペースに変換
			keyWord = keyWord.replace("　", " ");

			//スペースが入ったところで区切ってリストに入れる
			while (keyWord.contains(" ")) {
				int cut = keyWord.indexOf(" ");
				String  word = keyWord.substring(0, cut);
				keyWord = keyWord.substring(cut+1);
				if(word.equals("") == false) { //先頭がスペースだった場合は文字数0なのでリストに入れない
					wordList.add(word);
				}
			}
			if(keyWord.equals("") == false) {//最後がスペースだった場合もリストに入れない
				wordList.add(keyWord);
			}

			ProductSearchLogic logic = new ProductSearchLogic();
			List<Product> productList = logic.search(wordList);
			request.setAttribute("productList", productList);
			request.setAttribute("searchWord", searchWord);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
			rd.forward(request, response);
		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		/**----------------------カテゴリー検索----------------------**/
		if(action.equals("category")) {
			String category = request.getParameter("category");
			String word = null;

			//カテゴリーごとにSQL条件検索で使うwordを設定
			switch(category) {
			case  "Tee":
				word = "TS";
				break;
			case "Case":
				word = "SC";
				break;
			case "PostCard":
				word = "PC";
				break;
			}

			ProductSearchLogic logic = new ProductSearchLogic();
			List<Product> productList = logic.cateSearch(word);

			request.setAttribute("productList", productList);
			request.setAttribute("category", category);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/searchCategory.jsp");
			rd.forward(request, response);

		}

	}

}
