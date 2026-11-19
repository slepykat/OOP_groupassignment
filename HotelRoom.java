/**
 * ADDITIONAL CLASS 1: room inventory.
 *
 * Purpose: tracks one physical room - its number, type, floor, nightly rate
 * and current occupant. Check-in and check-out are the room's job; pricing
 * the stay is the guest's job (Billable), so this class deliberately holds
 * no discount rules.
 */
public class HotelRoom {

    // --- Attributes: one bookable room ---
    private int roomNumber;        // door number, also the room's public identifier
    private String roomType;       // Standard, Single, Double or Suite
    private double pricePerNight;  // rack rate, before any guest-tier discount
    private int floor;             // floor the room sits on
    private String guestName;      // current occupant, empty while the room is free
    private int nightsBooked;      // length of the current stay
    private boolean occupied;      // occupancy flag behind the front-desk screen

    /** Default constructor: a standard room on floor 1 at the starter price. */
    public HotelRoom() {
        roomNumber = 100;
        roomType = "Standard";
        pricePerNight = 99.00;   // starter price for a room created without details
        floor = 1;
        guestName = "";
        nightsBooked = 0;
        occupied = false;
    }

    /**
     * Parameterized constructor. The price goes through its setter so a
     * typo like RM-150 cannot enter the system.
     */
    public HotelRoom(int roomNumber, String roomType, double pricePerNight,
            int floor, String guestName, int nightsBooked, boolean occupied) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        setPricePerNight(pricePerNight);
        this.floor = floor;
        this.guestName = guestName;
        this.nightsBooked = nightsBooked;
        this.occupied = occupied;
    }

    // --- Getters ---
    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public int getFloor() { return floor; }
    public String getGuestName() { return guestName; }
    public int getNightsBooked() { return nightsBooked; }
    public boolean isOccupied() { return occupied; }

    // --- Setters ---
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setFloor(int floor) { this.floor = floor; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    // A zero or negative nightly rate would let a room be sold for free;
    // anything invalid falls back to the starter price instead.
    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight > 0) {
            this.pricePerNight = pricePerNight;
        } else {
            this.pricePerNight = 99.00;
        }
    }

    public void setNightsBooked(int nightsBooked) {
        if (nightsBooked >= 0) {
            this.nightsBooked = nightsBooked;
        }
    }

    /**
     * Attempts to check a guest in. A room holds one occupant at a time and
     * a stay needs a real name and at least one night, so all of those
     * conditions are rejected here rather than trusted to the caller.
     */
    public boolean checkIn(String guest, int nights) {
        if (occupied || nights < 1 || guest == null || guest.trim().isEmpty()) {
            return false;
        }
        guestName = guest;
        nightsBooked = nights;
        occupied = true;
        return true;
    }

    /**
     * Clears the room once the bill has been settled. The caller must read
     * guestName, nightsBooked and pricePerNight first - after this call the
     * room is blank and ready for the next guest.
     */
    public void checkOut() {
        guestName = "";
        nightsBooked = 0;
        occupied = false;
    }

    // One-line status used by the room listing. 
    public String getRoomInfo() {
        String status = occupied ? "Occupied by " + guestName : "Available";
        return "Room " + roomNumber + " (" + roomType + ", Floor " + floor + ") RM"
                + String.format("%.2f", pricePerNight) + "/night - " + status;
    }
}
