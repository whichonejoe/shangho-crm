package com.shangho.blackcore.api.parser;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shangho.blackcore.api.request.APIRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.time.JsonDateDeserializer;
import com.shangho.utils.time.TimeStandardSetting;

public class APIParser<T> {
	private int page;
	private int limit;

	private final static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer())
			.setDateFormat(TimeStandardSetting.STANDARD_TIMESTAMP).create();

	private APIParser() {
	}

	private static class LazyHolder {
		public static final APIParser INSTANCE = new APIParser();
	}

	public static APIParser getInstance() {
		return LazyHolder.INSTANCE;
	}

	public <T> T parse(String request, Class<T> clazz) throws SHException {

		if (StringUtils.isBlank(request)) {
			return null;
		}

		APIRequest message = gson.fromJson(request, APIRequest.class);

		page = message.getPage();
		limit = message.getLimit();

		LogAction.getInstance().debug("Parser Request=" + gson.toJson(message.getContent()));

		return gson.fromJson(gson.toJson(message.getContent()), clazz);

	}

	public <T> T nonNormalParse(String request, Class<T> clazz) throws SHException {

		if (StringUtils.isBlank(request)) {
			return null;
		}

		LogAction.getInstance().debug("Parser Request=" + request);

		return gson.fromJson(request, clazz);

	}

	public int getPage() {
		return page;
	}

	public int getLimit() {
		return limit;
	}
}
