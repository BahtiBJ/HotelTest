# HotelTest
Multimodular application template for choosing a hotel and tour for travel

# Structure
At the moment, the data is obtained from the stub (data-mock module). This module also features caching using Room.
You can implement your own interface from the data module to receive data. To work with the network, the network module is used. To work with databases, the database module is used.

# Stack
Language: Kotlin. \
Network: Retrofit, OkHttp. \
Database: Room. \
DI: Koin. \
UI: Adapter Delegate,FlexBoxLayout,Picasso.

# Capabilities
On the first page you can see information about the hotel.

![](illustrations/hotel.gif "Hotel info")

On the second page you can select a room and see detailed information about it.

![](illustrations/rooms.gif "Rooms")

On the third page for booking you need to provide information about yourself, and here you can find out the final price for the tour.

![](illustrations/booking.gif "Booking a tour")

The fourth page contains the status of your order.

![](illustrations/order.jpg "Order")
