package ruking.controller.manage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.springframework.web.bind.ServletRequestDataBinder;

import ruking.ba.GlobalVariablesBA;
import ruking.controller.BaseController;
import ruking.dao.ProductDAO;
import ruking.dto.ProductDTO;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

public class EditProductController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String act= Util.getNoNull(request.getParameter("act"));
		VelocityContext vc=new VelocityContext();
    	ProductDAO pDAO = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
       if(act.equals("")){
            new GlobalVariablesBA().setCommonVariables(request, vc);
        	vc.put("act", "add");
            VelocityParserFactory.getVP().render("editproduct", vc, request, response);
            return;
        }
        if(act.equals("add")){
        	add(request,response);
        	return;
        }
        if(act.equals("edit")){
            new GlobalVariablesBA().setCommonVariables(request, vc);
        	vc.put("act", "update");
            String id= Util.getNoNull(request.getParameter("id"));
	    	ProductDTO pDTO = pDAO.getProductByID(id);
        	vc.put("product", pDTO);
            VelocityParserFactory.getVP().render("editproduct", vc, request, response);
            return;
        }
        if(act.equals("update")){
            update(request,response);
            return;
        }
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception{
		VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
    	ProductDTO product = new ProductDTO();  
		ServletRequestDataBinder binder = new ServletRequestDataBinder(product, "product");
		binder.bind(request);
    	ProductDAO pDAO = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
		Map<String,String> error = check(product,pDAO);
		if(error.size()>0){
			vc.put("error", error);
			vc.put("act", "add");
			vc.put("product", product);
			VelocityParserFactory.getVP().render("editproduct", vc, request, response);
			return;
		}else{
	    	pDAO.insertProduct(product);
	    	response.sendRedirect("/listproducts.jhtml");
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        String id= Util.getNoNull(request.getParameter("id"));
    	ProductDAO pDAO = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
    	ProductDTO product = pDAO.getProductByID(id);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(product, "product");
		binder.bind(request);
		Map<String,String> error = check(product,pDAO);
		if(error.size()>0){
			vc.put("error", error);
			vc.put("act", "update");
			vc.put("product", product);
			VelocityParserFactory.getVP().render("editproduct", vc, request, response);
			return;
		}else{
	    	pDAO.updateProduct(product);
	    	response.sendRedirect("/listproducts.jhtml");
		}
	}
	
	private Map<String,String> check(ProductDTO p,ProductDAO pDAO) throws SQLException{
		Map<String,String> error = new HashMap<String,String>();
		if(Util.getNoNull(p.getTitle()).length()<1)error.put("titleEmptyError", "输入产品名称");
		if(pDAO.productTitleExits(p.getTitle()))error.put("titleValueError", "产品名称已存在");
		if(p.getTitle().length()>98)error.put("titleLengthError", "产品名称太长");
		if(Util.getNoNull(p.getCategory()).length()<1)error.put("categoryLengthError", "输入类别名称");
		if(p.getCategory().length()>98)error.put("categoryLengthError", "类别太长");
		if(p.getSubcategory().length()>98)error.put("subcategoryLengthError", "子类太长");
		return error;
	}
}