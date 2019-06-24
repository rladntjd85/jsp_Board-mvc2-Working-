package com.board.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.BDao;
import com.mvc.dao.MDao;
import com.mvc.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		
		String pageNumber = "1";
				
		if(request.getParameter("pageNumber") != null) {
			pageNumber = request.getParameter("pageNumber");
		}
		try {
			Integer.parseInt(pageNumber);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("messageType", "오류 메시지");
			request.setAttribute("messageContent", "오류 메시지");
			return;
		}
		
		System.out.println(":::::command는"+pageNumber);
		ArrayList<BDto> dtos = dao.list(pageNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("list", dtos);
	}
}