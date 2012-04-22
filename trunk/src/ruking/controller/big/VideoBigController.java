package ruking.controller.big;


import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.controller.BaseController;
import ruking.dao.ArticleDAO;
import ruking.dto.ArticleDTO;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VideoBigController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        String id = request.getParameter("id");
        Map selectedArticle = null;
        vc.put("articleDTO", selectedArticle);
        VelocityParserFactory.getVP().render("videos_big", vc, request, response);
	}
}