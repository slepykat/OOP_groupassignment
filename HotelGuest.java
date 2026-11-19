/**
 * BASE CLASS.
 *
 * HotelGuest is the object the group selected in Assignment 1, kept as the
 * root of the whole hierarchy. It holds who a guest is, how to contact them,
 * and how loyal they are to the hotel. Every guest in the system is a
 * HotelGuest, so all tiers share this state and these accessors.
 *
 * The class implements Billable so any guest can price a stay. The base
 * implementation charges the full rack rate: discount rules belong to the
 * tiers (VIPGuest, RegularGuest), which override it.
 */
public class HotelGuest implements Billable {

    // Loyalty points needed to reach VIP status 
    public static final int VIP_POINTS_THRESHOLD = 500;

    //   Attributes (all private)  
    private String guestName;     // full name as printed on bookings and invoices
    private String icNumber;      // Malaysian IC, checked against the passport at check-in
    private String phone;         // contact number for booking changes
    private String email;         // receives booking confirmations
    private int loyaltyPoints;    // points collected from stays; decides VIP by points
    private int totalStays;       // completed stays so far
    private boolean member;       // true once the guest signs up for membership

    /** Default constructor: a placeholder guest until the front desk fills in real details. */
    public HotelGuest() {
        guestName = "New Guest";
        icNumber = "-";
        phone = "-";
        email = "-";
        loyaltyPoints = 0;
        totalStays = 0;
        member = false;
    }

    /**
     * Parameterized constructor.
     * loyaltyPoints and totalStays go through their setters, so a negative
     * value from a bad form submission is rejected here instead of entering
     * the system.
     */
    public HotelGuest(String guestName, String icNumber, String phone, String email,
            int loyaltyPoints, int totalStays, boolean member) {
        this.guestName = guestName;
        this.icNumber = icNumber;
        this.phone = phone;
        this.email = email;
        setLoyaltyPoints(loyaltyPoints);
        setTotalStays(totalStays);
        this.member = member;
    }

    //   Getters: read-only access for display, billing, reporting  
    public String getGuestName() { return guestName; }
    public String getIcNumber() { return icNumber; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public int getTotalStays() { return totalStays; }
    public boolean isMember() { return member; }

    //   Setters   
    // Free-text fields accept whatever the front desk types in; only the
    // counters need guarding.
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setIcNumber(String icNumber) { this.icNumber = icNumber; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setMember(boolean member) { this.member = member; }

    // A negative point balance would corrupt the VIP test, so invalid
    // values are ignored rather than stored.
    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints >= 0) {
            this.loyaltyPoints = loyaltyPoints;
        }
    }

    public void setTotalStays(int totalStays) {
        if (totalStays >= 0) {
            this.totalStays = totalStays;
        }
    }

    // Points are only ever added after a completed stay, so a zero or
    // negative amount has nothing to add.
    public void addPoints(int points) {
        if (points > 0) {
            loyaltyPoints += points;
        }
    }

    /**
     * VIP test by points. VIPGuest overrides this: that tier is VIP by
     * definition, whatever its points balance is.
     */
    public boolean isVip() {
        return loyaltyPoints >= VIP_POINTS_THRESHOLD;
    }

    //   Billable implementation  
    // The base guest pays the full rack rate; the tiers discount from here.

    @Override
    public double calculateStayBill(double ratePerNight, int nights) {
        return ratePerNight * nights;
    }

    @Override
    public String getBillingPolicy() {
        return "Walk-in guests pay the full rack rate";
    }

    /** One-line profile used everywhere the guest is displayed. */
    public String getGuestInfo() {
        return "Guest: " + guestName
                + " | IC: " + icNumber
                + " | Phone: " + phone
                + " | Email: " + email
                + " | Points: " + loyaltyPoints
                + " | Stays: " + totalStays
                + " | Member: " + member;
    }
}
