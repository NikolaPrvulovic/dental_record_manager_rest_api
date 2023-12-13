package com.dentechsoft.recordmanagerrestapi.security;

import com.dentechsoft.recordmanagerrestapi.entity.Prosthetist;
import com.dentechsoft.recordmanagerrestapi.entity.Role;
import com.dentechsoft.recordmanagerrestapi.service.ProsthetistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ProsthetistService prosthetistService;

    public CustomAuthenticationSuccessHandler(ProsthetistService prosthetistService) {
        this.prosthetistService = prosthetistService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        System.out.println("In CustomAuthenticationSuccessHandler");

        String userName = authentication.getName();

        System.out.println("userName=" + userName);

        Prosthetist prosthetist = prosthetistService.findByUserId(userName);

        // Check if prosthetist is not null (found in the database)
        if (prosthetist != null) {
            // now place in the session
            HttpSession session = request.getSession();
            session.setAttribute("prosthetist", prosthetist);

            // You can also retrieve and set roles in the session if needed
            Collection<Role> roles = prosthetist.getRoles();
            List<String> roleNames = roles.stream().map(Role::getRole).collect(Collectors.toList());
            session.setAttribute("roles", roleNames);

            System.out.println("Roles: " + roleNames);

        }

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}

