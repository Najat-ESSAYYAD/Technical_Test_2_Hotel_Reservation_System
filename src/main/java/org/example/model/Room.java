package org.example.model;

import org.example.enums.RoomType;

public class Room {
        private int roomNumber;
        private RoomType roomType;
        private int pricePerNight;

        public Room(int roomNumber, RoomType roomType, int pricePerNight) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
        }

        public int getRoomNumber() { return roomNumber; }
        public RoomType getRoomType() { return roomType; }
        public int getPricePerNight() { return pricePerNight; }

        @Override
        public String toString() {
            return "Room{" +
                    "number=" + roomNumber +
                    ", type=" + roomType +
                    ", price=" + pricePerNight +
                    '}';
        }

}
