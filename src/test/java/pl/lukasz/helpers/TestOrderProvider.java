package pl.lukasz.helpers;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;

public class TestOrderProvider {

  private static final List<String> firstNames = List.of("Adam", "Jake", "John", "Jacob", "Fiona", "Lucy", "Miranda", "Olivier");
  private static final List<String> surnames = List.of("Smith", "Foy", "Larson", "Fredriksen", "Kowalski", "Nowak", "Putin", "Yakoblev");
  private static final List<String> descriptions = List.of("Fuel", "Pizza", "Bike", "Vegetables", "Fruits", "Bread", "Food", "Paint");

  private static final LocalDate startDate = LocalDate.of(2000, 1, 1);
  private static final LocalDate endDate = LocalDate.of(2020, 12, 31);
  private static final List<LocalDate> randomDates = getRandomDatesFromDateRange(descriptions.size());

  private static final LocalDate randomDate = getRandomDate();

  private static final String randomFirstName = getRandomElementFromList(firstNames);
  private static final String randomSurname = getRandomElementFromList(surnames);
  private static final String randomDescription = getRandomElementFromList(descriptions);

  public static Order getRandomOrder() {

    return Order.builder()
        .buyer(Buyer.builder()
            .name(randomFirstName)
            .surname(randomSurname)
            .build())
        .description(randomDescription)
        .date(randomDate)
        .build();
  }

  private static String getRandomElementFromList(List<String> list) {
    return list.get(getRandomIndex(list.size()));
  }

  private static int getRandomIndex(int size) {
    return ThreadLocalRandom.current().nextInt(0, size - 1);
  }

  private static LocalDate getRandomDate() {
    return randomDates.get(getRandomIndex(randomDates.size()));
  }

  private static List<LocalDate> getRandomDatesFromDateRange(int datesCount) {
    long minEpochDay = TestOrderProvider.startDate.toEpochDay();
    long maxEpochDay = TestOrderProvider.endDate.toEpochDay();

    return IntStream.rangeClosed(1, datesCount)
        .mapToLong(n -> getRandomEpochDay(minEpochDay, maxEpochDay))
        .mapToObj(LocalDate::ofEpochDay)
        .collect(Collectors.toList());
  }

  private static long getRandomEpochDay(long min, long max) {
    return ThreadLocalRandom.current().nextLong(min, max);
  }
}
