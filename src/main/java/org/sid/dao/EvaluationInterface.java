package org.sid.dao;

import org.sid.entites.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationInterface extends JpaRepository<Evaluation, Long> {

}
