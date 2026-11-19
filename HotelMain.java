class HotelRoom {

    // Encapsulated attributes of a hotel room
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private int floor;
    private String guestName;
    private int nightsBooked;
    private boolean occupied;

    // Default constructor
    public HotelRoom() {
        roomNumber = 100;
        roomType = "Standard";
        pricePerNight = 99.00;
        floor = 1;
        guestName = "";
        nightsBooked = 0;
        occupied = false;
    }

    // Parameterized constructor
    public HotelRoom(int roomNumber, String roomType,
            double pricePerNight, int floor, String guestName,
            int nightsBooked, boolean occupied) {

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        setPricePerNight(pricePerNight);
        this.floor = floor;
        this.guestName = guestName;
        this.nightsBooked = nightsBooked;
        this.occupied = occupied;
    }

    // Getter methods
    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public int getFloor() { return floor; }
    public String getGuestName() { return guestName; }
    public int getNightsBooked() { return nightsBooked; }
    public boolean isOccupied() { return occupied; }

    // Setter methods
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight > 0) {
            this.pricePerNight = pricePerNight;
        } else {
            this.pricePerNight = 99.00;
        }
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setNightsBooked(int nightsBooked) {
        if (nightsBooked >= 0) {
            this.nightsBooked = nightsBooked;
        }
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // Calculates room charges with a 10% discount
    // for stays of 5 nights or more
    public double calculateTotal() {

        double total = pricePerNight * nightsBooked;

        if (nightsBooked >= 5) {
            total = total * 0.90;
        }

        return total;
    }

    // Common method for polymorphism
    public String getRoomDescription() {
        return "Standard hotel room.";
    }

    // Checks in a guest if the room is available
    public boolean checkIn(String guest, int nights) {

        if (occupied || nights < 1 || guest == null
                || guest.trim().isEmpty()) {
            return false;
        }

        guestName = guest;
        nightsBooked = nights;
        occupied = true;

        return true;
    }

    // Checks out the guest and returns the final bill
    public double checkOut() {

        if (!occupied) {
            return 0.00;
        }

        double bill = calculateTotal();

        guestName = "";
        nightsBooked = 0;
        occupied = false;

        return bill;
    }

    // Displays room information
    public String getRoomInfo() {

        String status = occupied
                ? "Occupied by " + guestName
                : "Available";

        return "Room " + roomNumber + " (" + roomType
                + ", Floor " + floor + ") RM"
                + String.format("%.2f", pricePerNight)
                + "/night - " + status;
    }
}


// ============================================
// DERIVED CLASS 1: SINGLE ROOM
// ============================================

class SingleRoom extends HotelRoom {

    private boolean windowView;

    // Default constructor
    public SingleRoom() {
        super();
        setRoomType("Single");
        windowView = false;
    }

    // Parameterized constructor
    public SingleRoom(int roomNumber, double pricePerNight,
            int floor, String guestName, int nightsBooked,
            boolean occupied, boolean windowView) {

        super(roomNumber, "Single", pricePerNight, floor,
                guestName, nightsBooked, occupied);

        this.windowView = windowView;
    }

    // Getter and setter
    public boolean hasWindowView() {
        return windowView;
    }

    public void setWindowView(boolean windowView) {
        this.windowView = windowView;
    }

    // Overrides the common method
    @Override
    public String getRoomDescription() {

        if (windowView) {
            return "Single room with a window view for one guest.";
        } else {
            return "Single room suitable for one guest.";
        }
    }
}


// ============================================
// DERIVED CLASS 2: SUITE ROOM
// ============================================

class SuiteRoom extends HotelRoom {

    private boolean livingArea;

    // Default constructor
    public SuiteRoom() {
        super();
        setRoomType("Suite");
        livingArea = true;
    }

    // Parameterized constructor
    public SuiteRoom(int roomNumber, double pricePerNight,
            int floor, String guestName, int nightsBooked,
            boolean occupied, boolean livingArea) {

        super(roomNumber, "Suite", pricePerNight, floor,
                guestName, nightsBooked, occupied);

        this.livingArea = livingArea;
    }

    // Getter and setter
    public boolean hasLivingArea() {
        return livingArea;
    }

    public void setLivingArea(boolean livingArea) {
        this.livingArea = livingArea;
    }

    // Overrides the common method
    @Override
    public String getRoomDescription() {

        if (livingArea) {
            return "Luxury suite with a separate living area.";
        } else {
            return "Luxury suite with a spacious bedroom.";
        }
    }
}


// ============================================
// ADDITIONAL CLASS 1: HOTEL GUEST
// ============================================

class HotelGuest {

    // Private guest attributes
    private String guestName;
    private String icNumber;
    private String phone;
    private String email;
    private int loyaltyPoints;
    private int totalStays;
    private boolean member;

    // Default constructor
    public HotelGuest() {

        guestName = "New Guest";
        icNumber = "-";
        phone = "-";
        email = "-";
        loyaltyPoints = 0;
        totalStays = 0;
        member = false;
    }

