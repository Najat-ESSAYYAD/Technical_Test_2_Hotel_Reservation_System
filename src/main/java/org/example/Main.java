package org.example;
import org.example.enums.RoomType;
import org.example.service.HotelService;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception{
                HotelService service = new HotelService();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                // Create 3 rooms
                service.setRoom(1, RoomType.STANDARD_SUITE, 1000);
                service.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
                service.setRoom(3, RoomType.MASTER_SUITE, 3000);

                // Create 2 users, with IDs 1 and 2 and balance 5000, 10000
                service.setUser(1, 5000);
                service.setUser(2, 10000);

                // User 1 tries booking Room 2 from 30/06/2026 to 07/07/2026 (7//nights).
                service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026")); // ok
                // User 1 tries booking Room 2 from 07/07/2026 to 30/06/2026.
                service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026")); // dates invalides
                // User 1 tries booking Room 1 from 07/07/2026 to 08/07/2026 (1//night).
                service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026")); // ok
                // User 2 tries booking Room 1 from 07/07/2026 to 09/07/2026 (2//nights).
                service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026")); // conflit
                // User 2 tries booking Room 3 from 07/07/2026 to 08/07/2026 (1 night).
                service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026")); // ok

                // set Room
                service.setRoom(1, RoomType.MASTER_SUITE, 10000);

                // Affichage
                service.printAll();
                service.printAllUsers();
            }
        }
