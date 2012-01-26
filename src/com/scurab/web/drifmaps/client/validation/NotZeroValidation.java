package com.scurab.web.drifmaps.client.validation;

import com.pietschy.gwt.pectin.client.form.validation.ValidationResultCollector;
import com.pietschy.gwt.pectin.client.form.validation.Validator;
import com.pietschy.gwt.pectin.client.form.validation.message.ErrorMessage;

public class NotZeroValidation implements Validator<Double>
{
	
	private String message;
	private String infoMessage;
	
	public NotZeroValidation(String message)
	{
		this(message, null);
	}

	public NotZeroValidation(String message, String infoMessage)
	{
		this.message = message;
		this.infoMessage = infoMessage;
	}

	@Override
	public void validate(Double value, ValidationResultCollector results)
	{
		if (value == 0)
			results.add(new ErrorMessage(message, infoMessage));
	}
}
