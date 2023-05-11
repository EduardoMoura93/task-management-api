CREATE TABLE person (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    department VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE task (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    description TEXT,
    deadline DATETIME,
    department VARCHAR(255),
    duration BIGINT,
    assigned_person_id BIGINT,
    completed BOOLEAN,
    completed_date DATETIME,
    PRIMARY KEY (id),
    CONSTRAINT fk_assigned_person FOREIGN KEY (assigned_person_id) REFERENCES person(id) ON DELETE CASCADE
);
