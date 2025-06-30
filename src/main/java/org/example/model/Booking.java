package org.example.model;
import java.util.Date;
public class Booking {
        private Room room;
        private User user;
        private Date checkIn;
        private Date checkOut;

        public Booking(Room room, User user, Date checkIn, Date checkOut) {
            this.room = room;
            this.user = user;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        public Room getRoom() { return room; }
        public User getUser() { return user; }
        public Date getCheckIn() { return checkIn; }
        public Date getCheckOut() { return checkOut; }

        @Override
        public String toString() {
            return "Booking{" +
                    "room=" + room +
                    ", user=" + user +
                    ", checkIn=" + checkIn +
                    ", checkOut=" + checkOut +
                    '}';
        }
}
