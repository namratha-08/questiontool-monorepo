USE question_tool;

INSERT INTO questions (course_id, language_id, title, body, type, difficulty, metadata)
VALUES
  (NULL, NULL, 'What is Java?', 'Select the best answer about Java.', 'MCQ', 'EASY', JSON_OBJECT()),
  (NULL, NULL, 'Which symbol is used for lambda in Java?', 'Choose the lambda symbol.', 'MCQ', 'MEDIUM', JSON_OBJECT()),
  (NULL, NULL, 'Which is a Python keyword?', 'Select the correct Python keyword.', 'MCQ', 'EASY', JSON_OBJECT());
