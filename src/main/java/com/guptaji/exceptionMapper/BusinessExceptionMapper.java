package com.guptaji.exceptionMapper;

import com.guptaji.customException.BusinessException;
import com.guptaji.dto.ErrorMessageDto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setStatus(exception.getStatus());
        errorMessageDto.setApiName(exception.getApiName());
        errorMessageDto.setMessage(exception.getMessage());
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessageDto)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
