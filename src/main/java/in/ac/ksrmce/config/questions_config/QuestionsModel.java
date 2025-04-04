package in.ac.ksrmce.config.questions_config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsModel {
	public List<QuestionsEntity> listquestions() {
		List<QuestionsEntity> listquestions = new ArrayList<QuestionsEntity>();

		try {
			Connection con = DatabaseConfigQuestions.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from questions");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listquestions.add(new QuestionsEntity(rs.getInt("id"), rs.getString("question"),
						rs.getString("option_one"), rs.getString("option_two"), rs.getString("option_three"),
						rs.getString("option_four"), rs.getInt("correct_option")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return listquestions;
	}

	public boolean save(QuestionsEntity question) {
		try {
			Connection con = DatabaseConfigQuestions.getConnection();
			String sql = "INSERT INTO questions(question, option_one, option_two, option_three, option_four, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, question.getQuestion());
			statement.setString(2, question.getOption_one());
			statement.setString(3, question.getOption_two());
			statement.setString(4, question.getOption_three());
			statement.setString(5, question.getOption_four());
			statement.setInt(6, question.getCorrect_option());
			int rowsInserted = statement.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateQuestion(QuestionsEntity question) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			String query = "update questions set question =?, option_one=?, option_two=?, option_three=?, option_four =?, correct_option=?  where id = ?";
			con = DatabaseConfigQuestions.getConnection();
			st = con.prepareStatement(query);
			st.setString(1, question.getQuestion());
			st.setString(2, question.getOption_one());
			st.setString(3, question.getOption_two());
			st.setString(4, question.getOption_three());
			st.setString(5, question.getOption_four());
			st.setInt(6, question.getCorrect_option());
			st.setInt(7, question.getId());
			st.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteQuestion(int id) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DatabaseConfigQuestions.getConnection();

			String query = "delete from questions where id = ?";

			st = con.prepareStatement(query);
			st.setInt(1, id);

			st.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
