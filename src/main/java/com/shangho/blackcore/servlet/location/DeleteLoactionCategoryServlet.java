package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.DeleteLocationCategoryProcess;
import com.shangho.blackcore.api.location.request.DeleteLocationCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/category/delete")
public class DeleteLoactionCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -7495366797877763123L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteLoactionCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteLocationCategoryRequest entity = (DeleteLocationCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, DeleteLocationCategoryRequest.class);
		return new DeleteLocationCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
