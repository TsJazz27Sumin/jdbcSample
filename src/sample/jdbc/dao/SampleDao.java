package sample.jdbc.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sample.jdbc.model.Worker;

public class SampleDao {

	public static SampleDao instance = new SampleDao();

	private SampleDao () {

	}

	public List<Worker> select() throws SQLException {

		var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
		var statment = connection.createStatement();

        var sql = "select * from worker";
        var resultSet = statment.executeQuery(sql);

        var workerList = new ArrayList<Worker>();

        while(resultSet.next()){

            var worker = new Worker();

            worker.setId(resultSet.getString("id"));
            worker.setName(resultSet.getString("name"));
            worker.setAge(String.valueOf(resultSet.getInt("age")));
            worker.setDepartment(resultSet.getString("department"));

            workerList.add(worker);
        }

        resultSet.close();
        statment.close();
        connection.close();

        return workerList;
	}

	public int insert() throws SQLException {

		var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
		connection.setAutoCommit(false);

		var statment = connection.createStatement();

        var sql = "insert into worker values ('0001', 'k.jarrett', 73, 'music');";

        var resultCount = statment.executeUpdate(sql);

        connection.commit();
        statment.close();
        connection.close();

        return resultCount;
	}

	public int update() throws SQLException {

		var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
		connection.setAutoCommit(false);

		var statment = connection.createStatement();

        var sql = "update worker set age = 74 where id = '0001'";

        var resultCount = statment.executeUpdate(sql);

        connection.commit();
        statment.close();
        connection.close();

        return resultCount;
	}

	public int delete() throws SQLException {

		var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
		connection.setAutoCommit(false);

		var statment = connection.createStatement();

        var sql = "delete from worker where id = '0001'";

        var resultCount = statment.executeUpdate(sql);

        connection.commit();
        statment.close();
        connection.close();

        return resultCount;
	}
}
