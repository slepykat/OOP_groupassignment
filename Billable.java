/**
 * COMMON INTERFACE.
 *
 * Billable is the shared contract for anything that can price a hotel stay.
 * Every guest tier implements the same two method signatures but produces a
 * different result: a walk-in pays the rack rate, a regular guest gets the
 * long-stay discount, and a VIP gets a flat discount. HotelMain calls these
 * methods through one common reference without knowing which tier it holds,
 * which is the polymorphism this assignment has to demonstrate.
 *
 * HotelBooking also depends on this interface: it delegates pricing to the
 * guest instead of keeping its own copy of the discount rules.
 */
public interface Billable {

    /**
     * The final charge for a stay at the given nightly rate.
     *
     * @param ratePerNight rack rate of the room, in RM
     * @param nights       length of the stay, already validated as >= 1 by the room
     * @return the amount the guest actually pays, after their tier's discount
     */
    double calculateStayBill(double ratePerNight, int nights);

    /**
     * The pricing rule this guest type follows, as a human-readable sentence.
     * Printed in the front-desk output so the tiers' different behaviour shows
     * up in words, next to the bills it produces.
     */
    String getBillingPolicy();
}
