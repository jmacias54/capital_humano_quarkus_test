package com.mx.exceptions;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.mx.model.response.ErrorResponse;


@Provider
public class BadRequestExceptionHandler implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException exception) {
		exception.printStackTrace();

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

		return Response.status(Response.Status.BAD_REQUEST)
			.entity(errorResponse)
			.build();
	}
}
