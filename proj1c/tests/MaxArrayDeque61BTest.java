import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class IntergerComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    private static class StringFirstComparator implements Comparator<String> {
        public int compare(String a, String b) {
            char aFirst = Character.toLowerCase(a.charAt(0));
            char bFirst = Character.toLowerCase(b.charAt(0));
            return aFirst - bFirst;
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void maxComTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max(new StringFirstComparator())).isEqualTo("fury road");
    }
}
