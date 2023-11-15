CREATE TABLE IF NOT EXISTS subjects_courses(
        id SERIAL PRIMARY KEY,
        id_courses bigint NOT NULL,
        name varchar(255) NOT NULL,
        hours smallint NOT NULL,
        CONSTRAINT fk_subjects_courses FOREIGN KEY (id_courses) REFERENCES courses (id)
);