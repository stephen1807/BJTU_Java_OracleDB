import project.function.TopMovies;
import project.obj.MovieStat;
import project.query.Indexer;
import twitter4j.TwitterException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 1/2/2015.
 */
public class Main {

    public static void main(String[] args) {


        while (true) {
            System.out.println(">>>Please input command:");
            System.out.println("1. Create index");
            System.out.println("2. Find movie recommendation");
            System.out.println("0. Exit");
            System.out.print(">");
            Scanner in = new Scanner(System.in);

            int num;
            try {
                num = in.nextInt();
            } catch (InputMismatchException i) {
                System.out.println(">>Wrong format");
                continue;
            }

            switch (num) {
                case (0):
                    System.out.println(">>Good bye");
                    System.exit(0);
                    break;
                case (1):
                    Indexer index = new Indexer();
                    System.out.println(">>Checking index...");
                    outerloop:
                    if (index.checkIndex()) {
                        System.out.println("Exist");
                        System.out.println(">>Recreate? This process will take some time (Y/N)");
                        while (true) {
                            Scanner check = new Scanner(System.in);
                            char input;
                            try {
                                input = check.next().charAt(0);
                            } catch (InputMismatchException i) {
                                System.out.println(">>Wrong format");
                                continue;
                            }
                            if (input == 'y' || input == 'Y') {
                                index.recreateIndex();
                                System.out.println(">>Index created");
                                break outerloop;
                            } else if (input == 'n' || input == 'N') {
                                break;
                            } else {
                                System.out.println(">>Wrong command");
                            }
                        }
                    } else {
                        System.out.println("Index doesn't exist");
                        System.out.println(">>Create? This process will take some time (Y/N)");
                        while (true) {
                            Scanner check = new Scanner(System.in);
                            char input;
                            try {
                                input = check.next().charAt(0);
                            } catch (InputMismatchException i) {
                                System.out.println(">>Wrong format");
                                continue;
                            }
                            if (input == 'y' || input == 'Y') {
                                index.createIndex();
                                System.out.println(">>Index created");
                                break outerloop;
                            } else if (input == 'n' || input == 'N') {
                                break;
                            } else {
                                System.out.println(">>Wrong command");
                            }
                        }
                    }
                    break;
                case (2):
                    Indexer index1 = new Indexer();
                    if (!index1.checkIndex()) {
                        System.out.println(">>Index doesn't exist. Please create");
                        break;
                    } else {
                        System.out.println(">>>Please input twitter username");
                        System.out.print(">");
                        Scanner in1 = new Scanner(System.in);
                        String input;
                        try {
                            input = in1.next(Pattern.compile("^[A-Za-z0-9][A-Za-z0-9_]*[A-Za-z0-9]$"));
                        } catch (InputMismatchException i) {
                            System.out.println(">>Wrong format");
                            break;
                        }
                        List<MovieStat> topMovies;
                        try {
                            TopMovies gtm = new TopMovies();
                            topMovies = gtm.getTopMovies(input, "friends");
                        } catch (TwitterException t) {
                            System.out.println(">>Error getting data. Try again");
                            break;
                        }
                        System.out.println(">>Top 10 movie recommendations:");
                        for (int i = 0; i < 10; i++) {
                            System.out.println(topMovies.get(i).getCount() + "\t" + topMovies.get(i).getTitle());
                        }
                    }
                    break;
                default:
                    System.out.println(">>Invalid command");
                    break;
            }
        }
    }
}
