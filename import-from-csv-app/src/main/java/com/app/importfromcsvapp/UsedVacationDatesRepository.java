package com.app.importfromcsvapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedVacationDatesRepository extends JpaRepository<UsedVacationDates, Integer> {

}
