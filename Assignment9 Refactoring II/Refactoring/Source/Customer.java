import java.util.*;

/**
 * Assignment 9: Refactoring II <br />
 * The {@code Customer} class
 */
public class Customer {

    private String name;
    private Vector<Rental> rentals = new Vector<Rental>();

    public Customer (String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName () {
        return name;
    }

    public String statement() {
        // TODO: Assignment 9 Checkpoint 1 -- Move Method to Rental
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration<Rental> enumRentals = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (enumRentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) enumRentals.nextElement();

            //determine amounts for each rental
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (!(each.getDaysRented() <= 2))
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILD:
                    thisAmount += 1.5;
                    if (!(each.getDaysRented() <= 3))
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints ++;

            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) 
                frequentRenterPoints ++;

             //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +  " frequent renter points";

        return result;
    }

}
