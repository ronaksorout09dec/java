class Bank {
    int getBalance() {
        return 0;
    }
}

class BankA extends Bank {
    int getBalance() {
        return 1000;
    }
}

class BankB extends Bank {
    int getBalance() {
        return 1500;
    }
}

class BankC extends Bank {
    int getBalance() {
        return 2000;
    }
}

public class Main {
    public static void main(String[] args) {
        BankA bankA = new BankA();
        BankB bankB = new BankB();
        BankC bankC = new BankC();

        System.out.println("Balance in Bank A: $" + bankA.getBalance());
        System.out.println("Balance in Bank B: $" + bankB.getBalance());
        System.out.println("Balance in Bank C: $" + bankC.getBalance());
    }
}
