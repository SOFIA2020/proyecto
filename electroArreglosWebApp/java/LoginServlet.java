@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Verificar las credenciales (ejemplo básico)
        if (email.equals("admin@ejemplo.com") && password.equals("adminpassword")) {
            // Credenciales válidas, redirigir a la página de gestión
            response.sendRedirect("gestion.jsp"); // Página de gestión
        } else {
            // Credenciales inválidas, volver a la página de login
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
