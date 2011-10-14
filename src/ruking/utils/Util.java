package ruking.utils;

import javax.servlet.http.HttpServletRequest;

public class Util {
	public static String getNoNull(Object o){
		return o==null?"":o.toString();
	}
	

	// get relative url
	// example: 
	// original: http://localhost:8080/products/indoor_lighting_chandeliers
	// relative: /products/indoor_lighting_chandeliers
	public static String getRelativeUrl(HttpServletRequest request) throws Exception
	{
		StringBuffer wholeUrl = request.getRequestURL();
		String queryString = request.getQueryString();
		int i = wholeUrl.indexOf("://");
		if (i > 0)
		{
			int j = wholeUrl.indexOf("/", i + 3);
			String url = wholeUrl.substring(j);
			if (queryString != null)
			{
				url += "?" + queryString;
			}
			return url;
		}
		else
		{
			// this should never happen though!
			return wholeUrl.toString();
		}
	}
}
