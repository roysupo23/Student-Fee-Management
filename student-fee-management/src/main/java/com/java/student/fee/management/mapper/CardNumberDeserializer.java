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

public class CardNumberDeserializer extends StdDeserializer<String>{

	private static final long serialVersionUID = 6685380215684444509L;
	
	@Value("${pp.fee.card.pattern}")
	private String cardPattern;
	
	public CardNumberDeserializer() {
		this(null);
	}

	protected CardNumberDeserializer(Class<Date> dd) {
		super(dd);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		
		String cardNumber = p.getText();
		
		// For jUnit Testing
		if(StringUtils.isBlank(cardPattern)) {
			cardPattern = "^[0-9]+$";
		}
        if(!Pattern.matches(cardPattern, cardNumber)) {
        	throw new IOException(ErrorCode.INVALID_MOBILE_NUMBER);
        }
        return cardNumber;
	}
}
