package za.co.discovery.atm.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import za.co.discovery.atm.web.dto.FinancialPositionPerClient;
import za.co.discovery.atm.web.dto.HighestBalanceAccountPerClient;
import za.co.discovery.atm.repository.ReportRepository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HighestBalanceAccountPerClient> findHighestBalanceAccountsPerClient() {
        final String query = "SELECT c.CLIENT_ID AS clientId , \n" +
                "               c.SURNAME AS clientSurname, \n" +
                "               ca.CLIENT_ACCOUNT_NUMBER AS clientAccountNumber,\n" +
                "               at.DESCRIPTION AS accountDescription,\n" +
                "               ca.DISPLAY_BALANCE AS displayBalance\n" +
                " FROM CLIENT c INNER JOIN CLIENT_ACCOUNT ca ON c.CLIENT_ID = ca.CLIENT_ID INNER JOIN ACCOUNT_TYPE at ON at.ACCOUNT_TYPE_CODE = ca.ACCOUNT_TYPE_CODE";

        return jdbcTemplate.query(query, new RowMapper<HighestBalanceAccountPerClient>() {
            @Override
            public HighestBalanceAccountPerClient mapRow(ResultSet resultSet, int i) throws SQLException {
                HighestBalanceAccountPerClient highestBalanceAccountPerClient = new HighestBalanceAccountPerClient();
                highestBalanceAccountPerClient.setClientId(resultSet.getInt("clientId"));
                highestBalanceAccountPerClient.setCllientSurname(resultSet.getString("clientSurname"));
                highestBalanceAccountPerClient.setClientAccountNumber(resultSet.getString("clientAccountNumber"));
                highestBalanceAccountPerClient.setAccountDescription(resultSet.getString("accountDescription"));
                highestBalanceAccountPerClient.setDisplayBalnace(resultSet.getBigDecimal("displayBalance"));
                return highestBalanceAccountPerClient;
            }
        });
    }
}
