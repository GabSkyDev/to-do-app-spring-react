package com.GabSkyDev.to_do_list.repository;

import com.GabSkyDev.to_do_list.model.Priority;
import com.GabSkyDev.to_do_list.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByPriority(Priority priority);
}
