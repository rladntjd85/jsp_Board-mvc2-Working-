package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.BDao;
import com.mvc.dto.BDto;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		String userID = request.getParameter("userID");
		BDao dao = new BDao();
		BDto parent = dao.contentView(bId);
		//dao.replyUpdate(parent);
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent, userID, parent);

	}

}
