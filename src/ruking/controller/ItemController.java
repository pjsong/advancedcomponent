package ruking.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.dao.AttributeDAO;
import ruking.dao.ProductDAO;
import ruking.dto.ProductDTO;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

public class ItemController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        String id = Util.getNoNull(request.getParameter("id"));
        if(id.equals("") || !NumberUtils.isDigits(id)){
        	response.sendRedirect("/products.jhtml");
        	return;
        }
       	vc.put("id", id);
       	ProductDAO productDAO = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
       	ProductDTO pDTO = productDAO.getProductByID(id,"");
       	if(pDTO == null){
        	response.sendRedirect("/products.jhtml");
        	return;
       	}
       	AttributeDAO aDAO = new AttributeDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
        List<Map> attrs = aDAO.getAttributesByProductId(id,"");
        vc.put("pDTO", pDTO);
        vc.put("attrs", attrs);
       	VelocityParserFactory.getVP().render("item", vc, request, response);
	}
}