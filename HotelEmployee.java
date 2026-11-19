/**
 * ADDITIONAL CLASS 4: staffing and housekeeping operations.
 * Written by Rakhimbayev Akhmet.
 *
 * Purpose: tracks one staff member through a working shift - which rooms
 * they were assigned, whether they are on duty, and what the shift pays.
 * The pay rule is the hotel's: basic shift pay, plus a RM10 bonus for every
 * room beyond the first five, capped at eight rooms because one housekeeper
 * cannot clean more in a single shift.
 *
 * The class connects to the room side of the system through HotelMain: the
 * housekeeping demo runs right after a checkout, cleaning the floor the
 * guest just left.
 */
public class HotelEmployee {

    // Housekeeping rules for one shift
    public static final int MAX_ROOMS_PER_SHIFT = 8;         // one housekeeper cannot clean more in a single shift
    public static final int BONUS_THRESHOLD_ROOMS = 5;       // the first 5 rooms earn no bonus
    public static final double BONUS_PER_EXTRA_ROOM = 10.00; // RM10 bonus per room beyond the threshold
    public static final double DEFAULT_PAY_PER_SHIFT = 80.00; // starter rate for a record created without pay details

    //   Attributes: one staff record  
    private int staffId;        // employee number on the hotel roster
    private String staffName;   // name shown on the roster and payslip
    private String role;        // department: Housekeeping, Reception or Chef
    private String shift;       // Morning, Evening or Night
    private double payPerShift; // basic pay for one shift, before room bonuses
    private int roomsAssigned;  // rooms cleaned so far in the current shift
    private boolean onDuty;     // true while the staff member is working

    /** Default constructor: a blank staff record at the default pay rate. */
    public HotelEmployee() {
        staffId = 0;
        staffName = "New Staff";
        role = "Housekeeping";
        shift = "Morning";
        payPerShift = DEFAULT_PAY_PER_SHIFT;
        roomsAssigned = 0;
        onDuty = false;
    }

    /**
     * Parameterized constructor. shift, payPerShift and roomsAssigned go
     * through their setters, so an invented shift name or a negative pay
     * rate cannot enter the system through this path either.
     */
    public HotelEmployee(int staffId, String staffName, String role, String shift,
            double payPerShift, int roomsAssigned, boolean onDuty) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.role = role;
        setShift(shift);
        setPayPerShift(payPerShift);
        setRoomsAssigned(roomsAssigned);
        this.onDuty = onDuty;
    }

    //   Getters  
    public int getStaffId() { return staffId; }
    public String getStaffName() { return staffName; }
    public String getRole() { return role; }
    public String getShift() { return shift; }
    public double getPayPerShift() { return payPerShift; }
    public int getRoomsAssigned() { return roomsAssigned; }
    public boolean isOnDuty() { return onDuty; }

    //   Setters  
    public void setStaffId(int staffId) { this.staffId = staffId; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public void setRole(String role) { this.role = role; }

    // Only the three real shifts are accepted; anything else (a typo like
    // "Midnight") falls back to Morning instead of entering the roster.
    public void setShift(String shift) {
        if (shift.equals("Morning") || shift.equals("Evening") || shift.equals("Night")) {
            this.shift = shift;
        } else {
            this.shift = "Morning";
        }
    }

    // Pay cannot be zero or negative; invalid values fall back to the
    // default rate rather than a free or paying-the-hotel shift.
    public void setPayPerShift(double payPerShift) {
        if (payPerShift > 0) {
            this.payPerShift = payPerShift;
        } else {
            this.payPerShift = DEFAULT_PAY_PER_SHIFT;
        }
    }

    // A negative count of cleaned rooms is impossible, so it is ignored.
    public void setRoomsAssigned(int roomsAssigned) {
        if (roomsAssigned >= 0) {
            this.roomsAssigned = roomsAssigned;
        }
    }

    public void setOnDuty(boolean onDuty) { this.onDuty = onDuty; }

    /**
     * Starts the shift. Returns false when the staff member is already on
     * duty, so a double tap cannot fake a second shift.
     */
    public boolean startShift() {
        if (onDuty) {
            return false;
        }
        onDuty = true;
        return true;
    }

    /**
     * Assigns one more room to clean. A room can only be assigned while the
     * staff member is on duty, and no housekeeper cleans more than
     * MAX_ROOMS_PER_SHIFT rooms in one shift.
     */
    public boolean assignRoom() {
        if (!onDuty || roomsAssigned >= MAX_ROOMS_PER_SHIFT) {
            return false;
        }
        roomsAssigned++;
        return true;
    }

    /**
     * The shift's pay: basic payPerShift, plus RM10 for every room beyond
     * the first five - the hotel's reward for taking on extra rooms.
     */
    public double calculatePay() {
        double pay = payPerShift;
        if (roomsAssigned > BONUS_THRESHOLD_ROOMS) {
            pay = pay + (roomsAssigned - BONUS_THRESHOLD_ROOMS) * BONUS_PER_EXTRA_ROOM;
        }
        return pay;
    }

    /**
     * Ends the shift: hands back the final pay, then clears the room count
     * and the duty flag so the next shift starts from zero.
     */
    public double endShift() {
        double pay = calculatePay();
        roomsAssigned = 0;
        onDuty = false;
        return pay;
    }

    /** One-line roster entry for the front-desk screen. */
    public String getEmployeeInfo() {
        String status = onDuty ? "On duty" : "Off duty";
        return "Staff " + staffId + ": " + staffName + " (" + role + ") | Shift: " + shift
                + " | Rooms assigned: " + roomsAssigned + " | " + status;
    }
}
