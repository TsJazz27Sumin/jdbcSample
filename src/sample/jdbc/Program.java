package sample.jdbc;

import java.sql.SQLException;

import sample.jdbc.dao.SampleDao;

public class Program {

	public static void main(String[] args) {

		var dao = SampleDao.instance;

		try {

			var insertCount = dao.insert();

			System.out.println(
            		String.format(
            				"insertCount:%d",
            				insertCount
            				)
            		);

			var workerList = dao.select();

			for (var worker : workerList){

				System.out.println(
	            		String.format(
	            				"id:%s name:%s age:%s department:%s",
	            				worker.getId(), worker.getName(), worker.getAge(), worker.getDepartment()
	            				)
	            		);
			}

			var updateCount = dao.update();

			System.out.println(
            		String.format(
            				"updateCount:%d",
            				updateCount
            				)
            		);

			workerList.addAll(dao.select());

			for (var worker : workerList){

				System.out.println(
	            		String.format(
	            				"id:%s name:%s age:%s department:%s",
	            				worker.getId(), worker.getName(), worker.getAge(), worker.getDepartment()
	            				)
	            		);
			}

			var deleteCount = dao.delete();

			System.out.println(
            		String.format(
            				"deleteCount:%d",
            				deleteCount
            				)
            		);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
