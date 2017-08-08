package id.rojak.auth.application.representation;

import id.rojak.auth.application.command.RegisterUserCommand;
import id.rojak.auth.domain.model.identity.User;

/**
 * Created by inagi on 8/7/17.
 */
public interface IIdentityApplicationService {

    User newUser(RegisterUserCommand aCommand);
}
