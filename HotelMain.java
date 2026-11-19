/**
 * MAIN CLASS.
 *
 * Drives a front-desk shift at Sunrise Hotel: builds the room inventory,
 * registers guests of every tier, prices one identical stay through the
 * Billable interface to demonstrate polymorphism, then runs check-ins, a
 * booking, a checkout with an invoice, the housekeeping crew that cleans
 * the room afterwards, and the guest reports.
 */
public class HotelMain {

    public static void main(String[] args) {

        // Room inventory
        // room1 uses the default constructor; room2 and room3 are filled in
        // directly. room3 is already occupied because Sara walked in and was
        // assigned the suite at the counter before this program starts.
        HotelRoom room1 = new HotelRoom();
        HotelRoom room2 = new HotelRoom(201, "Single", 150.00, 2, "", 0, false);
        HotelRoom room3 = new HotelRoom(301, "Suite", 260.00, 3, "Sara Lim", 6, true);

        System.out.println("===== WELCOME TO SUNRISE HOTEL SYSTEM =====");

        // FOR LOOP : the room list has a fixed size and every
        // room must be visited exactly once, in order, so a counted for loop
        // fits this listing job.
        HotelRoom[] rooms = {room1, room2, room3};

        System.out.println("\n===== HOTEL ROOM INFORMATION =====");
        for (int i = 0; i < rooms.length; i++) {
            System.out.println(rooms[i].getRoomInfo());
        }

        // SWITCH: roomType has a small fixed set of values
        // and each one gets its own sentence, which is what switch expresses.
        System.out.println("\n===== ROOM TYPE INFORMATION =====");
        switch (room3.getRoomType()) {
            case "Single":
                System.out.println("Single room comes with one bed.");
                break;
            case "Double":
                System.out.println("Double room comes with two beds.");
                break;
            case "Suite":
                System.out.println("Suite is the biggest room with a living area.");
                break;
            default:
                System.out.println("Unknown room type.");
        }

        // --- Guests: base class + both derived classes ---
        // guest1 starts from the default constructor and is filled in with
        // setters later. Sara stays a plain HotelGuest because walk-ins are
        // registered without a tier. Ali and Daniel use the derived classes.
        HotelGuest guest1 = new HotelGuest();
        HotelGuest sara = new HotelGuest("Sara Lim", "011124-08-5344",
                "013-99887766", "sara@mail.com", 0, 1, false);
        RegularGuest ali = new RegularGuest("Ali bin Abu", "040222-07-5123",
                "011-22334455", "ali@mail.com", 120, 2, true, 2);
        VIPGuest daniel = new VIPGuest("Daniel Tan", "020315-14-5567",
                "012-3456789", "daniel@mail.com", 520, 4, true, true);

        // POLYMORPHISM
        // One array of the base type, one identical call per guest: same
        // signature, three different bills, because each tier implements
        // calculateStayBill differently behind the Billable interface.
        System.out.println("\n===== ONE STAY, THREE TIERS (POLYMORPHISM) =====");
        HotelGuest[] guests = {sara, ali, daniel};
        for (int i = 0; i < guests.length; i++) {
            double bill = guests[i].calculateStayBill(260.00, 6);
            System.out.println(guests[i].getGuestName() + " ("
                    + guests[i].getBillingPolicy() + "): RM"
                    + String.format("%.2f", bill));
        }

        // --- Check-ins ---
        System.out.println("\n===== GUEST CHECK-IN =====");

        // IF-ELSE: checkIn() reports success or failure and
        // the printout must show which one happened.
        if (room2.checkIn(daniel.getGuestName(), 3)) {
            System.out.println(daniel.getGuestName() + " checked in to room "
                    + room2.getRoomNumber() + " for 3 nights.");
        } else {
            System.out.println("Room " + room2.getRoomNumber() + " is not available.");
        }

        // Room 301 is still held by Sara, so this second attempt must fail -
        // the else branch proves the guard inside checkIn() actually works.
        if (room3.checkIn(ali.getGuestName(), 2)) {
            System.out.println(ali.getGuestName() + " checked in to room "
                    + room3.getRoomNumber() + ".");
        } else {
            System.out.println("Cannot check in: room " + room3.getRoomNumber()
                    + " is already occupied by " + room3.getGuestName() + ".");
        }

        // Booking for Sara's suite stay
        HotelBooking booking1 = new HotelBooking(1001, sara.getGuestName(),
                room3.getRoomNumber(), 6, 0.00, "PENDING", "19/11/2026");

        // The booking prices itself through Sara's Billable implementation,
        // not with its own copy of the discount rules.
        booking1.calculateTotal(260.00, sara);

        System.out.println("\n===== BOOKING INFORMATION =====");
        System.out.println(booking1.getBookingInfo());

        if (booking1.getPaymentStatus().equals("PENDING")) {
            booking1.confirmPayment();
            System.out.println("Payment confirmed for booking " + booking1.getBookingId() + ".");
        }
        System.out.println(booking1.getBookingInfo());

        // WHILE LOOP: walks the room array with a counter
        // that is tested before each step - the while form of the same scan
        // the for loop above performs.
        int i = 0;
        int occupiedCount = 0;
        while (i < rooms.length) {
            if (rooms[i].isOccupied()) {
                occupiedCount++;
            }
            i++;
        }
        System.out.println("\nOccupied rooms right now: " + occupiedCount + " of " + rooms.length);

        // Setters in action: reprice room1, then read the new value back
        // through its getter.
        room1.setPricePerNight(120.00);
        System.out.println("Room " + room1.getRoomNumber() + " new price: RM"
                + String.format("%.2f", room1.getPricePerNight()));

        // --- Checkout and invoice ---
        System.out.println("\n===== GUEST CHECK-OUT =====");

        // Read the stay details before checkOut() wipes them: the invoice
        // still needs them after the room has been cleared.
        double rate = room2.getPricePerNight();
        int nights = room2.getNightsBooked();

        // The occupant's own tier prices the bill. Daniel is a VIPGuest, so
        // his flat 15% discount applies - the same call would produce a
        // different amount for Sara or Ali.
        double bill = daniel.calculateStayBill(rate, nights);
        room2.checkOut();
        daniel.setTotalStays(daniel.getTotalStays() + 1);

        System.out.println(daniel.getGuestName() + " has checked out of room "
                + room2.getRoomNumber() + ".");
        System.out.println("Final bill: RM" + String.format("%.2f", bill));

        // ADDITIONAL CLASS 3: the invoice is generated automatically from
        // the numbers saved before checkout.
        HotelInvoice invoice1 = new HotelInvoice(5001, daniel.getGuestName(),
                room2.getRoomNumber(), room2.getRoomType(), nights, rate, bill,
                "19/11/2026");

        System.out.println("\n===== FINAL HOTEL INVOICE =====");
        invoice1.printInvoice();

        if (invoice1.confirmPayment()) {
            System.out.println("\nPayment successfully completed!");
        } else {
            System.out.println("\nPayment has already been completed.");
        }

        System.out.println("\n===== UPDATED INVOICE =====");
        invoice1.printInvoice();

        // Housekeeping (additional class 4)
        // A checkout leaves a room to clean, so housekeeping picks up the
        // work here: Aina takes the morning shift on the floor the guest
        // just left.
        System.out.println("\n===== HOUSEKEEPING =====");

        HotelEmployee staff1 = new HotelEmployee();
        HotelEmployee staff2 = new HotelEmployee(501, "Aina Yusof",
                "Housekeeping", "Morning", 80.00, 0, false);

        // IF-ELSE: startShift() refuses a second shift for
        // someone already working, and the printout shows which happened.
        if (staff2.startShift()) {
            System.out.println(staff2.getStaffName() + " started the "
                    + staff2.getShift() + " shift.");
        } else {
            System.out.println(staff2.getStaffName() + " is already on duty.");
        }

        // FOR LOOP: the corridor rooms are cleaned in order,
        // one assignRoom() call each. There are nine rooms but a shift is
        // capped at eight, so the last attempt must be rejected - the else
        // branch proves the cap inside assignRoom() works.
        int[] corridorRooms = {201, 202, 203, 204, 205, 206, 207, 208, 209};
        for (int r = 0; r < corridorRooms.length; r++) {
            if (staff2.assignRoom()) {
                System.out.println("Room " + corridorRooms[r] + " assigned to "
                        + staff2.getStaffName());
            } else {
                System.out.println("Cannot assign room " + corridorRooms[r]
                        + " - shift is full (max " + HotelEmployee.MAX_ROOMS_PER_SHIFT
                        + " rooms).");
            }
        }

        // Setters guarding bad input (encapsulation): an invented shift name
        // and a negative pay rate both fall back to safe defaults, visible
        // in staff1's roster line below.
        staff1.setShift("Midnight");
        staff1.setPayPerShift(-50.00);
        System.out.println(staff1.getEmployeeInfo());
        System.out.println(staff2.getEmployeeInfo());

        // End the shift: final pay = RM80.00 basic + RM10 for each room
        // beyond the first five - three extra rooms here.
        System.out.println("Pay for " + staff2.getStaffName() + ": RM"
                + String.format("%.2f", staff2.endShift()));

        // --- Guest reports ---
        System.out.println("\n===== GUEST DETAILS =====");

        // guest1 is filled in through setters to show attribute modification
        // from outside the object.
        guest1.setGuestName("Nurul Aisyah");
        guest1.setPhone("019-55667788");
        guest1.setTotalStays(guest1.getTotalStays() + 1);
        guest1.addPoints(50);
        System.out.println(guest1.getGuestInfo());

        System.out.println(ali.getGuestInfo());
        System.out.println(daniel.getGuestInfo());

        // IF-ELSE on the inherited isVip(): Daniel is VIP by tier, while Ali
        // is judged on his 120 points against the 500-point threshold.
        if (daniel.isVip()) {
            System.out.println(daniel.getGuestName() + " is a VIP guest.");
        } else {
            System.out.println(daniel.getGuestName() + " is a normal guest.");
        }

        if (ali.isVip()) {
            System.out.println(ali.getGuestName() + " is a VIP guest.");
        } else {
            System.out.println(ali.getGuestName() + " is a normal guest.");
        }

        // Distinctive RegularGuest method: redeem one of Ali's two vouchers.
        if (ali.redeemVoucher()) {
            System.out.println(ali.getGuestName() + " redeemed a free-night voucher. Vouchers left: "
                    + ali.getRewardVouchers());
        } else {
            System.out.println(ali.getGuestName() + " has no vouchers left.");
        }

        System.out.println("\nThank you for choosing Sunrise Hotel!");
    }
}
