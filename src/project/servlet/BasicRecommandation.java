package project.servlet;

import project.function.TopMovies;
import project.obj.MovieStat;
import project.query.Indexer;
import project.service.GenreService;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class BasicRecommandation
 */
@WebServlet("/BasicRecommandation")
public class BasicRecommandation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public BasicRecommandation() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        Indexer index=new Indexer();
        if (!index.checkIndex()){
            index.createIndex();
        }
        TopMovies top = new TopMovies();
        GenreService genreserv = GenreService.getInstance();
        try {
            List<MovieStat> states = top.getTopMovies(username, "friends");
            List<String> category = new ArrayList<String>();
            for (int i = 0; i < states.size(); i++) {
                List<String> genres = genreserv.getGenres(states.get(i).getMovieid());
                category.addAll(genres);
            }

            List<String> temp=new ArrayList<String>();
            for(String i:category){
                if(!temp.contains(i)){
                    temp.add(i);
                }
            }

            request.setAttribute("top", states);
            request.setAttribute("category", temp);
            session.removeAttribute("top");
            session.setAttribute("top", states);
            session.removeAttribute("category");
            session.setAttribute("category", temp);
            request.getRequestDispatcher("results.jsp").forward(request, response);

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
