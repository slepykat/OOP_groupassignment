/**
 * DERIVED CLASS 2.
 *
 * RegularGuest is the standard registered member. The tier adds reward
 * vouchers (one free night after enough stays) and the hotel's normal
 * long-stay discount: 10% once a stay reaches five nights. Its billing
 * override makes a long stay cheaper than a walk-in's, but not as cheap as
 * a VIP's, so the three tiers stay visibly apart in the output.
 */
public class RegularGuest extends HotelGuest {

    // Hotel long-stay policy: 10% off from the 5th night onwards
    public static final int LONG_STAY_MIN_NIGHTS = 5;
    public static final double LONG_STAY_DISCOUNT = 0.10;

    private int rewardVouchers;   // free-night vouchers earned; one can be redeemed per stay

    /** Default constructor: a new regular guest starts with no vouchers. */
    public RegularGuest() {
        super();
        rewardVouchers = 0;
    }

    /** Parameterized constructor: base attributes go up to HotelGuest, then the voucher count. */
    public RegularGuest(String guestName, String icNumber, String phone, String email,
            int loyaltyPoints, int totalStays, boolean member, int rewardVouchers) {
        super(guestName, icNumber, phone, email, loyaltyPoints, totalStays, member);
        this.rewardVouchers = rewardVouchers;
    }

    // Distinctive attribute access and behaviour
    public int getRewardVouchers() { return rewardVouchers; }

    // Vouchers are counted, so a negative count would break redeemVoucher().
    public void setRewardVouchers(int rewardVouchers) {
        if (rewardVouchers >= 0) {
            this.rewardVouchers = rewardVouchers;
        }
    }

    /**
     * Uses one voucher for the current stay. Returns false when the guest
     * has none left, so the front desk knows to charge the normal rate.
     */
    public boolean redeemVoucher() {
        if (rewardVouchers > 0) {
            rewardVouchers--;
            return true;
        }
        return false;
    }

    // Overridden behaviour
    // Short stays pay full rate; only stays of 5 nights or more earn the
    // long-stay discount, which is why the nights count is tested first.
    @Override
    public double calculateStayBill(double ratePerNight, int nights) {
        if (nights >= LONG_STAY_MIN_NIGHTS) {
            return ratePerNight * nights * (1 - LONG_STAY_DISCOUNT);
        }
        return ratePerNight * nights;
    }

    @Override
    public String getBillingPolicy() {
        return "Regular guests receive 10% off stays of 5 nights or more";
    }

    @Override
    public String getGuestInfo() {
        return super.getGuestInfo() + " | Reward vouchers: " + rewardVouchers;
    }
}
