package Classes;

import Enums.Furnish;
import Enums.Transport;
import Enums.View;
import lombok.Data;


public record FlatUpdateRecord(String name, CoordinatesUpdateRecord coordinates, Double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, HouseUpdateRecord house) {
}
