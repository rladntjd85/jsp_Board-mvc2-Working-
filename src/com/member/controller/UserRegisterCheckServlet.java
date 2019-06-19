package com.member.controller;
 
import java.io.IOException;
 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.MDao;
 
@WebServlet("/UserRegisterCheckServlet")
public class UserRegisterCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
 
        String userID = request.getParameter("userID");
        // getWirter로 registerCheck를 한 후의 값을 받아옴 -> function(result)이렇게 되어있으니까 자동으로 결과값이 result에 저장
        // registerCheck이 int형을 반환 -> ""공백을 넣어줌으로써 성공적으로 문자열 형태로  출력
        response.getWriter().write(new MDao().ConfirmId(userID) + "");
    }
}