    // Parameterized constructor
    public HotelGuest(String guestName, String icNumber,
            String phone, String email, int loyaltyPoints,
            int totalStays, boolean member) {

        this.guestName = guestName;
        this.icNumber = icNumber;
        this.phone = phone;
        this.email = email;
        setLoyaltyPoints(loyaltyPoints);
        setTotalStays(totalStays);
        this.member = member;
    }

    // Getters
    public String getGuestName() { return guestName; }
    public String getIcNumber() { return icNumber; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public int getTotalStays() { return totalStays; }
    public boolean isMember() { return member; }

    // Setters
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public void setMember(boolean member) {
        this.member = member;
    }

    // Adds loyalty points
    public void addPoints(int points) {

        if (points > 0) {
            loyaltyPoints += points;
        }
    }

    // Checks VIP membership
    public boolean isVip() {
        return loyaltyPoints >= 500;
    }

    // Displays guest information
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


// ============================================
// ADDITIONAL CLASS 2: HOTEL BOOKING
// ============================================

class HotelBooking {

    private int bookingId;
    private String guestName;
    private int roomNumber;
    private int nights;
    private double totalAmount;
    private String paymentStatus;
    private String bookingDate;

    // Default constructor
    public HotelBooking() {

        bookingId = 0;
        guestName = "-";
        roomNumber = 0;
        nights = 0;
        totalAmount = 0.00;
        paymentStatus = "PENDING";
        bookingDate = "-";
    }

    // Parameterized constructor
    public HotelBooking(int bookingId, String guestName,
            int roomNumber, int nights, double totalAmount,
            String paymentStatus, String bookingDate) {

        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        setNights(nights);
        setTotalAmount(totalAmount);
        this.paymentStatus = paymentStatus;
        this.bookingDate = bookingDate;
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public int getNights() { return nights; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getBookingDate() { return bookingDate; }

    // Setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

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

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Calculates booking amount
    public void calculateTotal(double ratePerNight) {

        totalAmount = ratePerNight * nights;

        if (nights >= 5) {
            totalAmount = totalAmount * 0.90;
        }
    }

    // Confirms payment
    public boolean confirmPayment() {

        if (paymentStatus.equals("PENDING")) {
            paymentStatus = "PAID";
            return true;
        }

        return false;
    }

    // Displays booking details
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


// ============================================
// ADDITIONAL CLASS 3: HOTEL INVOICE
// ============================================

class HotelInvoice {

    // Private invoice attributes
    private int invoiceId;
    private String guestName;
    private int roomNumber;
    private String roomType;
    private int nights;
    private double pricePerNight;
    private double subtotal;
    private double discount;
    private double totalAmount;
    private String paymentStatus;
    private String invoiceDate;

    // Constructor
    public HotelInvoice(int invoiceId, String guestName,
            int roomNumber, String roomType, int nights,
            double pricePerNight, double totalAmount,
            String invoiceDate) {

        this.invoiceId = invoiceId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
        this.subtotal = pricePerNight * nights;

        // Calculate discount
        if (nights >= 5) {
            discount = subtotal * 0.10;
        } else {
            discount = 0;
        }

        this.totalAmount = totalAmount;
        this.paymentStatus = "PENDING";
        this.invoiceDate = invoiceDate;
    }

    // Getters
    public int getInvoiceId() { return invoiceId; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }

    // Setter
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Prints final invoice after checkout
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

        System.out.printf("Price Per Night : RM %.2f%n",
                pricePerNight);

        System.out.println("----------------------------------------");

        System.out.printf("Subtotal        : RM %.2f%n",
                subtotal);

        System.out.printf("Discount        : RM %.2f%n",
                discount);

        System.out.println("----------------------------------------");

        System.out.printf("TOTAL BILL      : RM %.2f%n",
                totalAmount);

        System.out.println("Payment Status  : " + paymentStatus);

        System.out.println("========================================");
        System.out.println("    Thank you for staying with us!");
        System.out.println("========================================");
    }

    // Confirms invoice payment
    public boolean confirmPayment() {

        if (paymentStatus.equals("PENDING")) {
            paymentStatus = "PAID";
            return true;
        }

        return false;
    }
}


// ============================================
// MAIN CLASS: HOTEL MAIN
// ============================================

public class HotelMain {

    public static void main(String[] args) {

        // Create hotel rooms using constructors
        HotelRoom room1 = new HotelRoom();

        HotelRoom room2 = new SingleRoom(
                201, 150.00, 2, "", 0, false, true);

        HotelRoom room3 = new SuiteRoom(
                301, 260.00, 3, "Sara Lim", 6, true, true);

        // Create guest objects
        HotelGuest guest1 = new HotelGuest();

        HotelGuest guest2 = new HotelGuest(
                "Daniel Tan",
                "020315-14-5567",
                "012-3456789",
                "daniel@mail.com",
                520,
                4,
                true
        );

        // Create a booking
        HotelBooking booking1 = new HotelBooking(
                1001,
                "Sara Lim",
                301,
                6,
                0.00,
                "PENDING",
                "19/11/2026"
        );

        booking1.calculateTotal(260.00);

        System.out.println("===== WELCOME TO SUNRISE HOTEL SYSTEM =====");

        // Store rooms in an array
        HotelRoom[] rooms = {room1, room2, room3};

        // FOR LOOP: Display all rooms
        System.out.println("\n===== HOTEL ROOM INFORMATION =====");

        for (int i = 0; i < rooms.length; i++) {

            System.out.println(rooms[i].getRoomInfo());

            // POLYMORPHISM:
            // Each room uses its own overridden description
            System.out.println("Description: "
                    + rooms[i].getRoomDescription());
        }

        // SWITCH: Describe room types
        System.out.println("\n===== ROOM TYPE INFORMATION =====");

        switch (room3.getRoomType()) {

            case "Single":
                System.out.println(
                        "Single room comes with one bed.");
                break;

            case "Double":
                System.out.println(
                        "Double room comes with two beds.");
                break;

            case "Suite":
                System.out.println(
                        "Suite is the biggest room with a living area.");
                break;

            default:
                System.out.println("Unknown room type.");
        }

        // IF-ELSE: Check in a guest to room 201
        System.out.println("\n===== GUEST CHECK-IN =====");

        if (room2.checkIn("Ali bin Abu", 3)) {

            System.out.println(
                    "Ali bin Abu checked in to room 201.");

        } else {

            System.out.println(
                    "Room 201 is already occupied.");
        }

        // Attempt to check in another guest to occupied room
        if (room3.checkIn("Test Guest", 2)) {

            System.out.println(
                    "Checked in to room 301.");

        } else {

            System.out.println(
                    "Cannot check in, room 301 is already occupied.");
        }

        // Update guest information using setters and getters
        System.out.println("\n===== GUEST INFORMATION =====");

        guest1.setTotalStays(
                guest1.getTotalStays() + 1);

        guest1.addPoints(50);

        System.out.println(
                "Guest points now: "
                + guest1.getLoyaltyPoints());

        // WHILE LOOP: Count occupied rooms
        int i = 0;
        int occupiedCount = 0;

        while (i < rooms.length) {

            if (rooms[i].isOccupied()) {
                occupiedCount++;
            }

            i++;
        }

        System.out.println(
                "Occupied rooms: " + occupiedCount);

        // Update room price using setter
        room1.setPricePerNight(120.00);

        System.out.println(
                "Room 100 new price: RM"
                + room1.getPricePerNight());

        // ====================================
        // CHECKOUT AND AUTOMATIC INVOICE
        // ====================================

        System.out.println("\n===== GUEST CHECK-OUT =====");

        // Save guest and room details before checkout
        // because checkout clears the room information
        String guest = room3.getGuestName();

        int roomNumber = room3.getRoomNumber();

        String roomType = room3.getRoomType();

        int nights = room3.getNightsBooked();

        double rate = room3.getPricePerNight();

        // Check out guest and calculate final bill
        double bill = room3.checkOut();

        System.out.println(
                guest + " has successfully checked out.");

        System.out.println(
                "Final bill for room 301: RM"
                + String.format("%.2f", bill));

        // Create invoice automatically after checkout
        HotelInvoice invoice1 = new HotelInvoice(
                5001,
                guest,
                roomNumber,
                roomType,
                nights,
                rate,
                bill,
                "19/11/2026"
        );

        // Display invoice with pending payment
        System.out.println("\n===== FINAL HOTEL INVOICE =====");

        invoice1.printInvoice();

        // Confirm invoice payment
        if (invoice1.confirmPayment()) {

            System.out.println(
                    "\nPayment successfully completed!");

        } else {

            System.out.println(
                    "\nPayment has already been completed.");
        }

        // Display updated invoice after payment
        System.out.println("\n===== UPDATED INVOICE =====");

        invoice1.printInvoice();

        // Display guest information
        System.out.println("\n===== GUEST DETAILS =====");

        System.out.println(guest1.getGuestInfo());

        System.out.println(guest2.getGuestInfo());

        // Check VIP status
        if (guest2.isVip()) {

            System.out.println(
                    guest2.getGuestName()
                    + " is a VIP guest.");

        } else {

            System.out.println(
                    guest2.getGuestName()
                    + " is a normal guest.");
        }

        // Display booking details
        System.out.println("\n===== BOOKING INFORMATION =====");

        System.out.println(booking1.getBookingInfo());

        // Confirm booking payment
        if (booking1.getPaymentStatus().equals("PENDING")) {

            booking1.confirmPayment();

            System.out.println(
                    "Payment confirmed for booking "
                    + booking1.getBookingId());
        }

        // Display updated booking
        System.out.println(booking1.getBookingInfo());

        System.out.println(
                "\nThank you for choosing Sunrise Hotel!");
    }
}
