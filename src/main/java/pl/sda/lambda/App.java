package pl.sda.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.stream.Stream;

@FunctionalInterface
interface Square {
    int square(int x);
}

public class App {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Kasia", "Ania", "Zosia", "Bartek");
        //   Collections.sort(names, new Comparator<String>() {
        //       @Override
        //     public int compare(String o1, String o2) {
        //       return o1.compareTo(o2);//porównujemy parametr o1 do parametru o2
        //  }
        //  });
        //     Collections.sort(names, (String s1, String s2) -> s1.compareTo(s2));
        //     System.out.println(names);
        Collections.sort(names, (String s1, String s2) -> s1.compareTo(s2));
        System.out.println(names);

        Collections.sort(SampleData.membersOfTheBeatles, new Comparator<Artist>() {
            @Override
            public int compare(Artist o1, Artist o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(SampleData.membersOfTheBeatles);

        Square square = (int x) -> x * x;
        System.out.println(square.square(2));
//Lambda jako pętla

        String[] a = {"cat", "dog", "mouse", "rat", "pig", "rabbit", "hamster", "parrot"};
        List<String> animals = Arrays.asList(a);
        // Tradycyjna pętla
        for (String animal : animals) {
            System.out.print(animal + "; ");
        }
        //Wyrażenie lambd
        animals.forEach((animal) -> System.out.println(animal + "; "));
        animals.forEach(System.out::println);

//lista elementów. Trzeba wykonać stream by operować na niej:
        //Lambda jako strumień:
        List<String> fruits =
                Arrays.asList("apple", "banana", "cherry", "plum", "pear", "pinapple");
        //fruits.stream().filter( (x) -> x.startsWith("a")).forEach(System.out::println); //filtruje wszystko od literki a
        //---
        fruits.stream().
                filter((x) -> x.startsWith("a"))
                .map((x) -> x.toUpperCase())
                .forEach(System.out::println);//modyfikuje by były napisane wielką literą a forEach wywołuje

        //---
        //    List<String> result = fruits.stream()
        //            .filter((x) -> x.startsWith("a"))
        //            .map((x) -> x.toUpperCase())
        //            .collect(Collectors.toList());
        //    int counter = 0;
        //    for (Artist members : SampleData.membersOfTheBeatles) {
        //        if (members.getNationality().equals("UK")) {
        //            counter++;
        //        }
        //    }
        //    long number = SampleData.membersOfTheBeatles
        //            .stream()
        //            .filter((x) -> x.getNationality().equals("UK")).count();
        List<String> dataWithNumbers = Arrays.asList("a", "122as", "a23", "32ss", "3a");
        //  for (String data : dataWithNumbers) {
        //      if (Character.isDigit(data.charAt(0))) {
        //          System.out.println(data);
        //      }
        // }
        dataWithNumbers
                .stream()
                .filter(x -> Character.isDigit(x.charAt(0)))
                .collect(Collectors.toList());
        //[2,3,4], [33,32,44] => [2,3,4,33,32,44]

        List<Integer> flat = Stream.of(Arrays.asList(2, 3, 4), Arrays.asList(33, 32, 34))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        System.out.println(flat);
        //flatMap spłaszczanie listy wierowymiarowej

        //szukamy najkrótszego utworu
        List<Track> tracks = Arrays.asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));
        Track minTrack = tracks.stream().min(Comparator.comparing(track -> track.getLength())).get();
        System.out.println(minTrack);
        //napisać znajdowanie najkrótszego utworu bez użycia streamu - pętla for oraz if

    }

}
