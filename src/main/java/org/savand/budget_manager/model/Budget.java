package org.savand.budget_manager.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "budgets")
public class Budget extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "budget_creator_id")
  @Getter @Setter private User budgetCreator;
  
  @Column(name = "budget_name", nullable = false)
  @Getter @Setter private String budgetName;

  @Column
  @Getter @Setter private String description;

  @OneToMany(mappedBy = "budget")
  @BatchSize(size = 200)
  @Getter @Setter private List<Fundsflow> meansFlowList;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "budget_user", uniqueConstraints = @UniqueConstraint(columnNames = { "budget_id",
      "user_id" }), joinColumns = @JoinColumn(name = "budget_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  @Getter @Setter private List<User> contributors;

	@Override
	public String toString() {
	return "budget object [" + super.toString() + ", budgetName: " + budgetName 
			 + "]";
}

  
}