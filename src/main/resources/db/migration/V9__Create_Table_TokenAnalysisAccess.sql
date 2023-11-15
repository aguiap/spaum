CREATE TABLE IF NOT EXISTS token_analysis_access (
     id SERIAL PRIMARY KEY,
     token varchar(150) UNIQUE NOT NULL,
     path varchar(255),
     course varchar(255),
     type_analyses varchar(255)
);

CREATE TABLE IF NOT EXISTS analysis (
   id SERIAL PRIMARY KEY,
   name varchar(255),
   subject varchar(255),
   registration varchar(255),
   note_one DOUBLE PRECISION,
   note_two DOUBLE PRECISION,
   note_three DOUBLE PRECISION,
   note_subs DOUBLE PRECISION,
   total_fouls int,
   status_notes varchar(20),
   status_fouls varchar(20),
   id_token_analysis_access bigint NOT NULL,
   CONSTRAINT fk_analysis FOREIGN KEY (id_token_analysis_access) REFERENCES token_analysis_access (id)
);
