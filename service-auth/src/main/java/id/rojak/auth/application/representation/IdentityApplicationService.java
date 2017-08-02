package id.rojak.auth.application.representation;

import id.rojak.auth.domain.model.identity.*;
import id.rojak.auth.infrastructure.service.BcryptEncryptionService;
import id.rojak.auth.infrastructure.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by inagi on 8/1/17.
 */
@Service
public class IdentityApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User testing(String username, String password) {

//        String hash = "$2a$10$TY7zKY1IMuBbld3VZ.7xyOSVJRyezcfvF97u7oz/0Ibphs/Nt65ZC";
//        String pass = "Dinaryati1234_";
//
//        EncryptionService encryptionService = new BcryptEncryptionService();
//        System.out.println(encryptionService.matches(pass, hash));

//        User aUser = this.userRepository.findByUsername("imrenagi");
//
//        System.out.println("Get user with : " + aUser.username());

        User user = new User(
                username,
                password,
                Enablement.indefiniteEnablement(),
                new Person(
                        new FullName("Imre", "Nagi"),
                        new ContactInformation(
                                new EmailAddress("imre.nagi2812@gmail.com"),
                                new PostalAddress("Jl. Kalimantan T1", "Padang", "Sumatera Barat",
                                        "25133", "Indonesia"),
                                new Telephone("650-495-6101"),
                                new Telephone("650-495-6101"))));

        return this.userRepository.save(user);
    }

}
