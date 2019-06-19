package com.member.controller;
 
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.MDao;
 
@WebServlet("/UserRegisterServlet") 
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // 
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
 
        String userID = request.getParameter("userID");
        String userPassword1 = request.getParameter("userPassword1");
        String userPassword2 = request.getParameter("userPassword2");
        String userName = request.getParameter("userName");
        String userAge = request.getParameter("userAge");
        String userGender = request.getParameter("userGender");
        String userEmail = request.getParameter("userEmail");
 
        if (userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("")
                || userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("")
                || userAge == null || userAge.equals("") || userGender == null || userGender.equals("")
                || userEmail == null || userEmail.equals("")) {
            request.getSession().setAttribute("messageType", "오류 메시지");
            request.getSession().setAttribute("messageContent", "입력되지 않은 칸이 있습니다.");
            response.sendRedirect("register.jsp");
            return;
        }
        if (!userPassword1.equals(userPassword2)) {
            request.getSession().setAttribute("messageType", "오류 메시지");
            request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
            response.sendRedirect("register.jsp");
            return;
        }
        
        int result = new MDao().join(userID, userPassword1, userName, userAge, userGender, userEmail);
        
        if (result == 1) {
            request.getSession().setAttribute("messageType", "성공 메시지");
            request.getSession().setAttribute("messageContent", "회원가입을 축하합니다.");
            session.setAttribute("userID", userID);
            request.setAttribute("userID", session);
            response.sendRedirect("register.jsp");
            return;
        } else {
            request.getSession().setAttribute("messageType", "오류 메시지");
            request.getSession().setAttribute("messageContent", "회원가입에 실패하였습니다.");
            response.sendRedirect("register.jsp");
            return;
        }
    }
}
