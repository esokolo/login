package Lab4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Welcome")
public class Welcome extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet( HttpServletRequest request,
                          HttpServletResponse response )
            throws ServletException, IOException
    {
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html>" );
        out.println( "<head><title>Member-Only " +
                "Area</title></head>" );
        out.println( "<body>" );
        out.println( "<h1>Member-Only Area!</h1>" );
        out.println( "</body></html>" );
    }

    protected void doPost( HttpServletRequest request,
                           HttpServletResponse response ) throws ServletException, IOException
    {
        doGet( request, response );
    }

}
