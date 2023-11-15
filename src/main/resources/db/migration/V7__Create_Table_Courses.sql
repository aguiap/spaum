CREATE TABLE IF NOT EXISTS courses (
      id SERIAL PRIMARY KEY,
      name varchar(255) UNIQUE NOT NULL,
      email varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS rules_courses(
    id SERIAL PRIMARY KEY,
    id_courses bigint NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT fk_rules_courses FOREIGN KEY (id_courses) REFERENCES courses (id)
);