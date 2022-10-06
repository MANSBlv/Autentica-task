package lv.Autentica.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table
@Entity
public class Items {
	/*
	 * each item has their own unique Id, the name of the item, its parameters.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(value=AccessLevel.NONE)
	@Column(name="EquipmentId")
	private long eId;
	
	@Column(name="EquipmentName")
	@NotNull
	private String equipmentName;
	
	@Column(name="Parameters")
	@NotNull
	private String parameters;

	public Items(String equipmentName,String parameters) {
		this.equipmentName = equipmentName;
		this.parameters = parameters;
	}

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "EquipmentId"), inverseJoinColumns = @JoinColumn(name = "AddedItemsId"))
	@ToString.Exclude
	private Collection<AddedItems> addedItems = new ArrayList<AddedItems>();
	
	public void addAddedItems(AddedItems addedItem) {
		addedItems.add(addedItem);
	}
	
	public void removeAddedItems(AddedItems addedItem) {
		addedItems.remove(addedItem);
	}
	
	public void clearAddedItems(long id) {
		for(AddedItems addedItem: addedItems) {
			if(addedItem.getAddedItemsId() == id) {
				addedItems.remove(addedItem);
			}
		}
	}
	
}
