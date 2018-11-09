package br.edu.ifpe.jpa.querydsl;

import static com.querydsl.core.group.GroupBy.*;

import java.util.Date;
import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import br.edu.ifpe.jpa.IReportGenerator;
import br.edu.ifpe.jpa.entities.QClient;

public class ReportQuerydsl implements IReportGenerator {

	static EntityManagerHelper helper = EntityManagerHelper.getInstance();
	
	@Override
	public List<String> getClientNames(Date initialDate, Date finalDate) {
		QClient client = QClient.client;
		
		return helper.execute(query ->
			query
				.select(client.name)
				.from(client)
				.where(client.birthDate.after(initialDate).and(client.birthDate.before(finalDate)))
				.orderBy(client.birthDate.asc())
				.fetch()
			);
	}
	
	@Override
	public double getClientTotalCash(String email) {
		QClient client = QClient.client;
		
		return helper.execute(query ->
			query
				.select(client.accounts)
				.from(client)
				.where(client.email.eq(email))
				.
		);
	}

	@Override
	public List<String> getBestClientsEmails(int agency, int rankingSize) {
		
	}
}
