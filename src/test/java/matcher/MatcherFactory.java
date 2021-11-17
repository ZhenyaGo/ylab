package matcher;

import static org.assertj.core.api.Assertions.assertThat;

public class MatcherFactory {
    public static <T> Matcher<T> getMatcher() {
        return new Matcher<>();
    }

    public static class Matcher<T> {
        public void assertMatch(T actual, T expected) {
            assertThat(actual).isEqualTo(expected);
        }
    }
}
