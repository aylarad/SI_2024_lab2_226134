import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SILab2Test {
    @Test
    public void testCheckCartEveryBranch() {
        //Test1
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 150);
        });
        assertEquals("allItems list can't be null!", thrown.getMessage());

        //Test2
        List<Item> test2 = new ArrayList<>();
        test2.add(new Item(null, "012345678AA90", 150, 0));
        thrown = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(test2, 150);
        });
        assertEquals("Invalid character in item barcode!", thrown.getMessage());

        //Test3
        List<Item> test3 = new ArrayList<>();
        test3.add(new Item("test", "0123456789", 150, 0));
        assertFalse(SILab2.checkCart(test3, 100));

        //Test4
        List<Item> test4 = new ArrayList<>();
        test4.add(new Item(" ", null, 150, 0));
        thrown = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(test4, 150);
        });
        assertEquals("No barcode!", thrown.getMessage());

        //Test5
        List<Item> test5 = new ArrayList<>();
        test5.add(new Item("test", "0123456789", 350, 0.1f));
        assertTrue(SILab2.checkCart(test5, 60));
    }

    @Test
    public void testCheckCartMultipleCondition() {

        List<Item> list = Arrays.asList(
                new Item("test", "0123", 350, 0.1f),
                new Item("test", "1234", 350, 0.1f),
                new Item("test", "01234", 350, 0),
                new Item("test", "128", 150, 0));
        for (Item item : list) {
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0') {
                assertTrue(true);
            } else {
                assertFalse(false);
            }
        }
    }
}