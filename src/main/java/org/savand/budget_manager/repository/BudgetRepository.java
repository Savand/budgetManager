package org.savand.budget_manager.repository;

import org.savand.budget_manager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
