package com.java.student.fee.management.mapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeserializer extends StdDeserializer<String> {

	private static final long serialVersionUID = 1018235440080042157L;
	
	public DateDeserializer() {
		this(null);
	}

	protected DateDeserializer(Class<Date> dd) {
		super(dd);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");
		Date date = (Date) p.getCurrentValue();
		return dateFormatter.format(date);
	}

}
