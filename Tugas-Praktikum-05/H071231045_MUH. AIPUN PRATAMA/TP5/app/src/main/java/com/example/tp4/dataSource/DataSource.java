package com.example.tp4.dataSource;

import com.example.tp4.R;
import com.example.tp4.models.Book;
import com.example.tp4.models.BookPreview;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Book> books = generateBooks();
    public static ArrayList<BookPreview> bookPreviews = generateBookPreviews();
    public static boolean bookBaruDitambahkan = false;

    public static void tambahBuku(Book book) {
        if (book.getCoverUri() != null) {
            bookPreviews.add(0, new BookPreview(book.getId(), book.getJudul(), book.getPenulis(), book.getRating(), book.getCoverUri(), book.getGenre()));
        } else {
            bookPreviews.add(0, new BookPreview(book.getId(), book.getJudul(), book.getPenulis(), book.getRating(), book.getCoverResId(), book.getGenre()));
        }
        bookBaruDitambahkan = true;
    }


    public static ArrayList<Book> generateBooks() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("The Rise of Naruto", "Masashi Kishimoto", "2002", "Action", "Naruto Uzumaki, a spirited young ninja shunned by his village, strives to become Hokage, the greatest ninja leader. With the Nine-Tails sealed within him, Naruto faces endless trials, forges powerful bonds, and walks a path of perseverance, pain, and purpose.", 6, "Japanese", "Manga", "978-1234567890", "Japan", "Konoha Chronicles", "First", R.drawable.naruto1, 5, false));
        books.add(new Book("The Tale of Sasuke", "Masashi Kishimoto", "2003", "Drama", "Driven by revenge and haunted by the past, Sasuke Uchiha abandons his village to seek the power needed to avenge his clan. This volume explores his descent into darkness, torn loyalties, and the inner conflict that defines his tragic journey.", 6, "Japanese", "Manga", "978-1234567891", "Japan", "Konoha Chronicles", "First", R.drawable.naruto2, 4, false));
        books.add(new Book("Gumpalan Pink", "Masashi Kishimoto", "2004", "Adventure", "From a timid girl to a formidable ninja and healer, Sakura Haruno’s evolution is a testament to strength and growth. This story follows her resolve to stand on equal ground with Naruto and Sasuke, and her vital role in the fate of the ninja world.", 7, "Japanese", "Manga", "978-1234567892", "Japan", "Kunoichi Legends", "Collector's", R.drawable.naruto3, 3, false));
        books.add(new Book("The Will of Fire", "Hiruzen Sarutobi", "2005", "Philosophy", "A deep dive into the ideology of the Hidden Leaf: the Will of Fire. Learn about the values that bind generations, protect the village, and inspire countless shinobi, from Hashirama to Naruto.", 8, "Japanese", "Manga", "978-1234567893", "Japan", "Leaf Lore", "Standard", R.drawable.naruto4, 5, false));
        books.add(new Book("Akatsuki Rising", "Pain (Nagato)", "2006", "Thriller", "The Akatsuki's sinister plan to collect tailed beasts threatens the balance of the world. This dark tale explores their motives, ideology, and the devastation they leave behind.", 9, "Japanese", "Manga", "978-1234567894", "Japan", "Rogue Files", "Limited", R.drawable.naruto5, 4, false));
        books.add(new Book("The Way of the Ninja", "Jiraiya", "2007", "Biography", "A candid look into the legendary life of Jiraiya the Toad Sage. From student to sensei, from author to wanderer, this story is filled with heart, humor, and heroism.", 10, "Japanese", "Manga", "978-1234567895", "Japan", "Sannin Tales", "First", R.drawable.naruto6, 5, false));
        books.add(new Book("The Curse of the Uchiha", "Itachi Uchiha", "2008", "Tragedy", "A tragic chronicle of Itachi Uchiha, the boy who made the ultimate sacrifice. This introspective volume explores truth, betrayal, and the fine line between hero and villain.", 11, "Japanese", "Manga", "978-1234567896", "Japan", "Shadow Archives", "Standard", R.drawable.naruto7, 3, false));
        books.add(new Book("Sage of Six Paths", "Hagoromo Ōtsutsuki", "2009", "Fantasy", "Discover the ancient legend of the first shinobi, the Sage of Six Paths, and his battle against darkness that shaped the ninja world. This mythical tale reveals the origin of chakra and destiny itself.", 12, "Japanese", "Manga", "978-1234567897", "Japan", "Legends of Shinobi", "Revised", R.drawable.naruto8, 4, false));
        books.add(new Book("Tales of the Tailed Beasts", "Kurama", "2010", "Fantasy", "Each tailed beast has its own will and story. Learn about the lore, suffering, and friendships behind these powerful creatures once feared as monsters.", 13, "Japanese", "Manga", "978-1234567898", "Japan", "Beast Chronicles", "Collector's", R.drawable.naruto9, 5, false));
        books.add(new Book("The Ninja War Chronicles", "Kakashi Hatake", "2011", "War", "A harrowing account of the Fourth Great Ninja War, as alliances are forged, legends fall, and the fate of the world hangs in the balance. Kakashi narrates the highs and lows of battle, brotherhood, and loss.", 14, "Japanese", "Manga", "978-1234567899", "Japan", "War Records", "First", R.drawable.naruto10, 4, false));
        books.add(new Book("Borutod", "Ukyo Kodachi", "2016", "Action", "The new generation steps forward. Boruto Uzumaki struggles with legacy, technology, and forging his own ninja way in a peaceful yet uncertain world.", 15, "Japanese", "Manga", "978-1234567800", "Japan", "Next Generations", "Standard", R.drawable.naruto11, 1, false));

        return books;
    }


    private static ArrayList<BookPreview> generateBookPreviews() {
        ArrayList<BookPreview> bookPreviews = new ArrayList<>();
        for (Book book : books) {
            if (book.getCoverUri() != null) {
                bookPreviews.add(new BookPreview(book.getId(), book.getJudul(), book.getPenulis(), book.getRating(), book.getCoverUri(), book.getGenre()));
            } else {
                bookPreviews.add(new BookPreview(book.getId(), book.getJudul(), book.getPenulis(), book.getRating(), book.getCoverResId(), book.getGenre()));
            }
        }

        return bookPreviews;
    }

    public static ArrayList<BookPreview> getFavoriteBookPreviews() {
        ArrayList<BookPreview> favorites = new ArrayList<>();
        for (Book book : books) {
            if (book.isLike()) {
                if (book.getCoverUri() != null) {
                    favorites.add(new BookPreview(book.getId(), book.getJudul(), book.getPenulis(), book.getRating(), book.getCoverUri(), book.getGenre()));
                } else {
                    favorites.add(new BookPreview(book.getId(), book.getJudul(), book.getPenulis(), book.getRating(), book.getCoverResId(), book.getGenre()));
                }
            }
        }
        return favorites;
    }


}
