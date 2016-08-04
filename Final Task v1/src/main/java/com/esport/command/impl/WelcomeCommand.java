package main.java.com.esport.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.esport.command.Command;

public class WelcomeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		       // QueryUtil.saveCurrentQueryToSession(request);
		       // String languageId = LanguageUtil.getLanguageId(request);
		       // setLocaleAttributes(request, languageId);

		      //  try {
		        //    ServiceFactory serviceFactory = ServiceFactory.getInstance();

//		            GenreService genreService = serviceFactory.getGenreService();
//		            List<Genre> genres = genreService.getTopPositionGenres(AMOUNT_OF_TOP_POSITION_GENRES, languageId);
//		            request.setAttribute("genres", genres);
//
//		            CountryService countryService = serviceFactory.getCountryService();
//		            List<Country> countries = countryService.getTopPositionCountries(AMOUNT_OF_TOP_POSITION_COUNTRIES, languageId);
//		            request.setAttribute("countries", countries);
//
//		            MovieService movieService = serviceFactory.getMovieService();
//		            List<Movie> movies = movieService.getRecentAddedMovies(AMOUNT_OF_RECENT_ADDED_MOVIES, languageId);
//		            request.setAttribute("movies", movies);
//
//		            CommentService commentService = serviceFactory.getCommentService();
//		            List<Comment> comments = commentService.getRecentAddedComments(AMOUNT_OF_RECENT_ADDED_COMMENTS, languageId);
//		            request.setAttribute("comments", comments);
//		        } catch (ServiceException e) {
//		            request.setAttribute("serviceError", true);
//		        }
		        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
		    }

}
