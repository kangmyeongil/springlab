package com.springlab.biz.board.controller2;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(
		urlPatterns = { "*.do" },
		initParams = {@WebInitParam(name = "view_prefix", value="/WEB-INF/view/"),
				@WebInitParam(name = "view_suffix", value=".jsp"),
				@WebInitParam(name = "handler_mapping_config", value="/WEB-INF/config/handler_mapping.properties")})
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HandlerMapping handlerMapping = null;
	private ViewResolver viewResolver = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletConfig config = getServletConfig();
		String handlerMappinConfig = config.getInitParameter("handler_mapping_config");
		handlerMappinConfig = getServletContext().getRealPath(handlerMappinConfig);
		handlerMapping = new HandlerMapping(handlerMappinConfig);
		viewResolver = new ViewResolver();
		viewResolver.setPrefix(getInitParameter("view_prefix"));
		viewResolver.setSuffix(getInitParameter("view_suffix"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// step #1. execute common functions
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
//		System.out.println(uri+"\n"+path);
		
		String viewName = null;
		// step #2. get controller and call handle method
		Controller controller = handlerMapping.getController(path);
		
		
		// step #3. forward to view object(jsp)
		
		if(controller != null) {
			viewName = controller.handleRequest(request,  response);
			
			if(viewName.contains("redirect:")) {
				response.sendRedirect(viewName.substring(viewName.lastIndexOf(":")+1));
			}
			else {
				viewName = viewResolver.getView(viewName);
				jakarta.servlet.RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
