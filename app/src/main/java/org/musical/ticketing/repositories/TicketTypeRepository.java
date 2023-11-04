package org.musical.ticketing.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import org.musical.ticketing.domain.TicketType;
import org.musical.ticketing.domain.TicketTypeEnum;
import org.musical.ticketing.util.DBConnection;

public class TicketTypeRepository implements DomainRepository<TicketType> {

    private final TicketType instance = TicketType.empty();

    public Optional<TicketType> findByMusicalIdAndType(Long musicalId, TicketTypeEnum type) {
        String query = "SELECT " + instance.getColumns() + " FROM " + instance.tableName()
                + " WHERE musical_id=? AND name=?";

        try (var con = DBConnection.instance().getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, musicalId);
            stmt.setString(2, type.getValue());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(instance.map(rs));
            }
        } catch (SQLException e) {
            log.error("Exception fetching ticket type", e);
        }

        return Optional.empty();
    }
}
