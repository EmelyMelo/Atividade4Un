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
		return helper.execute(Account.class, streams ->
			streams
				.where(conta -> conta.getClient().getEmail().equals(email))
				.sumDouble(conta -> conta.getBalance())
		);
	}

	@Override
	public List<String> getBestClientsEmails(int agency, int rankingSize) {
		return helper.execute(Account.class, streams ->
			streams
				.where(conta -> conta.getClient().getEmail())
				.where(conta -> conta.getAgency().equals(agency))
				.sumDouble(conta -> conta.getBalance())
				.sortedBy(conta -> conta.getBalance())
				.limit(rankingSize)
				.toList()
		);
	}
}
