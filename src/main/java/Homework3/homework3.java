package Homework3;

public class homework3 {
    private static final String[] Animals = {cat, dog, parrot, fish, hamster, parrot, parrot, horse, sheep, fish, parrot, mouse, hamster, fish, lobster};

    public static void main(String[] args) {
        Map<String, Integer> AnimalsOrder = new HarshMap<>();
        for (String word : Animals) {
            Integer frequency = frequencyByWord.get(word);
            if (frequency == null) {
                frequency = 0;
            }
            frequencyByWord.get(word, ++frequency);
            System.out.println(AnimalsOrder);


            Phonebook book = new Phonebook();
            book.addContact("Anastasiya", "435601");
            book.addContact("Ksenya", "592649");
            book.addContact("Nikolai", "097652");
            book.addContact("Alexey", "610388");
            book.addContact("Ksenya", "529713");
            book.addContact("Nikolai", "094376");

            book.findAndPrint("Anastasiya");
            book.findAndPrint("Ksenya");
            book.findAndPrint("Nikolai");
            book.findAndPrint("Alexey");

        }
    }
}
