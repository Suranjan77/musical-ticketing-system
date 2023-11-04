package org.musical.ticketing.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.musical.ticketing.domain.Domain;
import org.musical.ticketing.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface DomainRepository<T> {

    Logger log = LoggerFactory.getLogger(DomainRepository.class);

    default T save(Domain<T> instance) {
        var query = "INSERT INTO " + instance.tableName()
                + " (" + instance.getColumns().replace("id,", "").trim() + ")"
                + "VALUES (" + instance.getInsertQueryPlaceHolders() + ")";

        try (var con = DBConnection.instance().getConnection(); PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            instance.setPreparedStatement(stmt);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    var entityId = rs.getLong(1);
                    return findById(entityId, instance).get();
                }
            }
        } catch (SQLException e) {
            log.error("Failed to save data", e);
        }

        return null;
    }

    default List<T> findAll(Domain<T> instance) {
        var query = "SELECT " + instance.getColumns() + " FROM " + instance.tableName();

        List<T> data = new ArrayList<>();

        try (var con = DBConnection.instance().getConnection(); Statement stmt = con.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                data.add(instance.map(resultSet));
            }
        } catch (SQLException e) {
            log.error("Failed to fetch from table: {}", instance.tableName(), e);
        }

        return data;
    }

    default Optional<T> findById(Long id, Domain<T> instance) {
        var query
                = "SELECT " + instance.getColumns() + " FROM " + instance.tableName() + " WHERE id= " + id;
        try (var con = DBConnection.instance().getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return Optional.of(instance.map(rs));
            }
        } catch (SQLException e) {
            log.error("Failed to fetch from table: {}", instance.tableName(), e);
        }

        return Optional.empty();
    }
}
