package com.member.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException ;
	
}