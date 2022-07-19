package dragonb.bearfamily.backend.controller;

import java.lang.annotation.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ApiResponses(value = {

    @ApiResponse(responseCode = "200", description = "Success", content = @Content),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    @ApiResponse(responseCode = "401", description = "Unauthenticated", content = @Content),
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)

})
public @interface SwaggerInterface {
    
}
