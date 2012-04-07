package ar.edu.itba.paw.grupo1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

public class SessionFilter implements Filter {

	private UserService userService;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain next) throws IOException, ServletException {
		
		if (!(req instanceof HttpServletRequest)) {
			next.doFilter(req, resp);
		}
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession session = httpReq.getSession();
		
		Object userIdObj = session.getAttribute("userId");
		User user = null;
		
		if (userIdObj instanceof Integer) {
			user = userService.get((int) userIdObj);
		} else {

			Cookie username = getCookie(httpReq, "username");
			Cookie hash = getCookie(httpReq, "hash");
			
			if (username != null && hash != null) {
				user = userService.loginWithHash(username.getValue(), hash.getValue());
				
				if (user != null) {
					session.setAttribute("userId", user.getId());
				}
			}
		}
		
		if (user != null) {
			req.setAttribute("user", user);
		}
		
		next.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		userService = ApplicationContainer.get(UserService.class);
	}

	protected Cookie getCookie(HttpServletRequest req, String name) {
		
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		
		return null;
	}
	
}
