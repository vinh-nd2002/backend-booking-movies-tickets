package com.hust.spectification.ticket;

import org.springframework.data.jpa.domain.Specification;

import com.hust.entity.Ticket;
import com.hust.form.filter.TicketFilterForm;
import com.hust.spectification.SearchCriteria;

public class TicketSpectificationBuilder {

	private TicketFilterForm filterForm;

	private String search;

	public TicketSpectificationBuilder(TicketFilterForm filterForm, String search) {
		this.filterForm = filterForm;
		this.search = search;
	}

	public Specification<Ticket> build() {
		SearchCriteria seachCriteria = new SearchCriteria("ticketCode", "Like", search);

		SearchCriteria usernameCriteria = new SearchCriteria("username", "Like", filterForm.getUsername());

		SearchCriteria numberPhoneCriteria = new SearchCriteria("numberPhone", "Like", filterForm.getNumberPhone());

		Specification<Ticket> where = null;
		// search by ticketCode
		if (search != null) {
			where = new TicketSpectification(seachCriteria);
		}

		// filter by username
		if (filterForm != null && filterForm.getUsername() != null) {
			if (where != null) {
				where = where.and(new TicketSpectification(usernameCriteria));
			} else {
				where = new TicketSpectification(usernameCriteria);
			}
		}

		// filter by user's numberPhone
		if (filterForm != null && filterForm.getNumberPhone() != null) {
			if (where != null) {
				where = where.and(new TicketSpectification(numberPhoneCriteria));
			} else {
				where = new TicketSpectification(numberPhoneCriteria);
			}
		}
		return where;

	}
}
