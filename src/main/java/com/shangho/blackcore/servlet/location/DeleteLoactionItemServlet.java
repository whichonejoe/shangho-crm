package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.DeleteLocationItemProcess;
import com.shangho.blackcore.api.location.request.DeleteLocationItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/item/delete")
public class DeleteLoactionItemServlet extends APIServlet {
	private static final long serialVersionUID = -7495366797877763123L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteLoactionItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteLocationItemRequest entity = (DeleteLocationItemRequest) APIParser.getInstance().parse(apiRequest,
				DeleteLocationItemRequest.class);
		return new DeleteLocationItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
