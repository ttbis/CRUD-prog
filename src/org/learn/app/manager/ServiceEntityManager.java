package org.learn.app.manager;

import org.learn.app.App;
import org.learn.app.entity.ServiceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEntityManager
{
    public static void insert(ServiceEntity service) throws SQLException
    {
        try (Connection c = App.getConnection())
        {
            String sql = "INSERT INTO Service(Title, Cost, Duration, Description, Discount, MainImagePath) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, service.getTitle());
            ps.setDouble(2, service.getCost());
            ps.setInt(3, service.getDuration());
            ps.setString(4, service.getDesc());
            ps.setDouble(5, service.getDiscount());
            ps.setString(6, service.getImagePath());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){
                service.setId(keys.getInt(1));
                return;
            }

        }
        throw new SQLException("entity not added");
    }

    public static List<ServiceEntity> selectAll() throws SQLException
    {
        try (Connection c = App.getConnection()){
            String sql = "SELECT * FROM service";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            List<ServiceEntity> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new ServiceEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getInt("Duration"),
                        resultSet.getDouble("Cost"),
                        resultSet.getDouble("Discount"),
                        resultSet.getString("Description"),
                        resultSet.getString("MainImagePath")
                ));
            }
            return list;
        }
    }

    public static void delete(int id) throws SQLException
    {
        try (Connection c = App.getConnection()){
            String sql = "DELETE FROM Service WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
