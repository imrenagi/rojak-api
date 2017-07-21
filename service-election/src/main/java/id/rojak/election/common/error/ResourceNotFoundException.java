package id.rojak.election.common.error;

/**
 * Created by imrenagi on 7/10/17.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
