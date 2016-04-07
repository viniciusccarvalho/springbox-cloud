package io.springbox.recommendations.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import io.springbox.recommendations.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Vinicius Carvalho
 */
@Component
public class DBLoader {

	private JdbcTemplate template;
	private ReviewRepository repository;
	int batchSize = 1000;
	int loadSize = 10000;
	final String SQL = "INSERT INTO REVIEW (USER_ID, MOVIE_ID, RATING) VALUES (?,?,?)";

	@Value("classpath:u.data")
	private Resource ratingsResource;

	@Autowired
	public DBLoader(JdbcTemplate template, ReviewRepository repository){
		this.template = template;
		this.repository = repository;

	}

	@PostConstruct
	public void setup() throws Exception{

		if(repository.count() == 0){
			int count = 0;
			Scanner scanner = new Scanner(ratingsResource.getInputStream());
			scanner.useDelimiter("\\n");
			List<String> entries = new LinkedList<>();
			while(scanner.hasNext()){
				entries.add(scanner.nextLine());
				count++;
				if(entries.size()%batchSize==0){
					batchInsert(entries);
					entries.clear();
				}
				if(count >= loadSize)
					break;

			}
			batchInsert(entries);
		}
	}


	private void batchInsert(final List<String> entries) throws Exception{
		template.batchUpdate(SQL, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				String[] values = entries.get(i).split("\\t");
				ps.setLong(1,Long.valueOf(values[0]));
				ps.setLong(2,Long.valueOf(values[1]));
				ps.setInt(3,Integer.valueOf(values[2]));

			}

			@Override
			public int getBatchSize() {
				return entries.size();
			}
		});
	}


}
