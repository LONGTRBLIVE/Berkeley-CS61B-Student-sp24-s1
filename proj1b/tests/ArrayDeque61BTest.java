import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     public void addFirstTestBasic(){
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         for (int i = 0; i < 6; i ++) {
             lld1.addFirst(i);
         }
         assertThat(lld1.toList()).containsExactly(5, 4, 3, 2, 1, 0).inOrder();
     }

    @Test
    public void addLastTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        for (int i = 0; i < 6; i++) {
            lld1.addLast(i);
        }
        assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5).inOrder();
    }

    @Test
    public void addFirstAndaddLastTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         lld1.addFirst(1);
         lld1.addFirst(0);
         lld1.addLast(2);
         lld1.addLast(3);
         assertThat(lld1.toList()).containsExactly(0, 1, 2, 3).inOrder();

    }
    @Test
    public void sizeTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         for (int i = 0; i < 6; i ++){
             lld1.addFirst(i);
         }
         assertThat(lld1.size()).isEqualTo(6);
    }
    @Test
    public void getTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         assertThat(lld1.get(2)).isEqualTo(null);
         lld1.addLast(1); // [1]
         assertThat(lld1.get(0)).isEqualTo(1);
         lld1.addFirst(0); // [0, 1]
        assertThat(lld1.get(1)).isEqualTo(1);
        for(int i = 0; i < 5; i++) {
            lld1.addFirst(i);
        }
        assertThat(lld1.get(0)).isEqualTo(4);
        assertThat(lld1.get(-1)).isEqualTo(null);
        assertThat(lld1.get(lld1.size())).isEqualTo(null);
    }

    @Test
    public void isEmptyTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         assertThat(lld1.isEmpty()).isTrue();
         lld1.addFirst(1);
         assertThat(lld1.isEmpty()).isFalse();
    }


    @Test
    public void resizeUpTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         for (int i = 9; i >= 0; i --) {
             lld1.addFirst(i);
         }
         assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
         for (int i = 8; i >= 0; i --) {
             lld1.addLast(i);
         }
         assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0).inOrder();
    }

    @Test
    public void resizeDownTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        for (int i = 0; i < 1000; i ++) {
            lld1.addFirst(i);
        }
        for (int i = 0; i < 900; i ++) {
            lld1.removeFirst();
        }
        assertThat(lld1.size()).isEqualTo(400);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        for (int i = 9; i >= 0; i --) {
            lld1.addFirst(i);
        }
        assertThat(lld1.removeFirst()).isEqualTo(0);
        assertThat(lld1.toList()).containsExactly( 1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.removeLast()).isEqualTo(null);
        for (int i = 9; i >= 0; i --) {
            lld1.addFirst(i);
        }
        assertThat(lld1.removeLast()).isEqualTo(9);
        assertThat(lld1.toList()).containsExactly( 0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }
}
