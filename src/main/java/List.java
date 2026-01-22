import java.util.ArrayList;

public class List {
    ArrayList<String> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public String AddItem(String item) {
        String message = "added: " + item;
        this.list.add(item);
        return message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;

        if (this.list.isEmpty()) {
            return "No items in list";
        }

        for (int i = 0; i < this.list.size(); i++) {
            if (!(i == this.list.size() - 1)) {
                builder.append(counter).append(". ").append(this.list.get(i)).append("\n");
            } else {
                builder.append(counter).append(". ").append(this.list.get(i));
            }
            counter++;
        }

        return builder.toString();
    }
}
