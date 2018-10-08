package com.shangho.blackcore.api.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.shangho.blackcore.api.response.Apidocument;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public abstract class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 5065418691737915280L;
	private final static Logger logger = LoggerFactory.getLogger(APIServlet.class);

	public APIServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());

		final String error = "HTTP Method Fail";

		final Apidocument apiDoc = new Apidocument(APIStatus.HTTP_METHOD_FAIL, error);

		if (apiDoc != null) {
			try {
				resp.getWriter().write(new Gson().toJson(apiDoc));
			} catch (IOException e) {
			} finally {
				LogAction.getInstance().writeRecord(APIStatus.HTTP_METHOD_FAIL, error);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		resp.setContentType("application/json; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		SHException ex = null;
		int status = APIStatus.SUCCESS;
		Object obj = null;
		Apidocument apiDoc = null;
		try {

			initial();

			if (!LogAction.getInstance().isSettingLog()) {
				apiDoc = new Apidocument(APIStatus.LOG_NOT_SETTING, "Log is not initial");

				resp.getWriter().write(new Gson().toJson(apiDoc));

				return;
			}

			final String apiRequest = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);

			obj = execute(apiRequest, req);

			apiDoc = new Apidocument(APIStatus.SUCCESS, APIStatus.MSG_SUCCESS, obj);

		} catch (SHException e) {
			ex = e;
			LogAction.getInstance().error(e.getMessage(), e);
		} catch (Exception e) {
			ex = new SHException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
			LogAction.getInstance().error(e.getMessage(), e);
		} catch (Throwable e) {
			ex = new SHException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
			LogAction.getInstance().error(e.getMessage(), e);
		} finally {

			LogAction.getInstance().setRemoteIP(req.getRemoteAddr());

			if (ex != null) {
				status = ex.getErrorCode();
				apiDoc = new Apidocument(ex.getErrorCode(), ex.getMessage());

				LogAction.getInstance().writeRecord(status, ex.getMessage());
			} else {
				LogAction.getInstance().writeRecord(status, APIStatus.MSG_SUCCESS);
			}

			if (apiDoc != null) {
				try {
					resp.getWriter().write(new Gson().toJson(apiDoc));
				} catch (IOException e) {
				}
			}

		}

	}

	protected abstract Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception;

	protected abstract void initial() throws SHException, Exception;

}
