package programowanieobiektowe.lab14;

import java.util.concurrent.ThreadLocalRandom;

public class Elevator {
    // tworzymy 3 wątki
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor,boolean directionUp){
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor,directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor){
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // uruchomienie wątków
    static void init(){
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    static int getRandomFloor() {
        return ThreadLocalRandom.current().nextInt(0, 10);
    }

    static class Passenger {
        private int inFloor = getRandomFloor();
        private int outFloor = getRandomFloor();
        private boolean up = inFloor > outFloor;
    }

    // miesjce na kod testowy
    public static void main(String[] args) {
        try {
            init();
            makeExternalCall(8,true);
            Thread.currentThread().sleep(100);
            makeInternalCall(0);
            Thread.currentThread().sleep(100);
            makeExternalCall(7,true);
            Thread.currentThread().sleep(100);
            makeInternalCall(9);
            Thread.currentThread().sleep(100);
            for(;;) {
                Passenger newPerson = new Passenger();
                makeExternalCall(newPerson.inFloor, newPerson.up);
                Thread.currentThread().sleep(100);
                makeInternalCall(newPerson.outFloor);
                Thread.currentThread().sleep(100);
            }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}