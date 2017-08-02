package id.rojak.auth.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by imrenagi on 5/14/17.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AuthApplication.class)
//@WebAppConfiguration
public class UserControllerTest {

//    private static final ObjectMapper mapper = new ObjectMapper();

//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }

//    @Test
//    public void shouldReturnCurrentUser() throws Exception {
//        mockMvc.perform(get("/users/current").principal(new UserPrincipal("test")))
//                .andExpect(jsonPath("$.name").value("test"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldCreateNewUser() throws Exception {
//        final User user = new User("imrenagi", "imrenagi", "imre", "nagi");
//        String json = mapper.writeValueAsString(user);
//
//        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldCreateNewUserWith3ValidFields() throws Exception {
//        final User user = new User("imrenagi", "imrenagi", "imre");
//        String json = mapper.writeValueAsString(user);
//
//        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldFailWhenThereIsNoUserData() throws Exception {
//        mockMvc.perform(post("/users")).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void shouldFailWhenTheUserDataIsIncomplete() throws Exception {
//        final User user = new User();
//        user.setUsername("imrenagi");
//        user.setPassword("imrenagi");
//        String json = mapper.writeValueAsString(user);
//
//        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isBadRequest());
//    }

}