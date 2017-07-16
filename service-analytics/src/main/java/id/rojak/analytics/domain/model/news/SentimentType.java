package id.rojak.analytics.domain.model.news;

/**
 * Created by imrenagi on 7/16/17.
 */
public enum SentimentType {

    POSITIVE {
        @Override
        public boolean isPositive() {
            return true;
        }
    },

    NEUTRAL {
        @Override
        public boolean isNeutral() {
            return true;
        }
    },

    NEGATIVE {
        @Override
        public boolean isNegative() {
            return true;
        }
    },

    UNKNOWN {
        @Override
        public boolean isUnknown() {
            return true;
        }
    };

    public boolean isPositive() { return false; }

    public boolean isNegative() { return false; }

    public boolean isNeutral() { return false; }

    public boolean isUnknown() { return false; }



}
