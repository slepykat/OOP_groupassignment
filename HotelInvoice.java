/**
 * ADDITIONAL CLASS 3: the final bill.
 *
 * Purpose: the printable document handed to the guest at checkout. It
 * stores the stay it covers and its payment state. The discount shown is
 * derived from the real bill (subtotal - totalAmount) instead of
 * recomputing a percentage, so the invoice reconciles with whichever tier
 * priced the stay.
 */
public class HotelInvoice {

    //  Attributes: one issued invoice 
    private int invoiceId;        // invoice reference for the hotel's records
    private String guestName;     // who the bill is for
    private int roomNumber;       // room the stay was in
    private String roomType;      // room type, printed for clarity
    private int nights;           // nights actually stayed
    private double pricePerNight; // rack rate of the room
    private double subtotal;      // rate x nights, before any discount
    private double discount;      // subtotal - totalAmount: whatever the tier took off
    private double totalAmount;   // amount actually charged
    private String paymentStatus; // "PENDING" until confirmPayment() marks it "PAID"
    private String invoiceDate;   // day the invoice was issued

    /**
     * An invoice only exists once a stay has been priced, so there is no
     * default constructor: every field is supplied by the checkout flow.
     */
    public HotelInvoice(int invoiceId, String guestName, int roomNumber,
            String roomType, int nights, double pricePerNight,
            double totalAmount, String invoiceDate) {
        this.invoiceId = invoiceId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
        this.subtotal = pricePerNight * nights;
        this.discount = subtotal - totalAmount;
        this.totalAmount = totalAmount;
        this.paymentStatus = "PENDING";
        this.invoiceDate = invoiceDate;
    }

    //  Getters 
    public int getInvoiceId() { return invoiceId; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public double getSubtotal() { return subtotal; }
    public double getDiscount() { return discount; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }

    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    /**
     * Marks a PENDING invoice as paid. Returns false when it was already
     * settled, mirroring HotelBooking.confirmPayment() so both payment
     * flows behave the same way at the counter.
     */
    public boolean confirmPayment() {
        if (paymentStatus.equals("PENDING")) {
            paymentStatus = "PAID";
            return true;
        }
        return false;
    }

    /** Prints the receipt exactly as the guest receives it at the counter. */
    public void printInvoice() {
        System.out.println("\n========================================");
        System.out.println("          SUNRISE HOTEL INVOICE");
        System.out.println("========================================");
        System.out.println("Invoice ID      : " + invoiceId);
        System.out.println("Invoice Date    : " + invoiceDate);
        System.out.println("Guest Name      : " + guestName);
        System.out.println("Room Number     : " + roomNumber);
        System.out.println("Room Type       : " + roomType);
        System.out.println("Nights Stayed   : " + nights);
        System.out.printf("Price Per Night : RM %.2f%n", pricePerNight);
        System.out.println("----------------------------------------");
        System.out.printf("Subtotal        : RM %.2f%n", subtotal);
        System.out.printf("Discount        : RM %.2f%n", discount);
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BILL      : RM %.2f%n", totalAmount);
        System.out.println("Payment Status  : " + paymentStatus);
        System.out.println("========================================");
        System.out.println("    Thank you for staying with us!");
        System.out.println("========================================");
    }
}
