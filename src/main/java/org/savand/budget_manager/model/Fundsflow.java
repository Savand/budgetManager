package org.savand.budget_manager.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.savand.budget_manager.util.LocalDateTimeAttributeConverter;

import lombok.Data;

@Entity
@Table(name = "fundsflows")
public @Data class Fundsflow extends BaseEntity{

	  @Column(nullable = false, name = "amount")
	  @NotNull
      protected Integer amount;

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "budget_id")
	  private Budget budget;

	  @Column
	  private String description;

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  private User byUser;

	  @Column(nullable = false, name = "operation_date_time")
	  @Convert(converter = LocalDateTimeAttributeConverter.class)
	  @NotNull
	  private LocalDateTime operationDateTime;

	  @Enumerated(EnumType.STRING)
	  @Column(name = "funds_flow_type")
	  private FundsFlowType fundsFlowType;
	  
	  public enum FundsFlowType {
		  FOOD, ENTERTAINMENT, CLOTHES, SERVICES, HEALTHCARE, PRESENTS, HOUSHOLD, 
		  SAVINGS, CHARITY, TRANSPORT, INCOME, OTHER;
	  }
	
}
