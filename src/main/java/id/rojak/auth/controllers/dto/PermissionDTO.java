package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by inagi on 8/3/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO extends BasicDTO {

    public PermissionDTO() {
    }

    public PermissionDTO(String name,
                         String description,
                         Date createdDate,
                         Date updatedDate) {
        super(name, description, createdDate, updatedDate);
    }
}
