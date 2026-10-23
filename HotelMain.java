// All three object classes in one file (All members)
class HotelRoom {
    // Attributes with encapsulation by making them all private
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private int floor;
    private String guestName; 
    private int nightsBooked;
    private boolean occupied; 
    // Default room constructor
    public HotelRoom() {
        roomNumber = 100; roomType = "Single"; pricePerNight = 99.00;
        floor = 1; guestName = ""; nightsBooked = 0; occupied = false;
    }
    // Parameterized constructor
    public HotelRoom(int roomNumber, String roomType, double pricePerNight, int floor, String guestName, int nightsBooked, boolean occupied) {
        this.roomNumber = roomNumber; this.roomType = roomType; this.pricePerNight = pricePerNight;
        this.floor = floor; this.guestName = guestName; this.nightsBooked = nightsBooked; this.occupied = occupied;
    }
    // Getters
    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public int getFloor() { return floor; }
    public String getGuestName() { return guestName; }
    public int getNightsBooked() { return nightsBooked; }
    public boolean isOccupied() { return occupied; }
    // Setters
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    // Sets price to default if its negative
    public void setPricePerNight(double pricePerNight) { if (pricePerNight > 0) { this.pricePerNight = pricePerNight; } else { this.pricePerNight = 99.00; } }
    public void setFloor(int floor) { this.floor = floor; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setNightsBooked(int nightsBooked) { if (nightsBooked >= 0) { this.nightsBooked = nightsBooked; } }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    // Calculates total price, add 10% discount for over 5 nights booking
    public double calculateTotal() {
        double total = pricePerNight * nightsBooked;
        if (nightsBooked >= 5) { total = total * 0.90; }
        return total;
    }
    // Checks the guest in if the room is not occupied
    public boolean checkIn(String guest, int nights) {
        if (occupied) { return false; }
        guestName = guest; nightsBooked = nights; occupied = true;
        return true;
    }
    // Check out, returns total bill and sets occupied status to false
    public double checkOut() {
        double bill = calculateTotal();
        guestName = ""; nightsBooked = 0; occupied = false;
        return bill;
    }
    // Summary of the room
    public String getRoomInfo() {
        String status = "Available";
        if (occupied) { status = "Occupied by " + guestName; }
        return "Room " + roomNumber + " (" + roomType + ", floor " + floor + ") RM" + pricePerNight + "/night - " + status;
    }
}

class HotelGuest {
    // Encapsulated attributes (all private)
    private String guestName;
    private String icNumber;
    private String phone;
    private String email;
    private int loyaltyPoints;
    private int totalStays;
    private boolean member;
    // Default blank guest constructor
    public HotelGuest() {
        guestName = "New Guest"; icNumber = "-"; phone = "-"; email = "-";
        loyaltyPoints = 0; totalStays = 0; member = false;
    }
    // Parameterized constructor
    public HotelGuest(String guestName, String icNumber, String phone, String email, int loyaltyPoints, int totalStays, boolean member) {
        this.guestName = guestName; this.icNumber = icNumber; this.phone = phone; this.email = email;
        this.loyaltyPoints = loyaltyPoints; this.totalStays = totalStays; this.member = member;
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
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setIcNumber(String icNumber) { this.icNumber = icNumber; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    // Points must not be negative
    public void setLoyaltyPoints(int loyaltyPoints) { if (loyaltyPoints >= 0) { this.loyaltyPoints = loyaltyPoints; } }
    // Stays must not be negative
    public void setTotalStays(int totalStays) { if (totalStays >= 0) { this.totalStays = totalStays; } }
    public void setMember(boolean member) { this.member = member; }

    // Adds loyalty points to guests balance
    public void addPoints(int points) { if (points > 0) { loyaltyPoints = loyaltyPoints + points; } }
    // Guest can get VIP status if they have over 500 points
    public boolean isVip() { return loyaltyPoints >= 500; }
    // Summary of the guest
    public String getGuestInfo() {
        return "Guest: " + guestName + " | IC: " + icNumber + " | Phone: " + phone + " | Email: " + email + " | Points: " + loyaltyPoints + " | Stays: " + totalStays + " | Member: " + member;
    }
}

class HotelBooking {
    // Attributes (private/encapsulation)
    private int bookingId;
    private String guestName;
    private int roomNumber;
    private int nights;
    private double totalAmount;
    private String paymentStatus;
    private String bookingDate;
    // Default constructor
    public HotelBooking() {
        bookingId = 0; guestName = "-"; roomNumber = 0; nights = 0;
        totalAmount = 0.00; paymentStatus = "PENDING"; bookingDate = "-";
    }
    // Parameterized constructor
    public HotelBooking(int bookingId, String guestName, int roomNumber, int nights, double totalAmount, String paymentStatus, String bookingDate) {
        this.bookingId = bookingId; this.guestName = guestName; this.roomNumber = roomNumber; this.nights = nights;
        this.totalAmount = totalAmount; this.paymentStatus = paymentStatus; this.bookingDate = bookingDate;
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
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    // A booking must be for at least 1 night
    public void setNights(int nights) { if (nights >= 1) { this.nights = nights; } }
    // Amount cannot be negative
    public void setTotalAmount(double totalAmount) { if (totalAmount >= 0) { this.totalAmount = totalAmount; } }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    // Calculates the booking amount; 10% discount over 5 nights
    public void calculateTotal(double ratePerNight) {
        totalAmount = ratePerNight * nights;
        if (nights >= 5) { totalAmount = totalAmount * 0.90; }
    }
    // Changes the status from PENDING to PAID
    public boolean confirmPayment() {
        if (paymentStatus.equals("PENDING")) { paymentStatus = "PAID"; return true; }
        return false;
    }
    // Returns a summary of the booking
    public String getBookingInfo() {
        return "Booking #" + bookingId + " | " + guestName + " | Room " + roomNumber + " | " + nights + " night(s) | RM" + totalAmount + " | " + paymentStatus + " | " + bookingDate;
    }
}

// Main class to test HotelRoom, HotelGuest and HotelBooking
public class HotelMain {

    public static void main(String[] args) {
        // Create rooms, guests and a booking using both constructors
        HotelRoom room1 = new HotelRoom();
        HotelRoom room2 = new HotelRoom(201, "Double", 150.00, 2, "", 0, false);
        HotelRoom room3 = new HotelRoom(301, "Suite", 260.00, 3, "Sara Lim", 6, true);
        HotelGuest guest1 = new HotelGuest();
        HotelGuest guest2 = new HotelGuest("Daniel Tan", "020315-14-5567", "012-3456789", "daniel@mail.com", 520, 4, true);
        HotelBooking booking1 = new HotelBooking(1001, "Sara Lim", 301, 6, 0.00, "PENDING", "20/10/2026");
        booking1.calculateTotal(260.00);

        System.out.println("===== WELCOME TO SUNRISE HOTEL SYSTEM =====");

        // Show all rooms using a for loop
        HotelRoom[] rooms = {room1, room2, room3};
        for (int i = 0; i < rooms.length; i++) { System.out.println(rooms[i].getRoomInfo()); }

        // Describe the room types using switch
        switch (room3.getRoomType()) {
            case "Single": System.out.println("Single room comes with 1 bed for 1 person."); break;
            case "Double": System.out.println("Double room comes with 2 beds for 2 people."); break;
            case "Suite":  System.out.println("Suite is the biggest room with a living area."); break;
            default:       System.out.println("Unknown room type.");
        }

        // Check in Ali to room 201, and try room 301 which is already taken
        if (room2.checkIn("Ali bin Abu", 3)) { System.out.println("Ali bin Abu checked in to room 201."); }
        else { System.out.println("Room 201 is already occupied."); }
        if (room3.checkIn("Test Guest", 2)) { System.out.println("Checked in to room 301."); }
        else { System.out.println("Cannot check in, room 301 is already occupied."); }

        // Update guest1 using a setter and getter, then add points
        guest1.setTotalStays(guest1.getTotalStays() + 1);
        guest1.addPoints(50);
        System.out.println("Guest points now: " + guest1.getLoyaltyPoints());

        // Count occupied rooms using a while loop
        int i = 0;
        int occupiedCount = 0;
        while (i < rooms.length) {
            if (rooms[i].isOccupied()) { occupiedCount++; }
            i++;
        }
        System.out.println("Occupied rooms: " + occupiedCount);

        // Change the price of room 100 using a setter
        room1.setPricePerNight(120.00);
        System.out.println("Room 100 new price: RM" + room1.getPricePerNight() + " per night");

        // Check out Sara Lim and show the final bill with the discount
        double bill = room3.checkOut();
        System.out.println("Final bill for room 301: RM" + bill);

        // Show guest details and check the VIP status
        System.out.println(guest1.getGuestInfo());
        System.out.println(guest2.getGuestInfo());
        if (guest2.isVip()) { System.out.println(guest2.getGuestName() + " is a VIP guest"); }
        else { System.out.println(guest2.getGuestName() + " is a normal guest"); }

        // Show the booking and confirm the payment
        System.out.println(booking1.getBookingInfo());
        if (booking1.getPaymentStatus().equals("PENDING")) {
            booking1.confirmPayment();
            System.out.println("Payment confirmed for booking " + booking1.getBookingId());
        }
        System.out.println(booking1.getBookingInfo());

        System.out.println("\nThank you for choosing the Sunrise Hotel");
    }
}
