package me.internalizable.vCard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.internalizable.vCard.filters.BirthdayFilter;
import me.internalizable.vCard.filters.FamilyFilter;
import me.internalizable.vCard.filters.NameFilter;
import me.internalizable.vCard.filters.utils.FilterComparator;
import me.internalizable.vCard.filters.utils.IFilter;
import me.internalizable.vCard.utils.objects.Gender;
import me.internalizable.vCard.utils.vCard;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Core {

    private static List<vCard> vCardsList = new ArrayList<>();
    private static List<Predicate<vCard>> filters = new ArrayList<>();

    private static Type listType = new TypeToken<ArrayList<vCard>>(){}.getType();

    public static void main(String[] args) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

            Reader reader = Files.newBufferedReader(Paths.get("vCards.json"));

            vCardsList = gson.fromJson(reader, listType);
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("Welcome, please input a choice.");
            System.out.println("1- View all vCards.");
            System.out.println("2- Enter Query Menu.");
            System.out.println("3- Quit");

            choice = scan.nextInt();

            switch(choice) {
                case 1:
                    vCardsList.forEach(vCard ->{
                        System.out.println("");
                        System.out.println("First Name: " + vCard.getName());
                        System.out.println("Last Name: " + vCard.getFamilyName());
                        System.out.println("Birthday: " + vCard.getBirthday());
                        System.out.println("Gender: " + vCard.getGender().name());
                        System.out.println("Location: " + vCard.getLocation());
                        System.out.println("Occupation: " + vCard.getOccupation());
                        System.out.println("");
                    });

                    break;
                case 2:
                    openQueryMenu();
                    break;
            }
        } while (choice != -1);
    }


    private static void openQueryMenu() {
        List<IFilter> filterList = new ArrayList<>();
        filterList.add(new FamilyFilter());
        filterList.add(new BirthdayFilter());
        filterList.add(new NameFilter());

        Collections.sort(filterList, new FilterComparator());

        System.out.println("");

        filterList.forEach(iFilter -> {
            System.out.println(iFilter.getFilterID() + "- " + iFilter.getFilterName());
        });

        int choice;

        Scanner scan = new Scanner(System.in);
        choice = scan.nextInt();

        if(choice == -1)
            System.exit(0);

        System.out.println("You picked " + choice);

        filterList.stream().filter(iFilter -> iFilter.getFilterID() == choice).findAny().ifPresent(iFilter -> {
            String getString = scan.next();
            filters.add(iFilter.getPredicate(getString));

            showCards();
        });

    }

    public static void showCards() {
        Predicate<vCard> pred = filters.stream().reduce(Predicate::and).orElse(x->true);

        vCardsList.stream().filter(pred).forEach(vCard ->{
            System.out.println("");
            System.out.println("First Name: " + vCard.getName());
            System.out.println("Last Name: " + vCard.getFamilyName());
            System.out.println("Birthday: " + vCard.getBirthday());
            System.out.println("Gender: " + vCard.getGender().name());
            System.out.println("Location: " + vCard.getLocation());
            System.out.println("Occupation: " + vCard.getOccupation());
            System.out.println("");
        });

        //openQueryMenu();
    }
}
