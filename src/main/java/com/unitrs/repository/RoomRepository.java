package com.unitrs.repository;

import com.unitrs.model.entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomRepository extends BaseRepository {

    public List<Room> findAllRooms() {
        String sql = "SELECT * FROM rooms ORDER BY floor_number ASC, room_number ASC";
        return executeQuery(sql, this::mapResultSetToRoom);
    }

    public Room findById(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        return executeQueryForObject(sql, this::mapResultSetToRoom, id);
    }

    public Room findByNumber(String roomNumber) {
        String sql = "SELECT * FROM rooms WHERE LOWER(room_number) = LOWER(?)";
        return executeQueryForObject(sql, this::mapResultSetToRoom, roomNumber);
    }

    public boolean save(Room room) {
        String sql = "INSERT INTO rooms (room_number, floor_number, capacity) VALUES (?, ?, ?)";
        return executeUpdate(sql, room.getRoomNumber(), room.getFloorNumber(), room.getCapacity()) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        return executeUpdate(sql, id) > 0;
    }

    private Room mapResultSetToRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setRoomNumber(rs.getString("room_number"));
        room.setFloorNumber(rs.getInt("floor_number"));
        room.setCapacity(rs.getInt("capacity"));
        return room;
    }
}
