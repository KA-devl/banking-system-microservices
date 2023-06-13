package project.departmentservice.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionRepresentation {

    private String errorMessage;

    private String errorCode;

    //private Set<String> validationErrors;

}
