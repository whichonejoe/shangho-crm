package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.InsertLocationItemProcess;
import com.shangho.blackcore.api.location.request.InsertLocationItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/item/insert")
public class InsertLoactionItemServlet extends APIServlet {
	private static final long serialVersionUID = 7336980233993222341L;
	private final static Logger logger = LoggerFactory.getLogger(InsertLoactionItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertLocationItemRequest entity = (InsertLocationItemRequest) APIParser.getInstance().parse(apiRequest,
				InsertLocationItemRequest.class);
		return new InsertLocationItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
