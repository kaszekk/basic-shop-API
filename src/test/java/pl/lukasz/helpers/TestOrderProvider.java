package pl.lukasz.helpers;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;

public class TestOrderProvider {

  private final List<String> firstNames = List.of("Adam", "Jake", "John", "Jacob", "Fiona", "Lucy", "Miranda", "Olivier");
  private final List<String> surnames = List.of("Smith", "Foy", "Larson", "Fredriksen", "Kowalski", "Nowak", "Putin", "Yakoblev");
  private final List<String> descriptions = List.of("Fuel", "Pizza", "Bike", "Vegetables", "Fruits", "Bread", "Food", "Paint");

  private final LocalDate dateRangeStart = LocalDate.of(2000, 1, 1);
  private final LocalDate dateRangeEnd = LocalDate.of(2020, 12, 31);
  private int randomDatesCount = 100;
  private final List<LocalDate> randomDates = getRandomDatesFromDateRange(randomDatesCount);

  public Order getRandomOrder() {

    LocalDate randomDate = getRandomDate();
    String randomFirstName = getRandomElementFromList(firstNames);
    String randomSurname = getRandomElementFromList(surnames);
    String randomDescription = getRandomElementFromList(descriptions);

    return Order.builder()
        .buyer(Buyer.builder()
            .name(randomFirstName)
            .surname(randomSurname)
            .build())
        .description(randomDescription)
        .date(randomDate)
        .build();
  }

  private String getRandomElementFromList(List<String> list) {
    return list.get(getRandomIndex(list.size()));
  }

  private int getRandomIndex(int size) {
    return ThreadLocalRandom.current().nextInt(0, size - 1);
  }

  private LocalDate getRandomDate() {
    return randomDates.get(getRandomIndex(randomDates.size()));
  }

  private List<LocalDate> getRandomDatesFromDateRange(int datesCount) {
    long minEpochDay = dateRangeStart.toEpochDay();
    long maxEpochDay = dateRangeEnd.toEpochDay();

    return IntStream.rangeClosed(1, datesCount)
        .mapToLong(n -> getRandomEpochDay(minEpochDay, maxEpochDay))
        .mapToObj(LocalDate::ofEpochDay)
        .collect(Collectors.toList());
  }

  private long getRandomEpochDay(long min, long max) {
    return ThreadLocalRandom.current().nextLong(min, max);
  }
}
