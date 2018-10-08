package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.InsertLocationCategoryProcess;
import com.shangho.blackcore.api.location.request.InsertLocationCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/category/insert")
public class InsertLoactionCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 6633923994584651987L;
	private final static Logger logger = LoggerFactory.getLogger(InsertLoactionCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertLocationCategoryRequest entity = (InsertLocationCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, InsertLocationCategoryRequest.class);
		return new InsertLocationCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
