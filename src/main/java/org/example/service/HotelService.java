package org.example.service;

import org.example.enums.RoomType;
import org.example.model.Booking;
import org.example.model.Room;
import org.example.model.User;

import java.util.ArrayList;
import java.util.Date;

public class HotelService {
        private ArrayList<Room> rooms = new ArrayList<>();
        private ArrayList<User> users = new ArrayList<>();
        private ArrayList<Booking> bookings = new ArrayList<>();

        public void setRoom(int roomNumber, RoomType type, int pricePerNight) {
            for (Room r : rooms) {
                if (r.getRoomNumber() == roomNumber) return; // déjà existante
            }
            rooms.add(new Room(roomNumber, type, pricePerNight));
        }

        public void setUser(int userId, int balance) {
            for (User u : users) {
                if (u.getId() == userId) return;
            }
            users.add(new User(userId, balance));
        }

        public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
            if (checkOut.before(checkIn)) {
                System.out.println("❌ Date invalide : checkOut avant checkIn.");
                return;
            }

            Room room = rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null);
            User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);

            if (room == null || user == null) {
                System.out.println("❌ Utilisateur ou chambre introuvable.");
                return;
            }

            // Vérifier disponibilité
            for (Booking b : bookings) {
                if (b.getRoom().getRoomNumber() == roomNumber &&
                        datesOverlap(b.getCheckIn(), b.getCheckOut(), checkIn, checkOut)) {
                    System.out.println("❌ Chambre déjà réservée sur cette période.");
                    return;
                }
            }

            int nights = (int)((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
            int totalCost = nights * room.getPricePerNight();

            if (user.getBalance() < totalCost) {
                System.out.println("❌ Solde insuffisant.");
                return;
            }

            user.deductBalance(totalCost);
            bookings.add(0, new Booking(room, user, checkIn, checkOut)); // du plus récent au plus ancien
            System.out.println("✅ Réservation réussie.");
        }

        public void printAll() {
            System.out.println("------ ROOMS ------");
            rooms.forEach(System.out::println);

            System.out.println("\n------ BOOKINGS ------");
            bookings.forEach(System.out::println);
        }

        public void printAllUsers() {
            System.out.println("------ USERS ------");
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.println(users.get(i));
            }
        }

        private boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
            return !end1.before(start2) && !end2.before(start1);
        }

}
