package br.edu.ifpe.jpa.jinq;

import java.util.Date;
import java.util.List;

import br.edu.ifpe.jpa.IReportGenerator;
import br.edu.ifpe.jpa.entities.Client;

public class ReportJinq implements IReportGenerator {
	
	static EntityManagerHelper helper = EntityManagerHelper.getInstance();

	@Override
	public List<String> getClientNames(Date initialDate, Date finalDate) {
		return helper.execute(Client.class, streams ->
			streams
				.where(client -> client.getBirthDate().after(initialDate))
				.where(client -> client.getBirthDate().before(finalDate))
				.select(Client::getName)
				.sortedBy(client -> client)
				.toList()
		); 
	}
	
	@Override
	public double getClientTotalCash(String email) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public List<String> getBestClientsEmails(int agency, int rankingSize) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
