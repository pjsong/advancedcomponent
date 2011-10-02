package velocity;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;


public class VelocityParser
{
	private static VelocityEngine ve;
    
	static
	{
		try
		{
			ve = new VelocityEngine();
			ve.setProperty("input.encoding", "GB2312");
			ve.setProperty("output.encoding", "GB2312");
			// setting up file resource loader
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "static/velocity");

			// Jian: this is very important, otherwise, there will be out of memory error when a template
			// with #foreach loop is called and the #foreach loop iterates through million of times
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
			// ve.setProperty("file.resource.loader.modificationCheckInterval", "5");
		
			// velocimacro configuration
			ve.setProperty(RuntimeConstants.VM_LIBRARY, "spring.vm");
			
			// this setting is such that changing inline velocity macro will take effect
			// WITHOUT restarting the web server!
			ve.setProperty(RuntimeConstants.VM_PERM_ALLOW_INLINE_REPLACE_GLOBAL, "true");
	
			ve.setProperty(RuntimeConstants.VM_LIBRARY_AUTORELOAD, "true");
	
			ve.setProperty(RuntimeConstants.COUNTER_INITIAL_VALUE, "0");
			ve.setProperty(RuntimeConstants.PARSER_POOL_SIZE, "1");
	
			// comment this out if we want to enable logging
			ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");
	
			ve.setProperty(RuntimeConstants.RUNTIME_LOG_REFERENCE_LOG_INVALID, "true");
	
			ve.setProperty(RuntimeConstants.PARSE_DIRECTIVE_MAXDEPTH, "10");
	
			// Jian: this is very important, otherwise, there will be out of memory error when a template
			// with #foreach loop is called and the #foreach loop iterates through million of times
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
	
			ve.init();
		}
		catch (Exception e)
		{
            throw new RuntimeException(e);
		}
	}
	public static void render(String viewName, Context ctx, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		response.setContentType("text/html; charset=GB2312");

          // set the no cache control header, HTTP 1.1 standard
          //resp.setHeader("Cache-Control", "no-cache");
          // this is to allow user to go back to previous page after a post operation in IE
          // this fixes the error:
          //  Error Message: Warning: Page Has Expired: The Page You Requested...
          response.setHeader("Cache-Control", "public");

          // set the no cache header, HTTP 1.0 standard
          response.setHeader("Pragma", "no-cache");

          // for user-agents that ignore the above settings
          response.setHeader("Expires", "Thu, 01 Jan 1970 00.00.00 GMT");

        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), "GB2312");
        ve.mergeTemplate(viewName + ".vm", "GB2312", ctx, out);
		out.flush();
	}	
}