package id.rojak.auth.domain.model.identity;

/**
 * Created by inagi on 8/2/17.
 */
public interface PasswordService {

    public String generateStrongPassword();

    public boolean isStrong(String aPlainTextPassword);

    public boolean isVeryStrong(String aPlainTextPassword);

    public boolean isWeak(String aPlainTextPassword);

    public int calculatePasswordStrength(String aPlainTextPassword);
}
