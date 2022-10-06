package lv.Autentica.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Table
@Entity
public class AddedItems {//Can be interpreted as Ordered items!
	/*
	 * Each added item has their unique ID, Date when the order was made, reason written by the User,
	 *  the User who made the order, the status  of the order
	 * and the Item thats added in the order
	 */
	@Id
	@Column(name = "AddedItemsId")
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(value=AccessLevel.NONE)
	private long addedItemsId;
	
	@Column(name = "ordered_date")
    @DateTimeFormat(iso = ISO.DATE_TIME)
	private Date addedDate;
	
	@Column(name = "reason")
	@NotNull
	private String reason; //reason for added items
	
	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "IdUser")
	private User user;
	
	@Column(name = "Status")
	private Status status;

	@ManyToMany(mappedBy = "addedItems")
	@Cascade(CascadeType.ALL)
	@ToString.Exclude
	private Collection<Items> items = new ArrayList<Items>();
	
	public AddedItems(Date addedDate,String reason,  Collection<Items> items, User user, Status status) {
		this.addedDate = addedDate;
		this.reason = reason;
		this.items = items;
		this.user = user;
		this.status = status;
	}
	
	public void addItems(Items item) {
		items.add(item);
	}
	
	public void removeItems(Items item) {
		items.remove(item);
	}
}
