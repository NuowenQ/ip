import java.util.ArrayList;

public class List {
    ArrayList<String> list;

    public List() {
        this.list = new ArrayList<String>();
    }

    public void AddItem(String item) {
        System.out.println("added: " + item);
        this.list.add(item);
    }

    public void DisplayItems() {
        int counter = 1;

        if (this.list.isEmpty()) {
            System.out.println("No items in list");
        }

        for (String item : this.list) {
            System.out.println(counter + ". " + item);
            counter++;
        }
    }
}
