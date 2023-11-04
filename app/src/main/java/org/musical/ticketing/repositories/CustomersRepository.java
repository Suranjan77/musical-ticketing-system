package org.musical.ticketing.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.musical.ticketing.domain.Customer;
import org.musical.ticketing.util.DBConnection;

public class CustomersRepository implements DomainRepository<Customer> {

    private final Customer instance = Customer.empty();

    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        String query = "SELECT " + instance.getColumns() + " FROM " + instance.tableName() + " WHERE phone_number='" + phoneNumber + "'";
        try (var con = DBConnection.instance().getConnection(); var stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return Optional.ofNullable(instance.map(rs));
            }
        } catch (SQLException e) {
            log.error("Exception fetching data", e);
        }

        return Optional.empty();
    }
}
