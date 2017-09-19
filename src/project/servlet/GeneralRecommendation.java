package project.servlet;

import project.function.FilterGenre;
import project.obj.MovieStat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class GeneralRecommadation
 */
@WebServlet("/GeneralRecommendation")
public class GeneralRecommendation extends HttpServlet{
public  GeneralRecommendation(){
        super();
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
        HttpSession session = request.getSession();
        List<MovieStat> data = (List<MovieStat>) session.getAttribute("top");
        List<String> genre = (List<String>) session.getAttribute("category");
        String[] category = request.getParameterValues("categoryCheck");
        FilterGenre genrefilter = new FilterGenre();
        List<MovieStat> states = genrefilter.filterGenre(data, java.util.Arrays.asList(category));
        request.setAttribute("top", states);
        System.out.println(states.size());
        request.setAttribute("category", genre);
        System.out.println(genre.size());
        request.getRequestDispatcher("results.jsp").forward(request, response);

    }

}
