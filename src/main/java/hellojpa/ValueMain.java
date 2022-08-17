package hellojpa;

import hellojpa.valuetype.Address;

public class ValueMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;

        System.out.println("a == b : " + (a == b));

        Address address1 = new Address();
        Address address2 = new Address();

        System.out.println("address2.equals(address1) = " + address2.equals(address1));

    }
}
