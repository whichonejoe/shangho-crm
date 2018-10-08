package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.UpdateLocationCategoryProcess;
import com.shangho.blackcore.api.location.request.UpdateLocationCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/category/update")
public class UpdateLoactionCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 6633923994584651987L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateLoactionCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateLocationCategoryRequest entity = (UpdateLocationCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateLocationCategoryRequest.class);
		return new UpdateLocationCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
