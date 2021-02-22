package com.smarthost.roommanager.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.smarthost.roommanager.model.Customer;

public class CustomerSerializer extends StdSerializer<Customer> {
	
	 public CustomerSerializer() {
	        this(null);
	    }
	
	 
	 public CustomerSerializer(Class<Customer> t) {
	        super(t);
	    }

    @Override
    public void serialize(Customer value, JsonGenerator jgen,
        SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("currency", value.getAmount().getCurrency().getCurrencyCode());
        jgen.writeNumberField("number", value.getAmount().getNumber().doubleValue());
        jgen.writeEndObject();
    }
}
