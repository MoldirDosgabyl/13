interface State {
    void selectTicket(TicketMachine machine);
    void insertMoney(TicketMachine machine, double amount);
    void dispenseTicket(TicketMachine machine);
    void cancelTransaction(TicketMachine machine);
}

class TicketMachine {
    State state;
    double ticketPrice = 10;
    double amountInserted = 0;

    public TicketMachine() { state = new IdleState(); }
    public void selectTicket() { state.selectTicket(this); }
    public void insertMoney(double amount) { state.insertMoney(this, amount); }
    public void dispenseTicket() { state.dispenseTicket(this); }
    public void cancelTransaction() { state.cancelTransaction(this); }
    public void setState(State state) { this.state = state; }
    public void resetAmount() { amountInserted = 0; }
}

class IdleState implements State {
    public void selectTicket(TicketMachine machine) {
        System.out.println("Билет выбран. Ожидание внесения денег...");
        machine.setState(new WaitingForMoneyState());
    }
    public void insertMoney(TicketMachine machine, double amount) { System.out.println("Сначала выберите билет!"); }
    public void dispenseTicket(TicketMachine machine) { System.out.println("Сначала выберите билет!"); }
    public void cancelTransaction(TicketMachine machine) { System.out.println("Нет активной транзакции."); }
}

class WaitingForMoneyState implements State {
    public void selectTicket(TicketMachine machine) { System.out.println("Билет уже выбран. Внесите деньги."); }
    public void insertMoney(TicketMachine machine, double amount) {
        machine.amountInserted += amount;
        System.out.println("Внесено: " + machine.amountInserted);
        if (machine.amountInserted >= machine.ticketPrice) machine.setState(new MoneyReceivedState());
    }
    public void dispenseTicket(TicketMachine machine) { System.out.println("Недостаточно денег."); }
    public void cancelTransaction(TicketMachine machine) {
        System.out.println("Транзакция отменена.");
        machine.resetAmount();
        machine.setState(new TransactionCanceledState());
    }
}

class MoneyReceivedState implements State {
    public void selectTicket(TicketMachine machine) { System.out.println("Билет уже выбран и деньги внесены."); }
    public void insertMoney(TicketMachine machine, double amount) {
        machine.amountInserted += amount;
        System.out.println("Дополнительно внесено: " + amount);
    }
    public void dispenseTicket(TicketMachine machine) {
        System.out.println("Билет выдан. Спасибо за покупку!");
        machine.resetAmount();
        machine.setState(new TicketDispensedState());
    }
    public void cancelTransaction(TicketMachine machine) {
        System.out.println("Транзакция отменена. Возврат денег.");
        machine.resetAmount();
        machine.setState(new TransactionCanceledState());
    }
}

class TicketDispensedState implements State {
    public void selectTicket(TicketMachine machine) { System.out.println("Начните новую транзакцию."); machine.setState(new IdleState()); }
    public void insertMoney(TicketMachine machine, double amount) { System.out.println("Начните новую транзакцию."); }
    public void dispenseTicket(TicketMachine machine) { System.out.println("Билет уже выдан."); }
    public void cancelTransaction(TicketMachine machine) { System.out.println("Невозможно отменить. Билет уже выдан."); }
}

class TransactionCanceledState implements State {
    public void selectTicket(TicketMachine machine) { System.out.println("Начинаем новую транзакцию..."); machine.setState(new IdleState()); }
    public void insertMoney(TicketMachine machine, double amount) { System.out.println("Транзакция отменена. Сначала выберите билет."); }
    public void dispenseTicket(TicketMachine machine) { System.out.println("Транзакция отменена. Невозможно выдать билет."); }
    public void cancelTransaction(TicketMachine machine) { System.out.println("Транзакция уже отменена."); }
}

public class StateDiagramTicet {
    public static void main(String[] args) {
        TicketMachine machine = new TicketMachine();
        machine.selectTicket();
        machine.insertMoney(5);
        machine.insertMoney(5);
        machine.dispenseTicket();
        machine.selectTicket();
    }
}
