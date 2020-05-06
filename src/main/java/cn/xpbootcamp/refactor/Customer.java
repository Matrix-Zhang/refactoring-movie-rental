package cn.xpbootcamp.refactor;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private final String name;
    private final Vector<Rental> rentals = new Vector<>();

    Customer(String name) {
        this.name = name;
    }

    void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    String statement() {
        double totalAmount = 0d;
        int frequentRenterPoints = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "：\n");
        while (rentals.hasMoreElements()) {
            Rental eachRental = rentals.nextElement();
            //show figures for this rental
            //determine amounts for each line
            double thisAmount = 0d;
            switch (eachRental.getMovie().getPriceCode()) {
                case Movie.HISTORY:
                    thisAmount += 2;
                    if (eachRental.getDaysRented() > 2)
                        thisAmount += (eachRental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += eachRental.getDaysRented() * 3;
                    break;
                case Movie.CAMPUS:
                    thisAmount += 1.5;
                    if (eachRental.getDaysRented() > 3)
                        thisAmount += (eachRental.getDaysRented() - 3) * 1.5;
                    break;
            }
            //add frequent renter points
            frequentRenterPoints++;
            if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && eachRental.getDaysRented() > 1)
                frequentRenterPoints++;

            //show figures for this rental
            result.append("\t")
                  .append(eachRental.getMovie().getTitle())
                  .append("\t")
                  .append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

}
