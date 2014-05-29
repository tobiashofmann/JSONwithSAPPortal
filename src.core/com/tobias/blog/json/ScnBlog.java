package com.tobias.blog.json;
 
import com.sapportals.portal.prt.component.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.tc.logging.Category;
import com.sap.tc.logging.Location;
import com.sapportals.portal.prt.component.AbstractPortalComponent;
import com.sapportals.portal.prt.component.IPortalComponentRequest;
import com.sapportals.portal.prt.component.IPortalComponentResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

public class ScnBlog extends AbstractPortalComponent
{

	private static final Location loc = Location.getLocation("com.tobias.blog");
	private static Category category = Category.getCategory(Category.APPLICATIONS, "com/tobias/blog");

	private enum Rest {
		GET, POST, PUT, DELETE;
	}

	public void doContent(IPortalComponentRequest request,
			IPortalComponentResponse response) {
		String subloc = "doContent";				
		loc.entering(subloc);
		HttpServletResponse servletResponse = request.getServletResponse(true);
		HttpServletRequest servletRequest = request.getServletRequest();
		String method = servletRequest.getMethod();
		Rest rest = Rest.valueOf(method);
		switch (rest) {
		case GET:
			try {
				PrintWriter out;
				out = servletResponse.getWriter();
				JSONObject retObj = new JSONObject();
				retObj.put("status", 1);
				servletResponse.setCharacterEncoding("UTF-8");
				servletResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
				out.write(retObj.toString());
				out.close();
			} catch (IOException e1) {
				loc.errorT(subloc, "IOException: {0}" , new Object[]{e1.getMessage()});
			}
			break;
		}
	}
}
