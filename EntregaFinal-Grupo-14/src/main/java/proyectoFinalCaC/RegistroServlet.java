package proyectoFinalCaC;

// Importes de Jackson - ORM
import com.fasterxml.jackson.databind.ObjectMapper;


// Importes de la biblioteca de Java...

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



@WebServlet("/registros/*")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private RegistroService registroService;
	    private ObjectMapper objectMapper;

	    @Override
	    public void init() throws ServletException {
	        registroService = new RegistroService();
	        objectMapper = new ObjectMapper();
	    }

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String pathInfo = req.getPathInfo();
	        try {
	            if (pathInfo == null || pathInfo.equals("/")) {
	                List<Registro> registros = registroService.getAllRegistros();
	                String json = objectMapper.writeValueAsString(registros);
	                resp.setContentType("application/json");
	                resp.getWriter().write(json);
	            } else {
	                String[] pathParts = pathInfo.split("/");
	                if (pathParts.length < 2) {
	                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de registro no proporcionado");
	                    return;
	                }
	                int id = Integer.parseInt(pathParts[1]);
	                Registro registro = registroService.getRegistroById(id);
	                if (registro != null) {
	                    String json = objectMapper.writeValueAsString(registro);
	                    resp.setContentType("application/json");
	                    resp.getWriter().write(json);
	                } else {
	                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Registro no encontrado");
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            throw new ServletException(e);
	        }
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        try {
	            Registro registro = objectMapper.readValue(req.getReader(), Registro.class);
	            registroService.addRegistro(registro);
	            resp.setStatus(HttpServletResponse.SC_CREATED);
	        } catch (SQLException | ClassNotFoundException e) {
	            throw new ServletException(e);
	        }
	    }

}
