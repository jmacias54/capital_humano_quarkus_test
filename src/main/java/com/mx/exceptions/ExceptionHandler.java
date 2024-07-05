package com.mx.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.mx.model.response.ErrorResponse;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		exception.printStackTrace();

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(errorResponse)
			.build();
	}
}