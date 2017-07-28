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

        @Override
        public String text() {
            return "Positive";
        }
    },

    NEUTRAL {
        @Override
        public boolean isNeutral() {
            return true;
        }

        @Override
        public String text() {
            return "Neutral";
        }
    },

    NEGATIVE {
        @Override
        public boolean isNegative() {
            return true;
        }

        @Override
        public String text() {
            return "Negative";
        }
    },

    UNKNOWN {
        @Override
        public boolean isUnknown() {
            return true;
        }

        @Override
        public String text() {
            return "Unknown";
        }
    };

    public boolean isPositive() { return false; }

    public boolean isNegative() { return false; }

    public boolean isNeutral() { return false; }

    public boolean isUnknown() { return false; }

    public String text() {
        return "Unknown";
    }

}
