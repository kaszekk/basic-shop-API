import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.lukasz.model.Buyer;

public class TestBuyerProvider {

  public static Buyer pickRandomBuyerFromBuyers() {
    int randomListIndex = new Random().nextInt(getBuyers().size() - 1);
    return getBuyers().get(randomListIndex);
  }

  private static List<Buyer> getBuyers() {

    List<Buyer> buyerList = new ArrayList<>();

    buyerList.add(new Buyer("Adam", "Foy"));
    buyerList.add(new Buyer("Adam", "Doe"));
    buyerList.add(new Buyer("Hugh", "Connor"));
    buyerList.add(new Buyer("John", "Foy"));
    buyerList.add(new Buyer("Lisa", "Connor"));
    buyerList.add(new Buyer("Linda", "Kowalski"));
    buyerList.add(new Buyer("Jessica", "Kowalski"));
    buyerList.add(new Buyer("Laura", "Thiem"));
    buyerList.add(new Buyer("Tom", "Herzog"));
    buyerList.add(new Buyer("Greg", "Mitt"));
    buyerList.add(new Buyer("William", "Abramovicz"));
    buyerList.add(new Buyer("William", "Kerrs"));
    buyerList.add(new Buyer("Damian", "Frane"));
    buyerList.add(new Buyer("Pat", "Long"));
    buyerList.add(new Buyer("Pat", "Foy"));
    buyerList.add(new Buyer("Antek", "Frane"));
    buyerList.add(new Buyer("Adam", "Thiem"));
    buyerList.add(new Buyer("Liam", "Foy"));
    buyerList.add(new Buyer("Eugene", "Monday"));
    buyerList.add(new Buyer("Austin", "Stanley"));
    buyerList.add(new Buyer("Ann", "Frisby"));
    buyerList.add(new Buyer("Michael", "Kiepski"));
    buyerList.add(new Buyer("Bart", "Finnan"));
    return buyerList;
  }

}
