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

import lombok.Data;

@Entity
@Table(name = "budgets")
public  @Data class Budget extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "budget_creator_id")
  private User budgetCreator;
  
  @Column(name = "budget_name", nullable = false)
  private String budgetName;

  @Column
  private String description;

  @OneToMany(mappedBy = "budget")
  @BatchSize(size = 200)
  private List<Fundsflow> meansFlowList;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "budget_user", uniqueConstraints = @UniqueConstraint(columnNames = { "budget_id",
      "user_id" }), joinColumns = @JoinColumn(name = "budget_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> contributors;

  
}