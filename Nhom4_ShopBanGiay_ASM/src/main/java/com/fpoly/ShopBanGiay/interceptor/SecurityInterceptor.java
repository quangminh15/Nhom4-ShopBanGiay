package com.fpoly.ShopBanGiay.interceptor;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class SecurityInterceptor implements HandlerInterceptor{
	
	@Autowired
    SessionService session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)	throws Exception {
		String uri = request.getRequestURI();
        NguoiDung user = (NguoiDung) session.getSessionAttribute("user");
        String error = "";
        if (user == null) {
            error = "NoLogin";
        } else if (!user.isVaitro() && uri.startsWith("/admin")) {
        	System.out.println("Có user");
            error = "AccessDenied";
        }

        if (error.length() > 0) {
        	session.setSessionAttribute(uri, error);
            response.sendRedirect("/errorpage?error=" + error);
            return false;
        }
        return true;
	}
}
