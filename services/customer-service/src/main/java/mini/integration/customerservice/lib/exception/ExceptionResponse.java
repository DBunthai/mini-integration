package mini.integration.customerservice.lib.exception;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {

    private HttpStatusCode code;


    @JsonGetter
    public Integer httpCode() {
        if (Objects.nonNull(code)) {
            return code.value();
        }
        return null;
    }

    private List<ExceptionDetailResponse> errors;
}
