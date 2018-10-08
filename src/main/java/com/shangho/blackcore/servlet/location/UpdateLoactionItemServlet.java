package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.UpdateLocationItemProcess;
import com.shangho.blackcore.api.location.request.UpdateLocationItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/item/update")
public class UpdateLoactionItemServlet extends APIServlet {
	private static final long serialVersionUID = 5896115227369861114L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateLoactionItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateLocationItemRequest entity = (UpdateLocationItemRequest) APIParser.getInstance().parse(apiRequest,
				UpdateLocationItemRequest.class);
		return new UpdateLocationItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
