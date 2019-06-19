package com.member.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.MDao;

public class MemberLoginCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		MDao dao = new MDao();
		int check = dao.LoginCheck(userID, userPassword);
		
		if(check == 0) {
			//비밀번호틀림
		}
		else if(check == -1) {
			//아이디없음
		}
		else if(check == 1) {
			//로그인
			session.setAttribute("userID", userID);
			request.setAttribute("userID", session);
		}
		
	}

}
