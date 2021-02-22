package com.smarthost.roommanager.config;

import java.io.IOException;

import javax.money.Monetary;

import org.javamoney.moneta.FastMoney;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.smarthost.roommanager.model.Customer;

public class CustomerDeserializer extends StdDeserializer<Customer> {

	public CustomerDeserializer() {
		this(null);
	}
	
	public CustomerDeserializer (Class<?> vc) {
		super(vc);
	}
	
	 @Override
	    public Customer deserialize(JsonParser jp, DeserializationContext ctxt) 
	      throws IOException {
	        JsonNode node = jp.getCodec().readTree(jp);
	        String currency = node.get("amount").get("currency").asText();
	        Number number = node.get("amount").get("number").numberValue();
	        if(currency == null || number == null)
	        	//throw new NullPointerException();
	        	number = -1;
	        return new Customer(FastMoney.of(number, Monetary.getCurrency(currency)));
	    }
}
