/**
 * DERIVED CLASS 1 (assignment requirement 2).
 *
 * VIPGuest extends HotelGuest with the perks the hotel sells as the VIP
 * package: guaranteed lounge access and a flat 15% discount on every stay,
 * with no minimum number of nights. It overrides the inherited billing
 * methods, so the same method call prices a VIP stay differently from a
 * plain guest's.
 */
public class VIPGuest extends HotelGuest {

    // Flat VIP perk: 15% off any stay, no minimum nights
    public static final double VIP_DISCOUNT = 0.15;

    private boolean loungeAccess;   // true while the executive lounge is open to this guest

    /** Default constructor: a VIP guest with lounge access granted. */
    public VIPGuest() {
        super();
        loungeAccess = true;
    }

    /**
     * Parameterized constructor: the seven base attributes are passed up to
     * HotelGuest; only the tier-specific perk is stored here.
     */
    public VIPGuest(String guestName, String icNumber, String phone, String email,
            int loyaltyPoints, int totalStays, boolean member, boolean loungeAccess) {
        super(guestName, icNumber, phone, email, loyaltyPoints, totalStays, member);
        this.loungeAccess = loungeAccess;
    }

    // --- Distinctive accessors (requirement 2: methods the base class does not have) ---
    public boolean hasLoungeAccess() { return loungeAccess; }

    public void setLoungeAccess(boolean loungeAccess) { this.loungeAccess = loungeAccess; }

    // --- Overridden behaviour (requirement 3: same signature, different result) ---

    @Override
    public double calculateStayBill(double ratePerNight, int nights) {
        return ratePerNight * nights * (1 - VIP_DISCOUNT);
    }

    @Override
    public String getBillingPolicy() {
        return "VIP guests receive a flat 15% discount on every stay";
    }

    // Membership of this tier is what makes the guest VIP, not the points
    // balance, so the inherited points-based test is replaced outright.
    @Override
    public boolean isVip() {
        return true;
    }

    @Override
    public String getGuestInfo() {
        return super.getGuestInfo() + " | VIP lounge access: " + (loungeAccess ? "yes" : "no");
    }
}
