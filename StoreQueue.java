import java.util.*;

class Customer {

    String name;
    boolean isSenior;
    boolean isPWD;

    public Customer(String name, boolean isSenior, boolean isPWD) {
        this.name = name;
        this.isSenior = isSenior;
        this.isPWD = isPWD;
    }
}

class PriorityComparator implements Comparator<Customer> {

    public int compare(Customer c1, Customer c2) {
        if (c1.isSenior && !c2.isSenior) {
            return -1;
        } else if (!c1.isSenior && c2.isSenior) {
            return 1;
        } else if (c1.isPWD && !c2.isPWD) {
            return -1;
        } else if (!c1.isPWD && c2.isPWD) {
            return 1;
        } else {
            return 0;
        }
    }
}

class StoreQueue {

    Queue<Customer> customerQueue;

    public StoreQueue() {
        this.customerQueue = new PriorityQueue<>(new PriorityComparator());
    }

    public void addCustomer(Customer customer) {

        customerQueue.add(customer);
    }

    public Customer getNextCustomer() {

        return customerQueue.poll();
    }
}

class Main {

    public static void main(String[] args) {
        StoreQueue storeQueue = new StoreQueue();
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            System.out.println();
            System.out.print("Enter name for person " + (i + 1) + "/4: ");
            String name = s.nextLine();
            System.out.print("Are you a senior? (Y/N): ");
            boolean isSenior = s.nextLine().equalsIgnoreCase("Y");
            boolean isPWD = false;

            if (!isSenior) {
                System.out.print("Are you PWD? (Y/N): ");
                isPWD = s.nextLine().equalsIgnoreCase("Y");
            }

            Customer customer = new Customer(name, isSenior, isPWD);
            storeQueue.addCustomer(customer);
        }
        System.out.println();
        System.out.print("Press 'N' to call the customer/s: ");
        while (s.nextLine().equalsIgnoreCase("N")) {

            Customer nextCustomer = storeQueue.getNextCustomer();
            if (nextCustomer != null) {

                System.out.println("Next: " + nextCustomer.name);
                System.out.println();
                if (storeQueue.customerQueue.isEmpty()) {
                    System.out.println("No person left.");
                    break;
                }
                System.out.print("Press 'N' to call the customer/s: ");
            } else {
                System.out.println();
                System.out.println("No person left.");
                break;
            }
        }
    }
}
