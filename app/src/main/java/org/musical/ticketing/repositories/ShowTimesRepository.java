package org.musical.ticketing.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.musical.ticketing.domain.ShowTime;
import static org.musical.ticketing.repositories.DomainRepository.log;
import org.musical.ticketing.util.DBConnection;

public class ShowTimesRepository implements DomainRepository<ShowTime> {

    private final ShowTime instance = ShowTime.empty();

    public List<ShowTime> findByMusicalIdForDate(Long musicalId, LocalDate date) {
        var query = "SELECT " + instance.getColumns() + " FROM " + instance.tableName()
                + " WHERE DATE(show_date)=? AND musical_id=?";

        List<ShowTime> showTimes = new ArrayList<>();

        try (var con = DBConnection.instance().getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(date));
            stmt.setLong(2, musicalId);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                showTimes.add(instance.map(rs));
            }

        } catch (SQLException e) {
            log.error("Failed to save data", e);
        }

        return showTimes;
    }

    public ShowTime updateSeatCount(Long id, int remainingSeats) {
        var query = "UPDATE " + instance.tableName()
                + " SET available_seats=? WHERE id=?";

        try (var con = DBConnection.instance().getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, remainingSeats);
            stmt.setLong(2, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 1) {
                return findById(id, instance).get();
            }
        } catch (SQLException e) {
            log.error("Failed to save data", e);
        }

        return null;
    }
}
