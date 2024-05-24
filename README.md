# Втора лабораториска вежба по Софтверско инженерство
## Ајла Радончиќ, бр. на индекс 226134
### Control Flow Graph
![Control Flow Graph](https://github.com/aylarad/SI_2024_lab2_226134/blob/master/cfgsi.png)
### Цикломатска комплексност
Цикломатска комплексност ја пресметав со броење на предикатните јазли, кои ги има 9, а формулата е P+1, што значи цикломатската комплексност е 10. Дополнително, и според форумалата C = E - N + 2P го добиваме бројот 10, т.е. C = 36 - 28 + 2 = 10.
### Тест случаи според критериумот Every branch
За овој критериум беа потребни 5 тест случаи за да се покријат сите ребра во графот.

allitems = [(name="test", price=150, discount=0, barcode="01234567890")], payment = 100

allItems = null, payment = 150

allitems = [(name=null, price=150, discount=0, barcode="012345678AA90")], payment = 150

allitems = [(name="", price=150, discount=0, barcode=null)], payment = 150

allItems = [(name="test", price=350, discount=0.1, barcode="0123456789")], payment = 60

![Every branch](https://github.com/aylarad/SI_2024_lab2_226134/blob/master/everybranch.png)
### Тест случаи според критериумот Multiple condition 
За овој критериум беа потребни 4 тест слуачаи, т.е.  T, T, T; T, T, F; T, F, X и F, X, X.

allItems=[("test", "0123", 350, 0,1), ("test", "1234", 350, 0,1), ("test", "01234",  350, 0), ("test", "128", 150, 0)], payment = 150

![Multiple condition](https://github.com/aylarad/SI_2024_lab2_226134/blob/master/multi.png)
### Објаснување на напишаните unit tests
Unit тестовите ги напишав според горенаведените критериуми и тестови за нив.

Првиот тест со критериумот Every branch го тестира кодот кога листата е празна, вториот тест тестира кога имаме невалиден баркод и нема име на предметот, третиот го тестира случајот кога сѐ е во ред, но сумата е поголема од payment, четвртиот тест испитува кога на производот нема баркод, а во петтиот тест сите параметри се задоволени и го испитува и условот за цената да е поголема од 300, да има попуст и баркодот да почнува со 0 и на крај го задоволува и условот сумата да е помала од payment.

Според критиериумот Multiple condition, првиот тест ги задоволува сите критериуми во условот, вториот ги задоволува првите два критериуми од условот, третиот го задоволува само првиот критериум, а четвртиот не го задоволува првиот услов, па секако нема да се задоволи целосно барањето.

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
