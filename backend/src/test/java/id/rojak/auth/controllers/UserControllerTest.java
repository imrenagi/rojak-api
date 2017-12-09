package id.rojak.auth.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;
import id.rojak.Application;
import id.rojak.auth.application.command.RegisterUserCommand;
import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.domain.model.identity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by imrenagi on 5/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private UserController userController;

    @Mock
    private IdentityApplicationService identityApplicationService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    public void shouldReturnCurrentUser() throws Exception {
        mockMvc.perform(get("/users/current").principal(new UserPrincipal("test")))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRegisterNewUserWithValidFields() throws Exception {
        RegisterUserCommand command =
                new RegisterUserCommand(
                        "admin",
                        "Password01",
                        "Admin",
                        "Admin",
                        true, null, null,
                        "rojak.admin@gmail.com",
                        "021-856-9876",
                        "021-856-9876",
                        "Jl. Pegangsaan Timur",
                        "Jakarta Selatan",
                        "DKI Jakarta",
                        "25134",
                        "Indonesia");
        String json = mapper.writeValueAsString(command);

        User user = new User("admin",
                "Password01",
                new Enablement(true, null, null),
                new Person(
                        new FullName("Admin", "Admin"),
                        new ContactInformation(
                                new EmailAddress("rojak.admin@gmail.com"),
                                new PostalAddress(
                                        "Jl. Pegangsaan Timur",
                                        "Jakarta Selatan",
                                        "DKI Jakarta",
                                        "25134",
                                        "Indonesia"),
                                new Telephone("021-856-9876"),
                                new Telephone("021-856-9876"))));

        when(this.identityApplicationService
                .newUser(any(RegisterUserCommand.class))).thenReturn(user);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldReturnBadRequestIfGetException() throws Exception {

        RegisterUserCommand command =
                new RegisterUserCommand(
                        "admin",
                        "Password01",
                        "Admin",
                        "Admin",
                        true, null, null,
                        "rojak.admin@gmail.com",
                        "021-856-9876",
                        "021-856-9876",
                        "Jl. Pegangsaan Timur",
                        "Jakarta Selatan",
                        "DKI Jakarta",
                        "25134",
                        "Indonesia");
        String json = mapper.writeValueAsString(command);

        when(this.identityApplicationService.newUser(any(RegisterUserCommand.class)))
                .thenThrow(new IllegalArgumentException("This is failed"));

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
    
}