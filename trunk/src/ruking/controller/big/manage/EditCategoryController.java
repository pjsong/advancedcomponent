package ruking.controller.big.manage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.web.bind.ServletRequestDataBinder;

import ruking.ba.GlobalVariablesBA;
import ruking.controller.BaseController;
import ruking.dao.CategoryDAO;
import ruking.dao.CategoryDAO;
import ruking.dto.CategoryDTO;
import ruking.dto.CategoryDTO;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

public class EditCategoryController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String act= Util.getNoNull(request.getParameter("act"));
		VelocityContext vc=new VelocityContext();
   		vc.put("currentTab", "category_big");
    	CategoryDAO pDAO = new CategoryDAO();
       if(act.equals("")){
            new GlobalVariablesBA().setCommonVariables(request, vc);
        	vc.put("act", "add");
            VelocityParserFactory.getVP().render("editcategory_big", vc, request, response);
            return;
        }
        if(act.equals("add")){
        	add(request,response);
        	return;
        }
        if(act.equals("edit")){
            new GlobalVariablesBA().setCommonVariables(request, vc);
        	vc.put("act", "update");
            String id= Util.getNoNull(request.getParameter("pid"));
	    	CategoryDTO pDTO = pDAO.getCategoryByID(id,"big");
        	vc.put("product", pDTO);
            VelocityParserFactory.getVP().render("editcategory_big", vc, request, response);
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
   		vc.put("currentTab", "product_big");
   		CategoryDTO category = new CategoryDTO();  
		ServletRequestDataBinder binder = new ServletRequestDataBinder(category, "product");
		binder.bind(request);
		CategoryDAO pDAO = new CategoryDAO();
		Map<String,String> error = check(category,pDAO);
		if(error.size()>0){
			vc.put("error", error);
			vc.put("act", "add");
			vc.put("product", category);
			VelocityParserFactory.getVP().render("editcategory_big", vc, request, response);
			return;
		}else{
			category = pDAO.insertCategory(category,"big");
	    	response.sendRedirect("/listproducts_big.jhtml");
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        String id= Util.getNoNull(request.getParameter("pid"));
    	CategoryDAO pDAO = new CategoryDAO();
    	CategoryDTO product = pDAO.getCategoryByID(id,"big");
    	String oldName = product.getCategory();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(product, "product");
		binder.bind(request);
		Map<String,String> error = updateCheck(product,pDAO,oldName);
		if(error.size()>0){
			vc.put("error", error);
			vc.put("act", "update");
			vc.put("product", product);
			VelocityParserFactory.getVP().render("editcategory_big", vc, request, response);
			return;
		}else{
			pDAO.updateCategory(product,id,"big");
	    	response.sendRedirect("/listproducts_big.jhtml");
		}
	}
	
	private Map<String,String> check(CategoryDTO p,CategoryDAO pDAO) throws SQLException{
		Map<String,String> error = new HashMap<String,String>();
		if(Util.getNoNull(p.getCategory()).length()<1)error.put("categoryLengthError", "输入类别名称");
		if(p.getCategory().length()>98)error.put("categoryLengthError", "类别太长");
		if(p.getSubcategory().length()>98)error.put("subcategoryLengthError", "子类太长");
		return error;
	}
	private Map<String,String> updateCheck(CategoryDTO p,CategoryDAO pDAO,String oldName) throws SQLException{
		Map<String,String> error = new HashMap<String,String>();
		if(Util.getNoNull(p.getCategory()).length()<1)error.put("categoryLengthError", "输入类别名称");
		if(p.getCategory().length()>98)error.put("categoryLengthError", "类别太长");
		if(p.getSubcategory().length()>98)error.put("subcategoryLengthError", "子类太长");
		return error;
	}
}