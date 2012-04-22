package ruking.controller;


import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.dao.ArticleDAO;
import ruking.dto.ArticleDTO;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VideoController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        String id = request.getParameter("id");
        Map selectedArticle = null;
        vc.put("articleDTO", selectedArticle);
        VelocityParserFactory.getVP().render("videos", vc, request, response);
	}
}