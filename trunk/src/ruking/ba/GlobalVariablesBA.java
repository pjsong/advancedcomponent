package ruking.ba;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.context.Context;
import org.springframework.web.servlet.support.RequestContext;

import ruking.utils.RegExp;
import ruking.utils.Util;
import ruking.velocity.EscapeTool;
import ruking.velocity.MathTool;
import ruking.velocity.VelocityUtil;



public class GlobalVariablesBA {
	public void setCommonVariables(HttpServletRequest request, Context ctx) throws Exception
	{
        // regular expression class used for displaying price
        ctx.put("formatter", new Formatter());
        ctx.put("regExp", new RegExp());
        ctx.put("math", new MathTool());
        ctx.put("esc", new EscapeTool());
        ctx.put("velocityUtil", new VelocityUtil());

        // image server
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    String currYear = sdf.format(new Date());
        ctx.put("currYear", currYear);

//		// get session
//		SessionUtil sessUtil = new SessionUtil(RepDataSourceFactory.getMasterDataSource(), new MySQLRowMapper());
//    	Map<String, Object> sessData = (Map<String, Object>) request.getAttribute(SessionUtil.SESS_DATA);
//
//		SessionCustomerDTO sessionCustomerDTO = (SessionCustomerDTO) sessData.get(SessionName.customerDTO);
//		if(sessionCustomerDTO != null && sessionCustomerDTO.getClientId()>0)
//		{
//		    ctx.put("customer", sessionCustomerDTO);
//		    ctx.put("clientId", sessionCustomerDTO.getClientId());
//
//		    String username = sessionCustomerDTO.getLoginName();
//	    	if(Util.isAdministrator(username) == true)
//	    	{
//	    		ctx.put("adminflag", "true");
//	    	}
//		}
//		
//
		String s1 = request.getRequestURL().toString();
		String s2=request.getRequestURI().substring(0);
		String s3=request.getContextPath();
		String s4 = s1.substring(0,8)+s1.substring(8).replace(s2, s3);
        ctx.put("_url", s4);
//		ctx.put("_secureUrl", Util.getHttpsBaseUrl(request));
//
//		boolean isSecurePage = false;
//    	String servletPath = request.getServletPath();		
//    	if (servletPath.equals("/checkout.jhtml") || servletPath.equals("/checkoutshipping.jhtml") ||
//	    		servletPath.equals("/checkoutbilling.jhtml") || servletPath.equals("/checkoutpayment.jhtml") ||
//	    		servletPath.equals("/orderreview.jhtml") || servletPath.equals("/ordercomplete.jhtml") || 
//	    		servletPath.equals("/wishlist.jhtml") || servletPath.equals("/registration_account.jhtml") || 
//	    		servletPath.equals("/myaccount.jhtml") || servletPath.equals("/login.jhtml") || 
//	    		servletPath.equals("/order_list.jhtml") || servletPath.equals("/order_detail.jhtml") ||
//	    		servletPath.equals("/myinfo.jhtml") || servletPath.equals("/addressBook.jhtml") ||
//	    		servletPath.equals("/editAddressBook.jhtml") || servletPath.equals("/wishlist_lists.jhtml") ||
//	    		servletPath.equals("/wishlist_item.jhtml") || servletPath.equals("/forgot_password.jhtml")
//	    	)
//    	{
//    		isSecurePage = true;
//    	}
//    	ctx.put("isSecurePage", isSecurePage);
//
//		if (Config.getSetting("livechat").equals("true"))
//		{
//			// disable live chat for the checkout pages for now
//	    	if (isSecurePage) 
//	    	{
//	    		ctx.put("chat", Boolean.FALSE);
//	    	}
//	    	else
//	    	{
//	    		ctx.put("chat", Boolean.TRUE);
//	    	}
//		}
//
//		// get deployTarget
//		if (Config.getRunLevel() == RunLevel.DEMO)
//		{
//			// get x-forwarded-host Apache header
//			String xForwardedHost = request.getHeader("x-forwarded-host");
//			ctx.put("deployTarget", xForwardedHost.substring(0, xForwardedHost.indexOf(".")));
//		}
//		else
//		{
//			ctx.put("deployTarget", Config.getRunLevel().name());
//		}
//
//		// if production site, turn on tracking
//		if (Config.getRunLevel() == RunLevel.REL)
//		{
//			ctx.put("track", Boolean.TRUE);
//		}
//		else
//		{
//			ctx.put("track", Boolean.FALSE);
//		}
//
//		// check the ip address to see if it is internal ip
//		if (Util.isInternalIP(request))
//		{
//			ctx.put("isInternalIP", Boolean.TRUE);
//		}
//		else
//		{
//			ctx.put("isInternalIP", Boolean.FALSE);
//		}
//		
//		// put referralbaseurl into the ctx
//		String referralBaseUrl = sessData.get(SessionUtil.REFERRAL_BASE_URL) == null ? "" : (String) sessData.get(SessionUtil.REFERRAL_BASE_URL);
//		ctx.put("referralBaseUrl", referralBaseUrl);
//
//		// mini basket
//		MiniBasketBA minibasketBA = new MiniBasketBA();
//		BasketDetailDTO details = minibasketBA.getFromSession(sessData);
//		MiniBasketDTO miniBasket = new MiniBasketDTO();
//		miniBasket.setDetails(details);
//		miniBasket = minibasketBA.setTotalPrice(miniBasket);
//		miniBasket = minibasketBA.setTotalQTY(miniBasket);
//		ctx.put("miniBasketDTO", miniBasket);
//		
//		ctx.put("totalProductCount", IndexReaderFactory.getTotalProductCount());
//
//		if(sessionCustomerDTO != null && sessionCustomerDTO.getClientId() > 0)
//		{
//			Integer wishlistCount = (Integer) sessData.get(SessionName.wishlistCount);
//			if (wishlistCount == null)
//			{
//				wishlistCount = new WishListDAO().getAllWishlistItemsCount(sessionCustomerDTO.getClientId());
//				sessData.put(SessionName.wishlistCount, wishlistCount);
//			}
//			ctx.put("wishlistCount", wishlistCount);
//		}
//
//		// cobranding logo logic
//		String homepageCoBr = (String) sessData.get(SessionName.homepageCoBr);
//		if (homepageCoBr == null)
//		{
//			homepageCoBr = initCoBrand(referralBaseUrl, request);
//			if (homepageCoBr != null)
//			{
//				sessData.put(SessionName.homepageCoBr, homepageCoBr);
//			}
//		}
//		ctx.put("homepageCoBr", homepageCoBr);
//    	
//    	// relative url
//    	String relativeUrl = Util.getRelativeUrl(request);
//    	ctx.put("relativeUrl", relativeUrl);
//    	
//    	// promo list
//    	ctx.put("promos", new Promos());
//    	
//    	ctx.put("promoNavs", IndexReaderFactory.getPromoList());
//    	
//    	//recently viewed products
//		ctx.put("recentlyViewed", getRecentlyViewedProducts(sessData));
//
//		Map model=new HashMap();
//		for(Object key:ctx.getKeys())model.put(key, ctx.get((String)key));
//    	ctx.put("springMacroRequestContext", new RequestContext(request, model));
//
//    	boolean isPeciCustomer = !Util.getNoNull(sessData, SessionName.peci).equals("");
//    	ctx.put("isPeciCustomer", isPeciCustomer);
//    	
//    	sessUtil.write(request, sessData);
//	}
	}
}
