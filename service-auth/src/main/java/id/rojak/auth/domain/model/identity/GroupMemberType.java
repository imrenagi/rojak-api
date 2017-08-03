package id.rojak.auth.domain.model.identity;

/**
 * Created by inagi on 8/3/17.
 */
public enum GroupMemberType {

    Group {
        public boolean isGroup() {
            return true;
        }
    },

    User {
        public boolean isUser() {
            return true;
        }
    };

    public boolean isGroup() {
        return false;
    }

    public boolean isUser() {
        return false;
    }

}
