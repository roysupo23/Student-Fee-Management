package com.java.student.fee.management.mapper;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.java.student.fee.management.common.ErrorCode;

public class MobileNumberDeserializer extends StdDeserializer<String>{
	
	private static final long serialVersionUID = -1153943439343804218L;

	@Value("${app.student.mobile.pattern}")
	String mobilePattern;
	
	public MobileNumberDeserializer() {
		this(null);
	}
	
	protected MobileNumberDeserializer(Class<Date> dd) {
		super(dd);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String mobileNumber = p.getText();
		// For jUnit Testing
		if(StringUtils.isBlank(mobilePattern)) {
			mobilePattern = "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
		}
        if(!Pattern.matches(mobilePattern, mobileNumber)) {
        	throw new IOException(ErrorCode.INVALID_MOBILE_NUMBER);
        }
        return mobileNumber;
	}
}
