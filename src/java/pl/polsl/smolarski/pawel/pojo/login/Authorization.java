/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class which provides secure filtering
 * 
 * @author psmolarski
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class Authorization implements Filter {
    
    
	public Authorization() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
                        
            try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if (reqURI.contains("/login.xhtml")
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.contains("/public/")
					|| reqURI.contains("javax.faces.resource")
                            || reqURI.contentEquals("/"))
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/secure/login.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
    
}