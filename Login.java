package Lab4;

import models.Lab4User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@WebServlet(urlPatterns = "/Login", loadOnStartup = 1)
public class Login extends HttpServlet
{
    boolean invalidUsername;

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        invalidUsername = false;

        List<Lab4User> registeredUsers = new ArrayList<Lab4User>();
        registeredUsers.add(new Lab4User("John Doe", "john",
                "abcd"));
        registeredUsers.add(new Lab4User("Mary Jane", "mary",
                "abcd"));
        registeredUsers.add(new Lab4User("Joe Boxer", "joe",
                "abcd"));

        getServletContext().setAttribute("registeredUsers",
                registeredUsers);
    }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response )
            throws ServletException, IOException
    {
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html>" );
        out.println( "<head><title>Login</title></head>" );
        out.println( "<body>" );

        if (invalidUsername)
            out.println( "INVALID USERNAME AND/OR PASSWORD" );

        out.println( "<form action='Login' method='post'>" );
        out.println( "Username: <input type='text' " +
                "name='username' /> <br />" );
        out.println( "Password: <input type='password' " +
                "name='password' /> <br />" );
        out.println( "<input type='checkbox' " +
                "name='checkBox' value='Remember'> " +
                "Remember <br />" );
        out.println( "<input type='submit' name='login' " +
                "value='Login' />  <br />" );

        out.println( "<p><a href=\"registrationPage\">" +
                "Registration Page</a></p>" );
        out.println( "</form>" );

        out.println( "</body></html>" );
    }

    protected void doPost( HttpServletRequest request,
                           HttpServletResponse response )
            throws ServletException, IOException
    {
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        String remember = request.getParameter("checkBox");
        List<Lab4User> lab4Users = (List<Lab4User>) getServletContext().
                getAttribute("registeredUsers");

        for (Lab4User element : lab4Users)
            if (element.getUsername().equals(username)
                    && element.getPassword().equals(password))
            {
                request.getSession().setAttribute( "currentUser", element );
                response.sendRedirect( "Welcome" );

                if (remember != null)
                {
                    Cookie cookie = new Cookie( "username", username );
                    response.addCookie(cookie);
                }

                return;
            }

        invalidUsername = true;
        response.sendRedirect( "Login" );
    }
}