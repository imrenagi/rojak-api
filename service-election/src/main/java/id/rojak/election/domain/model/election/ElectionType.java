package id.rojak.election.domain.model.election;

/**
 * Created by inagi on 7/3/17.
 */
public enum ElectionType {

    PRESIDENT {
        @Override
        public boolean isPresidentElection() {
            return true;
        }
    },

    GOVERNOR {
        @Override
        public boolean isGovernorElection() {
            return true;
        }
    },

    MAYOR {
        @Override
        public boolean isMayorElection() {
            return true;
        }
    };

    public boolean isPresidentElection() {
        return false;
    }

    public boolean isGovernorElection() {
        return false;
    }

    public boolean isMayorElection() {
        return false;
    }
}
