/**
 * ADDITIONAL CLASS 2: reservations.
 *
 * Purpose: records one reservation - who booked which room for how many
 * nights, what the stay costs, and whether it has been paid. The amount is
 * produced by delegating to the guest's Billable implementation, so the
 * booking always matches the price the hotel would actually charge that
 * guest.
 */
public class HotelBooking {

    //   Attributes: one reservation  
    private int bookingId;        // booking reference shown to the guest
    private String guestName;     // guest the reservation belongs to
    private int roomNumber;       // room held by this reservation
    private int nights;           // length of the booked stay
    private double totalAmount;   // quoted price, after the guest tier's discount
    private String paymentStatus; // "PENDING" until confirmPayment() flips it to "PAID"
    private String bookingDate;   // day the reservation was made

    /** Default constructor: an empty PENDING booking ready to be filled in. */
    public HotelBooking() {
        bookingId = 0;
        guestName = "-";
        roomNumber = 0;
        nights = 0;
        totalAmount = 0.00;
        paymentStatus = "PENDING";
        bookingDate = "-";
    }

    /**
     * Parameterized constructor. nights and totalAmount go through their
     * setters so an impossible booking (zero nights, negative amount) is
     * rejected at creation time.
     */
    public HotelBooking(int bookingId, String guestName, int roomNumber,
            int nights, double totalAmount, String paymentStatus, String bookingDate) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        setNights(nights);
        setTotalAmount(totalAmount);
        this.paymentStatus = paymentStatus;
        this.bookingDate = bookingDate;
    }

    //   Getters  
    public int getBookingId() { return bookingId; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public int getNights() { return nights; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getBookingDate() { return bookingDate; }

    //   Setters  
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    // A booking shorter than one night is not a stay, and a negative
    // quoted amount would mean the hotel pays the guest.
    public void setNights(int nights) {
        if (nights >= 1) {
            this.nights = nights;
        }
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        }
    }

    /**
     * Prices the stay by asking the guest's own Billable implementation.
     * The booking keeps no discount rules of its own: if a guest's tier
     * changes, the quoted amount follows automatically instead of drifting
     * out of sync with a second copy of the pricing logic.
     */
    public void calculateTotal(double ratePerNight, Billable biller) {
        totalAmount = biller.calculateStayBill(ratePerNight, nights);
    }

    /**
     * Flips PENDING to PAID. Returns false when the booking was already
     * paid, so a double click at the counter cannot charge twice.
     */
    public boolean confirmPayment() {
        if (paymentStatus.equals("PENDING")) {
            paymentStatus = "PAID";
            return true;
        }
        return false;
    }

    /** One-line booking summary for the front-desk printout. */
    public String getBookingInfo() {
        return "Booking #" + bookingId
                + " | " + guestName
                + " | Room " + roomNumber
                + " | " + nights + " night(s)"
                + " | RM" + String.format("%.2f", totalAmount)
                + " | " + paymentStatus
                + " | " + bookingDate;
    }
}
