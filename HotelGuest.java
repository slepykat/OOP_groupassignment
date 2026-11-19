/**
 * SUPPORTING CLASS 1 (From Assignment 1): HotelGuest
 */
public class HotelGuest {
    private String guestName;
    private String icNumber;
    private String phone;
    private String email;
    private int loyaltyPoints;
    private int totalStays;
    private boolean member;

    public HotelGuest() {
        guestName = "New Guest"; icNumber = "-"; phone = "-"; email = "-";
        loyaltyPoints = 0; totalStays = 0; member = false;
    }

    public HotelGuest(String guestName, String icNumber, String phone, String email,
                      int loyaltyPoints, int totalStays, boolean member) {
        this.guestName = guestName; this.icNumber = icNumber; this.phone = phone;
        this.email = email; setLoyaltyPoints(loyaltyPoints);
        setTotalStays(totalStays); this.member = member;
    }

    public String getGuestName() { return guestName; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public int getTotalStays() { return totalStays; }
    public void setLoyaltyPoints(int loyaltyPoints) { if (loyaltyPoints >= 0) this.loyaltyPoints = loyaltyPoints; }
    public void setTotalStays(int totalStays) { if (totalStays >= 0) this.totalStays = totalStays; }
    public void addPoints(int points) { if (points > 0) loyaltyPoints += points; }
    public boolean isVip() { return loyaltyPoints > 500; }

    public String getGuestInfo() {
        return "Guest: " + guestName + " | IC: " + icNumber + " | Phone: " + phone
                + " | Email: " + email + " | Points: " + loyaltyPoints
                + " | Stays: " + totalStays + " | Member: " + member;
    }
}

/**
 * SUPPORTING CLASS 2 (From Assignment 1): HotelBooking
 */
class HotelBooking {
    private int bookingId;
    private String guestName;
    private int roomNumber;
    private int nights;
    private double totalAmount;
    private String paymentStatus;
    private String bookingDate;

    public HotelBooking(int bookingId, String guestName, int roomNumber, int nights,
                        double totalAmount, String paymentStatus, String bookingDate) {
        this.bookingId = bookingId; this.guestName = guestName; this.roomNumber = roomNumber;
        this.nights = nights; this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus; this.bookingDate = bookingDate;
    }

    public int getBookingId() { return bookingId; }
    public String getPaymentStatus() { return paymentStatus; }

    // Polymorphically calculates total amount from any BillableService room
    public void calculateTotal(BillableService room) {
        totalAmount = room.calculateTotal();
    }

    public boolean confirmPayment() {
        if (paymentStatus.equals("PENDING")) {
            paymentStatus = "PAID";
            return true;
        }
        return false;
    }

    public String getBookingInfo() {
        return "Booking #" + bookingId + " | " + guestName + " | Room " + roomNumber
                + " | " + nights + " night(s) | RM" + String.format("%.2f", totalAmount)
                + " | " + paymentStatus + " | " + bookingDate;
    }
}

