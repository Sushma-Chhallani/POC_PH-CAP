package com.lti.poc.exception;

import java.util.Date;

public class ErrorDetails {
	private Date logTimeDate;
	  private String message;
	  private String details;

	  public ErrorDetails(Date timestamp, String message, String details) {
	    super();
	    this.logTimeDate = logTimeDate;
	    this.message = message;
	    this.details = details;
	  }
}