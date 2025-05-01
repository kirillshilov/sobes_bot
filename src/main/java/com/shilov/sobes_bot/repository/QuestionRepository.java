package com.shilov.sobes_bot.repository;

import com.shilov.sobes_bot.model.Question;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Optional<Question> findQuestionByText(String text);
}
